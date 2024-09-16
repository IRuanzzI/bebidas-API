
FROM ubuntu:latest AS build


RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    maven

Set the working directory
WORKDIR /app

COPY . .


RUN mvn clean install -DskipTests


FROM openjdk:17-jdk-slim

EXPOSE 8080

COPY --from=build /app/target/servico-bebdidas-0.0.1-SNAPSHOT.jar /app/app.jar

WORKDIR /app

ENTRYPOINT ["java", "-jar", "app.jar"]
