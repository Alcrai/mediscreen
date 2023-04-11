FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patient-0.0.1.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","/patient-0.0.1.jar"]
