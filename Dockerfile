FROM openjdk:19

WORKDIR /app
ADD target/javaapi-0.0.1-SNAPSHOT.jar /app/javaapi-0.0.1-SNAPSHOT.jar
EXPOSE 5000
CMD java -jar /app/javaapi-0.0.1-SNAPSHOT.jar