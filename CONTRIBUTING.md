# Contributing guidelines

**Want to contribute? Great!** 
We try to make it easy, and all contributions, even the smaller ones, are more than welcome.
This includes bug reports, fixes, documentation, examples... 
But first, read this page.

## Reporting an issue

This project uses GitHub issues to manage the issues. Open an issue directly in GitHub.

If you believe you found a bug, and it's likely possible, please indicate a way to reproduce it, what you are seeing and what you would expect to see.
Don't forget to indicate your Quarkus, Java, Maven/Gradle and GraalVM version. 

### Code reviews

All submissions, including submissions by project members, need to be reviewed before being merged.

## Setup

If you have not done so on this machine, you need to:
 
* Install Git and configure your GitHub access
* Install Java SDK (OpenJDK recommended)
* Install [GraalVM](https://quarkus.io/guides/building-native-image)
* Install platform C developer tools:
    * Linux
        * Make sure headers are available on your system (you'll hit 'Basic header file missing (<zlib.h>)' error if they aren't).
            * On Fedora `sudo dnf install zlib-devel`
            * Otherwise `sudo apt-get install libz-dev`
    * macOS
        * `xcode-select --install` 
* Set `GRAALVM_HOME` to your GraalVM Home directory e.g. `/opt/graalvm` on Linux or `$location/JDK/GraalVM/Contents/Home` on macOS
* Install Docker: it is used to run the integration tests for this project:
    * Check [the installation guide](https://docs.docker.com/install/), and [the MacOS installation guide](https://docs.docker.com/docker-for-mac/install/)
    * If you just installed docker, be sure that your current user can run a container (no root required). 
      On Linux, check [the post-installation guide](https://docs.docker.com/install/linux/linux-postinstall/)

## Coding Guidelines

### Java

We follow the [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html). See
https://github.com/google/google-java-format for IDE plugins. The rules are not configurable.

The build will fail if the code is not formatted. To format all files from the command line, run:
 
```
mvn fmt:format
```

Some aspects are not covered by the formatter: braces must be used with `if`, `else`, `for`, `do`
and `while` statements, even when the body is empty or contains only a single statement.

### XML

The build will fail if XML files are not formatted correctly. Run the following command before you
commit:

```java
mvn xml-format:xml-format
```

The formatter does not enforce a maximum line length, but please try to keep it below 100 characters
to keep files readable across all mediums (IDE, terminal, Github...).

## Coding style -- production code

Do not use static imports. They make things harder to understand when you look at the code 
someplace where you don't have IDE support, like Github's code view.

Avoid abbreviations in class and variable names. A good rule of thumb is that you should only use
them if you would also do so verbally, for example "id" and "config" are probably reasonable.
Single-letter variables are permissible if the variable scope is only a few lines, or for commonly
understood cases (like `i` for a loop index).

Keep source files short. Short files are easy to understand and test. The average should probably 
be around 200-300 lines. 

### Javadoc

All types in "API" packages must be documented. For "internal" packages, documentation is optional,
but in no way discouraged: it's generally a good idea to have a class-level comment that explains
where the component fits in the architecture, and anything else that you feel is important.

### Logs

We use SLF4J; loggers are declared like this:

```java
private static final Logger LOG = LoggerFactory.getLogger(TheEnclosingClass.class);
```


### Nullability annotations

We use the [Spotbugs annotations](https://spotbugs.github.io) to document nullability of parameters,
method return types and class members.

Please annotate any new class or interface with the appropriate annotations: `@NonNull`, `@Nullable`. Make sure you import 
the types from `edu.umd.cs.findbugs.annotations`, there are homonyms in the classpath.


## Coding style -- test code

Static imports are permitted in a couple of places:
* All AssertJ methods, e.g.:
  ```java
  assertThat(node.getDatacenter()).isNotNull();
  fail("Expecting IllegalStateException to be thrown");
  ```
* All Mockito methods, e.g.:
  ```java
  when(codecRegistry.codecFor(DataTypes.INT)).thenReturn(codec);
  verify(codec).decodePrimitive(any(ByteBuffer.class), eq(ProtocolVersion.DEFAULT));
  ```

Test methods names use lower snake case, generally start with `should`, and clearly indicate the
purpose of the test, for example: `should_fail_if_key_already_exists`. If you have trouble coming 
up with a simple name, it might be a sign that your test does too much, and should be split.

We use AssertJ (`assertThat`) for assertions. Don't use JUnit assertions (`assertEquals`, 
`assertNull`, etc).

Don't try to generify at all cost: a bit of duplication is acceptable, if that helps keep the tests
simple to understand (a newcomer should be able to understand how to fix a failing test without
having to read too much code).

Test classes can be a bit longer, since they often enumerate similar test cases. You can also
factor some common code in a parent abstract class named with "XxxTestBase", and then split
different families of tests into separate child classes.

## Running the tests

### Unit tests

    mvn clean test
   
### Integration tests

    mvn clean verify

### Integration tests native

    mvn clean verify -Dnative
    
### Generating documentation
   
    mvn clean package -Prelease    
    

## Build

* Clone the repository: `git clone git@github.com:datastax/cassandra-quarkus.git`
* Navigate to the directory: `cd cassandra-quarkus`
* Invoke `mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V` from the root directory

```bash
git clone git@github.com:datastax/cassandra-quarkus.git
cd cassandra-quarksu
mvn clean install -DskipTests=true -Dmaven.javadoc.skip=true -B -V
```

This build skipped all the tests, native-image builds and documentation generation. 

Removing the `-DskipTests -DskipITs` flags enables the tests. 
It will take much longer to build but will give you more guarantees on your code. 

You can build and test native images in the integration tests supporting it by using `mvn install -Dnative`.


## License headers

The build will fail if some license headers are missing. To update all files from the command line,
run:

```
mvn license:format
```
## Commits

Keep your changes **focused**. Each commit should have a single, clear purpose expressed in its 
message.

Resist the urge to "fix" cosmetic issues (add/remove blank lines, move methods, etc.) in existing
code. This adds cognitive load for reviewers, who have to figure out which changes are relevant to
the actual issue. If you see legitimate issues, like typos, address them in a separate commit (it's
fine to group multiple typo fixes in a single commit).

Isolate trivial refactorings into separate commits. For example, a method rename that affects dozens
of call sites can be reviewed in a few seconds, but if it's part of a larger diff it gets mixed up
with more complex changes (that might affect the same lines), and reviewers have to check every
line.

Commit message subjects start with a capital letter, use the imperative form and do **not** end
with a period:

* correct: "Add test for CQL request handler"
* incorrect: "~~Added test for CQL request handler~~"
* incorrect: "~~New test for CQL request handler~~"

Avoid catch-all messages like "Minor cleanup", "Various fixes", etc. They don't provide any useful
information to reviewers, and might be a sign that your commit contains unrelated changes.
 
We don't enforce a particular subject line length limit, but try to keep it short.

You can add more details after the subject line, separated by a blank line. The following pattern
(inspired by [Netty](http://netty.io/wiki/writing-a-commit-message.html)) is not mandatory, but
welcome for complex changes:

```
One line description of your change
 
Motivation:

Explain here the context, and why you're making that change.
What is the problem you're trying to solve.
 
Modifications:

Describe the modifications you've done.
 
Result:

After your change, what will change.
```

## Pull requests

Like commits, pull requests should be focused on a single, clearly stated goal.

Don't base a pull request onto another one, it's too complicated to follow two branches that evolve
at the same time. If a ticket depends on another, wait for the first one to be merged. 

If you have to address feedback, avoid rewriting the history (e.g. squashing or amending commits):
this makes the reviewers' job harder, because they have to re-read the full diff and figure out
where your new changes are. Instead, push a new commit on top of the existing history; it will be
squashed later when the PR gets merged. If the history is complex, it's a good idea to indicate in
the message where the changes should be squashed:

```
* 20c88f4 - Address feedback (to squash with "Add metadata parsing logic") (36 minutes ago)
* 7044739 - Fix various typos in Javadocs (2 days ago)
* 574dd08 - Add metadata parsing logic (2 days ago)
```

(Note that the message refers to the other commit's subject line, not the SHA-1. This way it's still
relevant if there are intermediary rebases.)

If you need new stuff from the base branch, it's fine to rebase and force-push, as long as you don't
rewrite the history. Just give a heads up to the reviewers beforehand. Don't push a merge commit to
a pull request.

Be sure to test your pull request in:

1. Java mode
2. Native mode
