FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*.jar order-service.jar
ENTRYPOINT ["java", "-jar", "order-service.jar"]