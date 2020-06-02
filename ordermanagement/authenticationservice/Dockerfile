FROM openjdk:latest
ARG JAR_FILE=authenticationservice/target/*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]