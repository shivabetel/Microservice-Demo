FROM openjdk:latest
ARG JAR_FILE=customermanagement/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]