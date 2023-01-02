# Java API

This is me learning how to create a REST API with Java Spring Boot

[reference](https://www.youtube.com/watch?v=9SGDpanrc8U&ab_channel=Amigoscode)

I am using MariaDB 10.9.4
Run in development

```shell
mvn spring-boot:run
```

To deploy

```shell
mvn clean install
docker build -t javaapi .
docker run -p 5000:5000 javaapi
```