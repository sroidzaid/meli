FROM openjdk:8-jdk-slim
ADD target/examen-meli-0.0.1-SNAPSHOT.jar meli.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "meli.jar"]