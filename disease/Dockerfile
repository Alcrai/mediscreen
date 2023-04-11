FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} disease-0.0.1.jar
EXPOSE 8093
ENTRYPOINT ["java","-jar","/disease-0.0.1.jar"]
