# Bank Customer Management System

This project implements a customer management system for a bank, allowing CRUD operations for customers and basic account management functionalities.

## Project Status

🚧 **Under Development** 🚧

This project is currently under active development. New features, enhancements, and bug fixes are regularly being added.

## Features

- **Customer Management:**
    - Create, update, retrieve, and delete customer details.
    - Each customer can have a unique email and phone number.

- **Account Management:**
    - Create bank accounts associated with customers.
    - Perform operations such as adding balance, withdrawing balance, and transferring money between accounts.

## Technologies Used

- Java
- Spring Framework (Spring Boot, Spring Data JPA)
- Hibernate
- MapStruct (for DTO mapping)
- PostgreSQL

## Modules

### `customer-service`

- Contains business logic and service implementations for customer operations.
- Uses `CustomerEntity`, `CustomerRepository`, and `CustomerMapper`.

### `bank-service`

- Manages bank account operations.
- Uses `AccountEntity`, `AccountRepository`, and `FinanceHelper` for IBAN generation.

### `common-utils`

- Contains utility classes like `ApiResponse`, `ApiResponseHelper`, and exceptions (`NotFoundIdException`).

### `controllers`

- HTTP request handling using Spring MVC for customers (`CustomerController`) and bank accounts (`AccountController`).

## API Endpoints

### Customers

- **POST /customer**: Create a new customer.
- **PUT /customer**: Update an existing customer.
- **GET /customer/{id}**: Retrieve customer details by ID.
- **GET /customer**: Retrieve all customers.

### Accounts

- **POST /account**: Create a new bank account.
- **GET /account**: Retrieve all bank accounts.
- **PUT /account/add-balance**: Add balance to a bank account.
- **PUT /account/withdraw**: Withdraw balance from a bank account.
- **PUT /account/transfer**: Transfer money between bank accounts.


