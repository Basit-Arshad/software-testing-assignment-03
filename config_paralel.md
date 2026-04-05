# Task 4 - Test Configuration, Parallel Execution & Suites

## Overview
This task demonstrates advanced JUnit 5 test configuration for the custom Java-based order processing system.  
The implementation includes:

- Parallel test execution
- Test grouping using tags
- Custom test suites
- Ordered test execution
- Parameterized tests
- Running individual test groups and full suite execution

## Parallel Execution Configuration
Parallel execution was enabled using JUnit 5 configuration in:

`src/test/resources/junit-platform.properties`

### Configuration Used
- Parallel execution enabled
- Concurrent execution for test methods
- Concurrent execution for test classes

This allows multiple test classes to run simultaneously and improves execution efficiency.

## Test Grouping with Tags
Tests were organized into the following groups using JUnit 5 tags:

- `@Tag("fast")`  
  Used for lightweight unit tests with simple logic  
  Examples:
    - `DiscountServiceTest`
    - `InventoryServiceTest`
    - `PaymentServiceTest`

- `@Tag("slow")`  
  Used for data-driven and parameterized tests  
  Examples:
    - `CsvDiscountParameterizedTest`
    - `ExcelOrderParameterizedTest`

- `@Tag("integration")`  
  Used for tests involving interaction between multiple services  
  Example:
    - `OrderServiceTest`

## Ordered Test Execution
Ordered execution was demonstrated using:

- `@TestMethodOrder(MethodOrderer.OrderAnnotation.class)`
- `@Order(n)`

This was implemented in `DiscountServiceTest` to control execution order of selected test methods.

## Parameterized Testing
Parameterized testing is also part of Task 4 and was already implemented in the following files:

- `CsvDiscountParameterizedTest.java`
- `ExcelOrderParameterizedTest.java`

These tests use external data sources and demonstrate reusable test execution with multiple datasets.

## Custom Test Suites
The following custom suite classes were created:

- `FastTestSuite`
- `SlowTestSuite`
- `IntegrationTestSuite`
- `AllTestsSuite`

### Purpose of Each Suite
- `FastTestSuite` runs all tests tagged as `fast`
- `SlowTestSuite` runs all tests tagged as `slow`
- `IntegrationTestSuite` runs all tests tagged as `integration`
- `AllTestsSuite` runs all available test classes

## How to Run the Tests

### Run Full Test Set
```bash
mvn test