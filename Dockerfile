FROM openjdk:17

WORKDIR /app

COPY target/appversion-0.0.1-SNAPSHOT.jar app-version.jar

EXPOSE 8000

ENTRYPOINT ["java", "-jar", "app-version.jar"]
