# Data-Driven Testing Note

## Data Sources Used
- CSV file: `src/test/resources/discount-data.csv`
- Excel file: `src/test/resources/order-test-data.xlsx`

## CSV-Based Testing
The CSV file is used in `CsvDiscountParameterizedTest.java`.
It provides parameterized input for discount-related scenarios.

## Excel-Based Testing
The Excel file is used in `ExcelOrderParameterizedTest.java` through `ExcelDataReader.java`.
The workbook contains four sheets:
- Valid
- Invalid
- Edge
- Stress

Each sheet contains at least 5 test cases.

## How Data Feeds the Test Suite
- `@CsvFileSource` reads rows from the CSV file and feeds them into JUnit parameterized tests.
- `ExcelDataReader` reads Excel sheets using Apache POI and supplies data to `ExcelOrderParameterizedTest` through `@MethodSource`.