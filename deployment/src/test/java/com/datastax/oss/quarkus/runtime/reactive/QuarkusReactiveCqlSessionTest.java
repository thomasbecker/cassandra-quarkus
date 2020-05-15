/*
 * Copyright DataStax, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datastax.oss.quarkus.runtime.reactive;

import static org.assertj.core.api.Assertions.assertThat;

import com.datastax.dse.driver.api.core.cql.reactive.ReactiveRow;
import com.datastax.oss.driver.api.core.cql.ExecutionInfo;
import com.datastax.oss.driver.api.core.cql.SimpleStatement;
import com.datastax.oss.driver.internal.core.cql.DefaultExecutionInfo;
import com.datastax.oss.quarkus.CassandraTestBase;
import io.quarkus.test.QuarkusUnitTest;
import io.quarkus.test.common.QuarkusTestResource;
import io.reactivex.subscribers.TestSubscriber;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.groups.MultiSubscribe;
import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

@QuarkusTestResource(CassandraTestBase.class)
public class QuarkusReactiveCqlSessionTest {
  @Inject QuarkusReactiveCqlSession quarkusReactiveCqlSession;

  @RegisterExtension
  static QuarkusUnitTest runner =
      new QuarkusUnitTest()
          .setArchiveProducer(
              () -> ShrinkWrap.create(JavaArchive.class).addClasses(CassandraTestBase.class))
          .withConfigurationResource("application-cassandra-client.properties");

  @Test
  public void should_execute_reactive_query_string() {
    // when
    Multi<ReactiveRow> reactiveRowMulti =
        quarkusReactiveCqlSession.executeReactive("select * from system.local");

    // then
    validateReactiveRowNotEmpty(reactiveRowMulti);
    validateExecutionInfoNotEmpty((MutinyReactiveResultSet) reactiveRowMulti);
  }

  @Test
  public void should_execute_reactive_query_statement() {
    // when
    Multi<ReactiveRow> reactiveRowMulti =
        quarkusReactiveCqlSession.executeReactive(
            SimpleStatement.newInstance("select * from system.local"));

    // then
    validateReactiveRowNotEmpty(reactiveRowMulti);
    validateExecutionInfoNotEmpty((MutinyReactiveResultSet) reactiveRowMulti);
  }

  private void validateReactiveRowNotEmpty(Multi<ReactiveRow> reactiveRowMulti) {
    MultiSubscribe<ReactiveRow> result = reactiveRowMulti.subscribe();
    List<ReactiveRow> collect = result.asIterable().stream().collect(Collectors.toList());
    assertThat(collect).isNotEmpty();
  }

  private void validateExecutionInfoNotEmpty(MutinyReactiveResultSet reactiveRowMulti) {
    TestSubscriber<ExecutionInfo> testSubscriber = new TestSubscriber<>();
    reactiveRowMulti.getExecutionInfos().subscribe(testSubscriber);
    List<List<Object>> executionsInfo = testSubscriber.getEvents();
    assertThat(((DefaultExecutionInfo) executionsInfo.get(0).get(0)).getResponseSizeInBytes())
        .isGreaterThan(0);
    testSubscriber.assertComplete();
  }
}