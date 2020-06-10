# Cassandra Quarkus - Runtime Module

The Runtime module for an Apache Cassandra(R) extension for Quarkus.

# The Purpose of Runtime

This module contains classes that are used during the runtime of this extension.
The clients of the cassandra-extension should import this dependency in their applications.

## The QuarkusCqlSession

The main integration point with clients using this extension is the `QuarkusCqlSession`.
When clients want to connects with Cassandra, they should inject `QuarkusCqlSession` in their code and execute all queries using it. 
The `QuarkusCqlSession` instance is produced as a `Singleton` and can be safely shared between all components of your application. Indeed it is usually considered an anti-pattern to create more than one 
instance one session per application; to understand how to create and use a session object correctly, 
refer to the [Java driver documentation](https://docs.datastax.com/en/developer/java-driver/latest/manual/core/#cql-session).

## Runtime Configuration

The `CassandraClientConnectionConfig` provides the runtime settings for configuration of the `QuarkusCqlSession`.