# Expense Control


is an application capable of uploading files (csv, xlsl)
and saving the data in a database for later consultation.
At the time of saving, the system notifies a user by email if the total
for the analyzed month is negative

### prerequisites

what do you need to run the project?
* [Gradle](https://gradle.org/)
* [MySQL](https://www.mysql.com/)
* [JDK-17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Docker Compose](https://docs.docker.com/compose/)

### how can we download the application?
#### SSH
```
git@gitlab.com:Mateusferian/expense-control.git
```
#### HTTPs
```
https://gitlab.com/Mateusferian/expense-control.git
```

### code versioning best practices
* Using Gitflow
* Using semantic commit

### how to run the application?
open the terminal and run the command below:

```
sh docker-compose-dev.sh
```

### how to run unit tests?
open the terminal and run the command below:

```
./gradlew test
```

### to access API documentation
Open your browser and go to the following link:
```
http://localhost:8080/swagger-ui.html#/
```
-------------------------------------------------------------------------------------------------
### TUTORIAL EMAIL
#### how to configure which email will receive a notification?
1-open the "services" package

2-open package "impl"

3-open the class "EmailJavaServiceImpl"

4- locate the "try catch" that is inside the "sendEmail" method

5- after locating the "try catch" change the email information passed in "email.addTo"
to the email that will receive the notification
-------------------------------------------------------------------------------------------------

### technologies used

* [Gradle](https://gradle.org/) - from mobile apps to microservices, from small businesses to large enterprises, Gradle helps teams build, automate, and deliver better software, faster.
####
* [Spring Boot Web Starter](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-test) - starter for building web, including RESTful apps, using Spring MVC. Uses Tomcat as the default embedded container
####
* [Model Mapper](http://modelmapper.org/) - applications often consist of similar but different object models where the data in two models may be similar but the structure and concerns of the models are different. Object mapping makes it easy to convert from one model to another, allowing separate models to remain segregated.
####
* [Swagger](https://swagger.io/) - Simplify API development for users, teams, and enterprises with the open source, professional Swagger toolset.
####
* [Power Mock](https://powermock.github.io/) - PowerMock is a framework that extends other mock libraries like EasyMock with more powerful features. PowerMock uses a custom class loader and bytecode handling to allow mocking static methods, constructors, final classes and methods, private methods, removing static initializers, and much more.
####
* [Apache Commons](https://commons.apache.org/proper/commons-email/) - Commons Email aims to provide a API for sending email. It is built on top of the Java Mail API, which it aims to simplify