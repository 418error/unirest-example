# unirest-example
Using a lite weight framework like [Java spark][Java Spark] and [unirest.io][unirest.io] to consume rest APIs.

## Prerequisites
I'm on a Mac using Homebrew goodness...

• Java 1.8
```
brew cask install java
```
• Gradle
```
brew install gradle
```

## Setting up and compiling
1. Clone it
  ```
  git clone git@github.com:lendmeapound/unirest-example.git
  ```

2. Get in it
  ```
  cd unirest-example
  ```

3. Build it
  ```
  gradle build
  ```

4. Run it
  ```
  java -jar build/libs/unirest-example.jar
  ```
5. Hit it
  http://localhost:4567



## Kudos
[Java spark][Java Spark] - For an awesome framework.

[Project Lombok][Project Lombok] - No more getters and setters.

[Apache Commons][Apache Commons] - Taking the pain out of string validation.

[FasterXML][FasterXML] - For the Jackson databind module.

[GSON][GSON] - Converting Java Objects into their JSON representation and back again.

[unirest.io][unirest.io] - Making java http client much easier!


[Obtaining the WebSphere MQ classes for JMS]:http://www-01.ibm.com/support/docview.wss?uid=swg21683398
[Java Spark]:http://sparkjava.com/
[Project Lombok]:https://projectlombok.org/
[Apache Commons]: https://commons.apache.org/
[FasterXML]:http://wiki.fasterxml.com/JacksonHome
[XStream]:https://x-stream.github.io/
[GSON]:https://github.com/google/gson
[unirest.io]:http://unirest.io/
