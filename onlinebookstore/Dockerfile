FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/onlinebookstore-1.0.jar onlinebookstore.jar
ENTRYPOINT ["java", "-jar", "/onlinebookstore.jar"]