# admin-test

stacks

spring-boot 2.x.x
spring jpa

h2

chart.js

### mysql setting with docker
```
docker pull mysql
docker run --name mysqldb -e MYSQL_ROOT_PASSWORD=1234 -e MYSQL_DATABASE=marketing -d -p 3306:3306 mysql:latest
```

### build and run
```
./gradlew build
./gradlew bootjar
```