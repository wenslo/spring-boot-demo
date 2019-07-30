## Spring Boot Demo

## About
This is just a simple demo for using **Spring Boot**.

This demo consists of following three components:

- Spring Data JPA with Query DSL
- Spring Security

## Requirements
This demo is build with with Gradle 5 and Java 1.8.

## Usage
Just start the application with the Spring Boot gradle plugin(`gradle bootRun`). The application is running at [http://localhost:8080](http://localhost:8080).


## TODO :
- Add component of student(enrollment,order,high-concurrency,appointment notice, mock the high-concurrency)
- Using multithreading on this demo.
- Running with front-end(vue-admin,react-admin,whatever)
- Improve docker config , add it on jenkins , continuous integration it
- Finally split it to micro-service, using spring-cloud or dubbox