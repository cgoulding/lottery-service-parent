# Lottery Service

A simple lottery service for fun

## Overview

### Features
REST interface
Ability to generate a ticket with n lines.
Ability to check the status of lines on a ticket.
Ability to ammend with n additional lines.
Once the status of a ticket has been checked it cannot be ammended.

### Lottery Rules
You have a series of lines on a ticket with 3 numbers, each of which has a value of 0, 1, or 2.
For each ticket if the sum of the values is 2, the result is 10.
Otherwise if they are all the same, the result is 5.
Otherwise so long as both 2nd and 3rd numbers are different from the 1st, the result is 1.
Otherwise the result is 0.

## Getting Started

Check out and build locally

### Prerequisites

Docker and Maven

### Installing

mvn clean install

## Running the tests

mvn clean test

## Deployment

### From IDE
Run com.goulding.connor.lottery.web.LotteryServiceApplication main class

### From Command Line
java -jar lottery-service-web/target/lottery-service-web-0.0.1-SNAPSHOT.jar

### Docker image
docker run -it -p 8080:8080 registry.local.dev/lottery-service:0.0.1-SNAPSHOT

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management

## Authors

* **Connor Goulding** - *Initial work* - (https://github.com/cgoulding)

## License

http://www.apache.org/licenses/LICENSE-2.0
