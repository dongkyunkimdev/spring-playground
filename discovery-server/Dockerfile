FROM amazoncorretto:17
WORKDIR /app
EXPOSE 8080
COPY ./build/libs/*.jar discovery-server.jar
ENTRYPOINT ["java", "-jar", "discovery-server.jar"]