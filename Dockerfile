FROM openjdk:17-jdk-alpine
COPY ./build/libs/storeDemo.jar /app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
