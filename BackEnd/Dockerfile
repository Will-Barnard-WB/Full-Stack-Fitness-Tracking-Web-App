# Stage 1: Build with Maven
FROM maven:3.9.4-jdk-17 AS build

WORKDIR /app
COPY . .

RUN mvn clean package

# Stage 2: Run the app
FROM openjdk:17-jdk-slim

WORKDIR /app

COPY --from=build /app/target/BackEnd-0.0.1-SNAPSHOT.jar app.jar

CMD ["java", "-jar", "app.jar"]
