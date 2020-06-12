# Datastax Apache Cassandra® Quarkus extension

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/com.datastax.oss.quarkus/cassandra-quarkus-client/badge.svg)](https://maven-badges.herokuapp.com/maven-central/com.datastax.oss.quarkus/cassandra-quarkus-client/)

An Apache Cassandra(R) extension for Quarkus.

*You can find the latest version of the official documentation 
[here](https://github.com/datastax/cassandra-quarkus/blob/master/documentation/src/main/asciidoc/cassandraclient.adoc). 
Documentation for specific versions can be consulted via their respective release tags, e.g. 
[1.0.0-alpha1](https://github.com/datastax/cassandra-quarkus/blob/1.0.0-alpha1/documentation/src/main/asciidoc/cassandraclient.adoc).*

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

* [Quickstart guide]
* Bug tracking: [Issues]
* Quarkus [Mailing list]
* [Changelog]

[quickstart guide]: https://github.com/datastax/cassandra-quarkus/blob/master/documentation/src/main/asciidoc/cassandraclient.adoc 
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
