FROM maven:3.8.1-openjdk-16-slim

MAINTAINER okanmenevseoglu@gmail.com

WORKDIR /app

COPY . /app

ENV TZ Europe/Amsterdam

RUN mvn clean install

ENTRYPOINT ["java","-jar","target/rpn-calculator-1.0.0.jar"]

EXPOSE 8080
