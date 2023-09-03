FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*.jar user-service.jar
ENTRYPOINT ["java", "-jar", "user-service.jar"]