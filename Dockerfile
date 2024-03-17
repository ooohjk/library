FROM openjdk:21-jdk-slim
ADD /build/libs/*-1.0.0.jar app.jar
#ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
ENTRYPOINT ["java", "-jar", "/app.jar"]