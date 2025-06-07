# Stage 1: Build with Maven (uses JDK 11 by default)
FROM maven:3.8.6 AS build

WORKDIR /app
COPY . .

RUN mvn clean package

# Stage 2: Run the app with JDK 17
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/BackEnd-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
