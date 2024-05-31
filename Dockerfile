FROM openjdk:17
ADD target/github-actions-0.0.1-SNAPSHOT.jar backend.jar
ADD .env .
ENTRYPOINT ["java", "-jar", "backend.jar"]