# Transaction Microservice

## Overview
This is a Spring Boot microservice that manages financial transactions. It provides RESTful APIs to create, retrieve, update, and delete transactions using a relational database.

## Features
- CRUD operations for transactions
- Spring Boot with Spring Data JPA
- H2 in-memory database
- Unit testing with JUnit and Spring Boot Test
- REST API with JSON support

## Technologies Used
- Java 21
- Spring Boot 3.2.0
- Spring Web
- Spring Data JPA
- H2 Database
- JUnit & Spring Boot Test
- Maven

## Project Structure
```
TransactionMicroService/
│── src/main/java/com/example/microservice/
│   ├── controller/TransactionController.java
│   ├── service/TransactionService.java
│   ├── model/Transaction.java
│   ├── repository/TransactionRepository.java
│   ├── exception/TransactionNotFoundException.java
│── src/test/java/com/example/microservice/
│   ├── service/TransactionServiceTests.java
│── pom.xml
```

## Installation
Ensure you have **Java 21** and **Maven** installed.

Clone the repository:
```sh
git clone https://github.com/your-repo/TransactionMicroService.git
cd TransactionMicroService
```

Build the project:
```sh
mvn clean package
```

## Running the Application
Run the Spring Boot application:
```sh
mvn spring-boot:run
```

## API Endpoints

### Create a Transaction
```http
POST /transactions
```
**Request Body:**
```json
{
  "id": "1",
  "description": "Test Transaction",
  "amount": 100.50
}
```
**Response:**
```json
{
  "id": "1",
  "description": "Test Transaction",
  "amount": 100.50
}
```

### Retrieve All Transactions
```http
GET /transactions
```

### Retrieve a Transaction by ID
```http
GET /transactions/{id}
```

### Update a Transaction
```http
PUT /transactions/{id}
```
**Request Body:**
```json
{
  "id": "1",
  "description": "Updated Transaction",
  "amount": 200.00
}
```

### Delete a Transaction
```http
DELETE /transactions/{id}
```

## Running Tests
To run unit tests:
```sh
mvn test
```


