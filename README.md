<br />
<p align="center">
  
<h1 align="center">Reading Is Good</h1>

  <p align="center">
    This application aims to take customers book orders and bring 
    them together with their favorite books.
  </p>
</p>

## About The Project

This case is consider; 
* Registering New Customer 
* Placing a new order 
* Tracking the stock of books 
* List all orders of the customer 
* Viewing the order details 
* Query Monthly Statistics 


## Tech Stack
    • Java 11
    • Spring Boot 2.5.2
    • MySQL
    • Lombok
    • Swagger
    • Docker
    • Github

**Additional Information:**

* []()Added Global Exception Handler
* []()Unit tests for business requirements written using the TDD
* []()Added some basic validations like as you can not register via the same email or username again and again. 
* []()Authentication is provided by JWT-Bearer Token. You can get this token by request to the http://localhost:8080/api/auth/signin to the endpoint. After that you can make requests by adding token to the header to the other endpoints.
* []()Added paging when you call query all orders of the customers. 
* []()Database view schema is used to serve customer’s monthly order statistics. 
* []()If 2 or more users tries to buy one last book at the same time, It will be prevented by implemented optimistic locking by using @Version
* []()Dockerized the project. You can find more details the following section.
* []() Provides swagger api docs. You can reach it after successfully run the application.
`The URL`: http://localhost:8080/api/swagger-ui/index.html

<!-- GETTING STARTED -->
## Getting Started

To get a local copy up and running follow these simple steps.

### Installation

1. Clone the project
   ```sh
   git clone https://github.com/ibrahimshn/book-store
   ```
2. Execute the following docker command:
   ```sh
   docker build -t book-store .
   docker-compose up
   ```



<!-- USAGE EXAMPLES -->
## Usage

You can make request by import postman collections where is `book-store.postman_collection.json`




