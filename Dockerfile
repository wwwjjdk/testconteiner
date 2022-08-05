FROM openjdk:18-jdk-alpine

EXPOSE 8081

ADD target/lasthm050822-0.0.1-SNAPSHOT.jar myapp.jar

ENTRYPOINT ["java","-jar","/myapp.jar"]