# ohlcEngine


[OHLC Engine](https://github.com/devenparab/ohlcEngine) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.ohlc.trading.ohlcEngine.OHLCEngineApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Deploying the application to Pivotal Cloud Foundry

The easiest way to deploy the sample application to Cloud Foundry is to use the [cf CLI](https://github.com/cloudfoundry/cli):

```shell
cf push manifest.yml 
```

This will do following things:

* Upload a droplet on CF.
* An app with name from manifest will be created on CF
* Service called "ohlcEngine"

## System Design

![alt text](https://github.com/devenparab/ohlcEngine/blob/master/blob/SystemDesign.PNG)

## Copyright

Released under Free License :)