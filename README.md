# lottery-service-parent

Simple lottery service

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
