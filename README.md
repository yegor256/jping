[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](https://www.rultor.com/b/yegor256/jping)](https://www.rultor.com/p/yegor256/jping)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/yegor256/jping/actions/workflows/mvn.yml/badge.svg)](https://github.com/yegor256/jping/actions/workflows/mvn.yml)
[![PDD status](https://www.0pdd.com/svg?name=yegor256/jping)](https://www.0pdd.com/p?name=yegor256/jping)
[![Maven Central](https://img.shields.io/maven-central/v/com.yegor256/jping.svg)](https://maven-badges.herokuapp.com/maven-central/com.yegor256/jping)
[![Javadoc](https://www.javadoc.io/badge/com.yegor256/jping.svg)](https://www.javadoc.io/doc/com.yegor256/jping)
[![codecov](https://codecov.io/gh/yegor256/jping/branch/master/graph/badge.svg)](https://codecov.io/gh/yegor256/jping)
[![Hits-of-Code](https://hitsofcode.com/github/yegor256/jping)](https://hitsofcode.com/view/github/yegor256/jping)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/yegor256/jping/blob/master/LICENSE.txt)

JUnit5 execution condition that checks whether a connection to public Internet is available.

First, you add this to your `pom.xml`:

```xml
<dependency>
  <groupId>com.yegor256</groupId>
  <artifactId>jping</artifactId>
  <version>0.0.3</version>
  <scope>test</scope>
</dependency>
```

Then, you use it like this:

```java
import com.yegor256.WeAreOnline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeAreOnline.class)
final class MyTest {
  @Test
  void canDownloadViaHttp() throws Exception {
    new URL("https://www.google.com").openStream();
  }
}
```

Or if need to override default settings:

```java
import com.yegor256.OnlineMeans;
import com.yegor256.WeAreOnline;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(WeAreOnline.class)
final class MyTest {
    @Test
    @OnlineMeans(url = "https://www.amazon.com", connectTimeout = 500, readTimeout = 1500)
    void canDownloadViaHttp() throws Exception {
        new URL("https://www.amazon.com").openStream();
    }
}
```

We don't want this unit test to be executed when no Internet connection
is available. The `WeAreOnline` execution condition will prevent JUnit5 from
executing the test when you are offline.

## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
