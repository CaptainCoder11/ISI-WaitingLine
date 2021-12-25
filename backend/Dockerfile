#
# Build stage
#
FROM maven:3.8.4-jdk-11-slim AS build
COPY . /usr/src/app
RUN mvn -f /usr/src/app/pom.xml clean -Dmaven.test.skip package

#
# Package stage
#
# FROM openjdk:11-jre-slim
FROM gcr.io/distroless/java
COPY --from=build /usr/src/app/target/waitingline-0.0.1-SNAPSHOT.jar /usr/app/
ENTRYPOINT ["java", "-jar", "/usr/app/waitingline-0.0.1-SNAPSHOT.jar"]
EXPOSE 3001
