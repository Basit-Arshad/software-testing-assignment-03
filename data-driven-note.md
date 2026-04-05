# Data-Driven Testing Note

## Data Sources Used
- CSV file: `src/test/resources/discount-data.csv`
- Excel file: `src/test/resources/order-test-data.xlsx`

## CSV-Based Testing
The CSV file is used in `CsvDiscountParameterizedTest.java`.
It provides parameterized test data for discount-related scenarios.

## Excel-Based Testing
The Excel file is used in `ExcelOrderParameterizedTest.java` through `ExcelDataReader.java`.

The workbook contains four structured sheets:
- Valid
- Invalid
- Edge
- Stress

Each sheet contains at least 5 test cases.

## How the Data Feeds the Test Suite
- `@CsvFileSource` reads rows from the CSV file and injects them into parameterized JUnit tests.
- `ExcelDataReader` reads Excel sheets using Apache POI.
- `@MethodSource` passes the Excel data into `ExcelOrderParameterizedTest`.