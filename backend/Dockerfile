#FROM adoptopenjdk/openjdk11:alpine
#RUN addgroup -S spring && adduser -S spring -G spring
#USER spring:spring
#ARG JAR_FILE=target/*.jar
#COPY ${JAR_FILE} app.jar
#ENTRYPOINT ["java","-jar","/app.jar"]

FROM openjdk:8
ADD target/spring-boot-mysql-master.jar spring-boot-mysql-master.jar
EXPOSE 8086
ENTRYPOINT ["java", "-jar", "spring-boot-mysql-master.jar"]