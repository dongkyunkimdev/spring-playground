FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*.jar delivery-service.jar
ENTRYPOINT ["java", "-jar", "delivery-service.jar"]