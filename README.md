# Datastax Apache Cassandra® Quarkus extension

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.datastax.oss.quarkus/cassandra-quarkus-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.datastax.oss.quarkus/cassandra-quarkus-client/)

*You can find the
documentation for latest version through [DataStax Docs] or via the release tags, e.g. 
[1.0.0-alpha1](https://github.com/datastax/cassandra-quarkus/tree/1.0.0-alpha1).*

An Apache Cassandra(R) extension for Quarkus.

[DataStax Docs]: todo_link_to_datastax_docs

## Getting the extension

The extension artifact is published in Maven central, under the group id [com.datastax.oss.quarkus]:

```xml
<dependency>
  <groupId>com.datastax.oss.quarkus</groupId>
  <artifactId>cassandra-quarkus-client</artifactId>
  <version>${cassandra-quarkus.version}</version>
</dependency>
```

[com.datastax.oss.quarkus]: http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22com.datastax.oss%22.quarkus%22

## Compatibility

The extension is compatible with Quarkus version 1.5.0.Final and higher.

It requires Java 8 or higher.

## Useful links

* [quickstart guide]
* Bug tracking: [Issues]
* [Mailing list]
* [Changelog]

[quickstart guide]: todo_link_to_live_adoc_from_docs_module 
[Issues]: https://github.com/datastax/cassandra-quarkus/issues
[Mailing list]: https://groups.google.com/forum/#!forum/quarkus-dev
[Changelog]: changelog/

## License

&copy; DataStax, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

----

DataStax is a registered trademark of DataStax, Inc. and its subsidiaries in the United States 
and/or other countries.

Apache Cassandra, Apache, Tomcat, Lucene, Solr, Hadoop, Spark, TinkerPop, and Cassandra are 
trademarks of the [Apache Software Foundation](http://www.apache.org/) or its subsidiaries in
Canada, the United States and/or other countries. 


## Running integration tests

To run the non-native integration tests simply execute:

    mvn clean verify
    
To run the native integration tests as well, execute:

    mvn clean verify -Dnative
    
You need to point the environment variable `GRAALVM_HOME` to a valid Graal 
installation root.