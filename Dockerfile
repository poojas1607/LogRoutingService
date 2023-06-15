# syntax=docker/dockerfile:experimental
# Download dependencies
FROM maven:3.6.3-jdk-11-slim AS dependency
WORKDIR /usr/app
COPY pom.xml .
RUN mvn dependency:go-offline -B --fail-never

# Build JAR
FROM dependency as build
WORKDIR /usr/app
COPY src src
COPY src/main/resources/secrets.json secrets.json
RUN --mount=type=cache,target=.m2 mvn clean install -DskipTests=true

FROM openjdk:11-jre-slim
WORKDIR /usr/app

EXPOSE 8000
COPY --from=build /usr/app/target/central-kyc-*.jar app.jar
COPY --from=build /usr/app/secrets.json .
CMD ["java", "-jar", "-Dspring.profiles.active=local", "app.jar"]
