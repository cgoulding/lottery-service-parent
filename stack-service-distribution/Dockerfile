# Start from base image with Java 8
FROM java:8-jre-alpine

WORKDIR /

COPY target/dependency/lib/* lib/

CMD java -jar lib/stack-service-web-0.0.1-SNAPSHOT-exec.jar