# Phone Book Service

An implementation of a RESTful api Service using Spring Boot, Java 11, Swagger-OAS3 and Postgresql Database.

## How to run the service

This is a Spring Boot project built with Maven 3.3 and Java 11. So, after you clone it, you can follow [Spring Boot instructions](https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html) and run the phone book with one of the following 3 ways:

* Type `mvn spring-boot:run` in your console, to use the Maven plugin.
* Build the project with `mvn install` and then run it as a packaged application with `java -jar target/PhoneBookService-0.0.1-SNAPSHOT.jar.
* Load the project on your favourite IDE and run it from there. For Eclipse, this would be `mvn install eclipse:eclipse` to build the project, Import -> Existing Projects into Workspace and finally Run as Java Application on the Application.java file.

## Simple way to run the service
Install jdk and maven
Set environment variables
Run packaged jar file: PhoneBookService-0.0.1-SNAPSHOT.jar
Hit the url : http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/pb-controllers


## What to expect to see

When you run the application a RESTful phone book will be exposed under the URI `http://localhost:8080/phonebook/api/GetAllContacts`. You can type this URI to your favourite browser to see the contacts that are already registered 
## How to interact with the phone book

The project uses SpringFox to create a human readable specification for the phone book's API. This is accomplished using a combination of SpringFox classes and Swagger annotations. To take a look at the phone book's API you can type `http://localhost:8080/swagger-ui.html` on your browser. Phone Book Controller(PB Controller) is the entry point of our REST APIs that performing CRUD operations on contacts. These operations are:
* Search all contacts  *end-point:/phonebook/api/GetAllContacts
* Create new contact by passing json request *end-point:/phonebook/api/create-contact
* Retrieve a single specific contact using Contact number *end-point:/phonebook/api/GetContact-byNo
* Retrieve a single specific contact using Id *end-point:/phonebook/api/GetContact-byId
* Update name,lastname,contactnumber using id *end-point:/phonebook/api/Edit-Contact
* Delete a single specific contact *end-point:/phonebook/api/Delete-Contact

## A few words about the code

As in every Spring Boot project, you can start with the Application class which is used to run the project and also contains the Swagger configuration and description.

The class that hanldes the HTTP requests to the server is PBController, which makes use of Spring's RequestMapping annotations to correspond the requests to specific URIs to phone book actions and Swagger's configuration and docket to enrich the phone book API.

Under the repository package there is a Contact repository interface describing the operations to the persistent layer and an implementation and User.java contains table related information & Validation

Application properties file contains Postgresql DB information including hostname,username,password.

For logging, spring boot log4j2 is used. After every run a log file will get generated under logs folder. It will also create backup files.

Under Exceptions package, I tried to handle few exceptions. All global exceptions will be handled by globalexception handler class.
