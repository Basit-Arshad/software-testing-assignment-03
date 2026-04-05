# JUnit 5 Assignment 03 - Software Testing

## Overview

This project is a custom Java-based order processing system developed for a software testing assignment using **JUnit 5** and **Maven**. The main purpose of this project is not only to implement core order-processing business logic, but also to apply different software testing techniques including:

- Unit testing
- Test architecture and strategy design
- Data-driven testing
- Test grouping and configuration
- Parallel execution
- Ordered test execution
- Code coverage and reporting

The system simulates a simple order workflow involving products, shopping cart operations, stock validation, discount handling, payment processing, and final order placement.

---

## Project Objectives

The project was built to demonstrate practical testing concepts in Java, including:

- Writing structured and meaningful unit tests
- Following the AAA pattern (Arrange, Act, Assert)
- Covering positive, negative, boundary, and exception scenarios
- Using parameterized testing with CSV and Excel files
- Organizing tests with tags
- Running grouped tests separately
- Enabling parallel execution
- Measuring code coverage using JaCoCo
- Achieving at least 70% line and branch coverage

---

## Main Business Classes

The following core classes were implemented in the main application:

- `Product`
- `CartItem`
- `ShoppingCart`
- `InventoryService`
- `DiscountService`
- `PaymentService`
- `OrderService`

These classes together represent the business flow of adding items to a cart, checking stock, applying discounts, processing payments, and placing orders.

---

## Technologies Used

- **Java 17**
- **Maven**
- **JUnit 5**
- **Apache POI**
- **JaCoCo**

---

## Project Structure

```text
junit5-assignment_3/
├── pom.xml
├── README.md
├── setup-note.md
├── test-strategy.md
├── data-driven-note.md
├── task4-note.md
├── task5-note.md
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/example/order/
│   │           ├── Product.java
│   │           ├── CartItem.java
│   │           ├── ShoppingCart.java
│   │           ├── InventoryService.java
│   │           ├── DiscountService.java
│   │           ├── PaymentService.java
│   │           └── OrderService.java
│   └── test/
│       ├── java/
│       │   └── com/example/order/
│       │       ├── DiscountServiceTest.java
│       │       ├── InventoryServiceTest.java
│       │       ├── PaymentServiceTest.java
│       │       ├── OrderServiceTest.java
│       │       ├── CsvDiscountParameterizedTest.java
│       │       ├── ExcelOrderParameterizedTest.java
│       │       ├── ExcelDataReader.java
│       │       ├── TestOrderingDemoTest.java
│       │       └── suite/
│       │           ├── FastTestSuite.java
│       │           ├── SlowTestSuite.java
│       │           ├── IntegrationTestSuite.java
│       │           └── FullTestSuite.java
│       └── resources/
│           ├── discount-data.csv
│           ├── order-test-data.xlsx
│           └── junit-platform.properties
