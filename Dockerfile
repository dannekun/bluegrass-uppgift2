FROM openjdk
COPY target/bluegrass-uppgift-0.0.1-SNAPSHOT.jar app.jar
CMD ["java","-jar","/app.jar"]