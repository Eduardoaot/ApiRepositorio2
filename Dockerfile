# Usar una imagen base de Java 21
FROM eclipse-temurin:21.0.3_9-jdk

ARG JAR_FILE=target/coleccion-0.0.1.jar
COPY ${JAR_FILE} app_coleccion.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app_coleccion.jar"]