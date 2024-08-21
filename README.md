# Password Manager Backend

Backend of Password Manager application, which allows users to generate and manage robust and random passwords to ensure their accounts.

## Overview

Technologies used:
  - Java
  - Spring Boot
  - Spring Data JPA
  - MySQL

## Architecture

This project was created using hexagonal architecture. Hexagonal architecture is one of the moust famous implementation of clean architecture, and it aims to create loosely coupled architectures, where components can be tested independently.

![Hexagonal Architecture](https://github.com/user-attachments/assets/8c3ac06a-3879-46d1-9816-c92452478419)

This architecture defines three principal layers:
  - The domain layer, which contains the main entities and the business logic,
  - The application layer, which contains the services and the application logic, and
  - The infrastructure layer, which implements all the components related to the framework and external dependencies (such as REST API or databases).

The main goal of this separation is to make use of the depedency inversion principle. Within this architecture, infrastructure layer depends of application and domain layer, and application layer depends of domain layer, while domain layer depends on itself.

The implementation of this archicture leads to the following __basic__ directory structure:

```
pass-manager
  ├── application
  │     ├── usecases
  |     ├── services
  |
  ├── domain
  │     ├── model
  |     ├── ports
  │     │     ├── input
  │     │     ├── output
  |
  ├── infrastructure
  │     ├── controllers
  |     ├── adapters
```
Where the domain layer defines the interfaces that will be implemented by the application and infrastructure layers. By declaring the interfaces in the domain layer we are following two of the most important OOP principles: _dependency inversion_ and _program to interface_.

Finally, in this project was also implemented the authentication mechanism using __jwt__. As I've not ever seen some official documentation of how to implement jwt within hexagonal architecture, I think this project has, at least, a great quickstart.

## Installation

Follow these steps to install and run the project:

  1. Clone the repository: `git clone https://github.com/EzeSosa/pass-manager-back.git`
  2. Navigate to the project directory: `cd your-repo`
  3. Build the project using Maven: `mvn clean install`
  4. Run the project: `mvn spring-boot:run`
  5. The project will run at `http://localhost:8080`. You can use either the [front-end page](https://github.com/EzeSosa/pass-manager-front) or Postman to start generating passwords.

Notice that since we are using MySQL, you should create your own database and configure it on [application.yaml](src/main/resources/application.yml).
