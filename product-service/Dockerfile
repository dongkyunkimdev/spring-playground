FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*.jar product-service.jar
ENTRYPOINT ["java", "-jar", "product-service.jar"]