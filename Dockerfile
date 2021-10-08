FROM openjdk:11

WORKDIR /app

COPY target/stecnology-api-0.0.1-SNAPSHOT.jar /app/stecnology-app.jar

ENTRYPOINT ["java","-jar","stecnology-app.jar"]