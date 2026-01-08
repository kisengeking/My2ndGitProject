FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY target/app.jar app.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]
