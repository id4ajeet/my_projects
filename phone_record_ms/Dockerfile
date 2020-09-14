FROM openjdk:8-jre
LABEL imageName=phone_record_ms imageVersion=0.0.1-SNAPSHOT
EXPOSE 8080
COPY ./target/*.jar /app.jar
ENTRYPOINT ["java","-jar","app.jar"]
