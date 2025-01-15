FROM openjdk:24-ea-19-jdk-slim-bullseye

WORKDIR /app

COPY target/Restaurant-0.0.1-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]