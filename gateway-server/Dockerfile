FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*.jar gateway-server.jar
ENTRYPOINT ["java", "-jar", "gateway-server.jar"]