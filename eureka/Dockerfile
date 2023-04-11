FROM openjdk:17-jdk-alpine
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-0.0.1.jar
EXPOSE 9102
ENTRYPOINT ["java","-jar","/eureka-0.0.1.jar"]
