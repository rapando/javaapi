FROM openjdk:19-jdk-alpine

RUN apk add --no-cache maven

WORKDIR /app
COPY . .
RUN ls -lh .
RUN mv /app/src/main/resources/prod.application.properties /app/src/main/resources/application.properties

RUN mvn install
CMD java -jar /app/target/javaapi-0.0.1-SNAPSHOT.jar