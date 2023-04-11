FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} history-0.0.1.jar
EXPOSE 8092
ENTRYPOINT ["java","-jar","/history-0.0.1.jar"]
