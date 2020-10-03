# ohlcEngine

[![Build Status][build-status-shield]][build-status-url]
[![Code Coverage][code-coverage-shield]][code-coverage-shield]
[![Service Tests][service-tests-shield]][service-tests-url]
[![Invoker Test][invoker-test-shield]][invoker-test-url]
[![MIT License][license-shield]][license-url]
[![LinkedIn][linkedin-shield]][linkedin-url]

[OHLC Engine](https://github.com/devenparab/ohlcEngine) sample app.

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)

## Running the application through WorkerReaderImplTest.java 

Navigate to [![Invoker Test][invoker-test-shield]][invoker-test-url] and invoke this jUnit test to get the output. Since the dataset is larger all the records cannot be displayed in IDE console due to buffer size limitation, hence for simplicity the end-to-end logs are appended to external `${LOGS}/ohlc-logger.log` file under following location `ohlcEngine/logs/*` this folder and files will be generated once [![Invoker Test][invoker-test-shield]][invoker-test-url] is started. 


## Running the application locally [Currently dont use this method]

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



<!-- MARKDOWN LINKS & IMAGES -->
[build-status-shield]: https://img.shields.io/badge/build-passing-brightgreen
[build-status-url]: https://github.com/devenparab/ohlcEngine
[code-coverage-shield]: https://img.shields.io/badge/coverage-85%25-yellowgreen
[code-coverage-url]: https://github.com/devenparab/ohlcEngine
[service-tests-shield]: https://img.shields.io/badge/service%20tests-passing-brightgreen
[service-tests-url]: https://github.com/devenparab/ohlcEngine/tree/master/src/test/java/com/ohlc/trading/ohlcEngine
[invoker-test-shield]: https://img.shields.io/badge/invoke-WorkerReaderImplTest.java-blue
[invoker-test-url]: https://github.com/devenparab/ohlcEngine/blob/master/src/test/java/com/ohlc/trading/ohlcEngine/process/reader/impl/WorkerReaderImplTest.java
[license-shield]: https://img.shields.io/github/license/othneildrew/Best-README-Template.svg?style=flat-square
[license-url]: https://github.com/othneildrew/Best-README-Template/blob/master/LICENSE.txt
[linkedin-shield]: https://img.shields.io/badge/-LinkedIn-black.svg?style=flat-square&logo=linkedin&colorB=555
[linkedin-url]: https://www.linkedin.com/in/deven-parab-b3125522/
