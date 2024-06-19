# Bank Application

Bank Application is a Java Spring Boot project focused on customer and account management, designed to simulate basic banking operations.

## Project Description

Bank Application provides an API for managing customer information, opening accounts, and performing account transactions. It also includes user authentication and authorization for security.

## Technologies Used

The project utilizes the following technologies:

- **Java 22**
- **Spring Boot**
- **Spring Data JPA**
- **Spring Security**
- **Spring MVC**
- **MapStruct**
- **PostgreSQL**
- **Docker**


## Getting Started

To run the project, follow these steps:

1. **Install PostgreSQL and Docker**: Ensure PostgreSQL is installed and Docker is running.

2. **Configure Database**: Set up PostgreSQL connection settings in `application.properties` or `application.yml`. For example:

    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/db
        username: postgres
        password: 1511
        driver-class-name: org.postgresql.Driver
    ```

3. **Run the Application**:

    ```bash
    cd Bank
    mvn spring-boot:run
    ```

4. **Access the Application**: By default, the application can be accessed at `http://localhost:8080`.

## Note
This project is still under development.


## Demo images

## Main Page

<img src="https://cdn.discordapp.com/attachments/870962319625191428/1251766360925667338/image.png?ex=667268c5&is=66711745&hm=e5a8c3d9f80f3817ce9b0de6373f3fcc4eb9856e42c30250c4b6cb67be7c9c6a&">

## Login Page
<img src="https://cdn.discordapp.com/attachments/870962319625191428/1251766400184356945/image.png?ex=666fc5ce&is=666e744e&hm=ba5dc4b5400de2dd25431eb83e5731c50df861738e24214e0dce328e9cfc26d3&">

## Register Page
<img  src="https://cdn.discordapp.com/attachments/870962319625191428/1251766451337953300/image.png?ex=666fc5da&is=666e745a&hm=98c0502859ab1a8dc496ca767a119a4b5b038fbfd224c3d8bb276420201fa8f2&">

## Account Info
<img src="https://cdn.discordapp.com/attachments/870962319625191428/1252859653474222182/image.png?ex=6673bffa&is=66726e7a&hm=60f93ca68ca0d4455c9d50f5649fb1dc3549ba1caab6c0a13eb872c3c8c0952a&">

## Money Transfer
<img src="https://cdn.discordapp.com/attachments/870962319625191428/1252859711712133211/image.png?ex=6673c008&is=66726e88&hm=464c56291bf816104a6096c1e86db18556d079092d52685494cfcd5f1b9cb1da&">

