# Recipe-Management-System
This repository contains the source code for a Recipe Management System application. The application allows users to manage and share recipes, add comments, and authenticate themselves using an authentication token. It provides various controllers for handling different functionalities.

## Frameworks and Language used
* Java
* Maven 
* SpringBoot 
* MySQL

## Required Dependencies
To run this project, you will need to add the following dependencies to your pom.xml file

* Spring Web
* Spring Boot
* Dev Tool
* Lombok
* Validation
* Spring Data JPA
* MySql Driver
* Swagger-UI

## Entities
The following entities are used in the application:
* User: Represents a user of the application.
* Recipe: Represents a recipe posted by a user.
* Comment: Represents a comment made by a user on a recipe.
* AuthenticationToken: Represents an authentication token used to authenticate users.

## Database Configuration
To connect to a MySQL database, update the application.properties file with the appropriate database URL, username, and password. The following properties need to be updated:

* spring.datasource.url = jdbc:mysql://<>:3306/DataBaseName
* spring.datasource.username = <userName>
* spring.datasource.password = <password>
* spring.jpa.show-sql = true
* spring.jpa.hibernate.ddl-auto = update

* spring.jpa.properties.hibernate.show_sql=true
* spring.jpa.properties.hibernate.use_sql_comments=true
* spring.jpa.properties.hibernate.format_sql=true

## Controllers

The application contains several controllers to handle different operations and manage the flow of data. The controllers and their associated methods are as follows:
* **signUp** : Allows a new user to sign up by providing their details.
* **signIn** : Authenticates a user by validating their credentials.
* **addRecipe**: Enables a user to add a new recipe.
* **editRecipe** : Allows a user to update an existing recipe.
* **findMyRecipes**: Retrieves all recipes posted by a specific user.
* **deleteRecipe**: Deletes a recipe, only if the user is the owner.
* **addComment**: Allows a user to add a comment to a recipe.
* **deleteComment**: Allows the owner of a recipe to delete any comments associated with that recipe.

## Data Flow
1. The user at client side sends a request to the application through the API endpoints.
2. The API receives the request and sends it to the appropriate controller method.
3. The controller method makes a call to the method in service class.
4. The method in service class builds logic and retrieves or modifies data from the database, which is in turn given to controller class
5. The controller method returns a response to the API.
6. The API sends the response back to the user.

## DataBase Used
SQL database
  
- We have used Persistent database to implement CRUD Operations.

## Deployed
AWS Ec2 Instance
-We have used two Ec2 instance for Both Spring Boot Application and Mysql Database 

## Project Summary
The Recipe Management System is a web application that allows users to manage and share recipes. Users can sign up, sign in, and sign out, and have the ability to add, edit, and delete their recipes. They can also add comments to recipes and like recipes posted by other users. The application provides authentication using an authentication token and includes controllers for user management, recipe management, and comment handling.
