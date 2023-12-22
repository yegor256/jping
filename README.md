[![EO principles respected here](https://www.elegantobjects.org/badge.svg)](https://www.elegantobjects.org)
[![DevOps By Rultor.com](http://www.rultor.com/b/yegor256/jping)](http://www.rultor.com/p/yegor256/jping)
[![We recommend IntelliJ IDEA](https://www.elegantobjects.org/intellij-idea.svg)](https://www.jetbrains.com/idea/)

[![mvn](https://github.com/yegor256/jping/actions/workflows/mvn.yml/badge.svg)](https://github.com/yegor256/jping/actions/workflows/mvn.yml)
[![PDD status](http://www.0pdd.com/svg?name=yegor256/jping)](http://www.0pdd.com/p?name=yegor256/jping)
[![Maven Central](https://img.shields.io/maven-central/v/com.yegor256/jping.svg)](https://maven-badges.herokuapp.com/maven-central/com.yegor256/jping)
[![Javadoc](http://www.javadoc.io/badge/com.yegor256/jping.svg)](http://www.javadoc.io/doc/com.yegor256/jping)
[![codecov](https://codecov.io/gh/yegor256/jping/branch/master/graph/badge.svg)](https://codecov.io/gh/yegor256/jping)
[![Hits-of-Code](https://hitsofcode.com/github/yegor256/jping)](https://hitsofcode.com/view/github/yegor256/jping)
[![License](https://img.shields.io/badge/license-MIT-green.svg)](https://github.com/yegor256/jping/blob/master/LICENSE.txt)

JUnit5 execution condition that checks whether a connection to public Internet is available.

First, you add this to your `pom.xml`:

```xml
<dependency>
  <groupId>com.yegor256</groupId>
  <artifactId>jping</artifactId>
  <version>0.0.1</version>
</dependency>
```

Then, you use it like this:

```java
import com.yegor256.WeAreOnline;

@ExtendWith(WeAreOnline.class)
final class MyTest {
  // Your test methods
}
```


## How to Contribute

Fork repository, make changes, send us a [pull request](https://www.yegor256.com/2014/04/15/github-guidelines.html).
We will review your changes and apply them to the `master` branch shortly,
provided they don't violate our quality standards. To avoid frustration,
before sending us your pull request please run full Maven build:

```bash
$ mvn clean install -Pqulice
```

You will need Maven 3.3+ and Java 8+.
