FROM openjdk:17-jdk-slim
WORKDIR /app
COPY src ./src
COPY target/testing-1.0-SNAPSHOT.jar /app/testing.jar
EXPOSE 8080
CMD [ "java", "-jar", "testing.jar" ]