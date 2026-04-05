package com.example.order;

import org.apache.poi.ss.usermodel.*;
import org.junit.jupiter.params.provider.Arguments;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class ExcelDataReader {

    private static final String FILE_NAME = "/order-test-data.xlsx";

    public static Stream<Arguments> validOrders() {
        return readSheet("Valid");
    }

    public static Stream<Arguments> invalidOrders() {
        return readSheet("Invalid");
    }

    public static Stream<Arguments> edgeOrders() {
        return readSheet("Edge");
    }

    public static Stream<Arguments> stressOrders() {
        return readSheet("Stress");
    }

    private static Stream<Arguments> readSheet(String sheetName) {
        List<Arguments> data = new ArrayList<>();

        try (InputStream inputStream = ExcelDataReader.class.getResourceAsStream(FILE_NAME)) {
            if (inputStream == null) {
                throw new RuntimeException("Excel file not found: " + FILE_NAME);
            }

            Workbook workbook = WorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                workbook.close();
                throw new RuntimeException("Sheet not found: " + sheetName);
            }

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);
                if (row == null || isRowEmpty(row)) {
                    continue;
                }

                data.add(Arguments.of(
                        getStringCellValue(row.getCell(0)),  // productId
                        getStringCellValue(row.getCell(1)),  // productName
                        getDoubleCellValue(row.getCell(2)),  // price
                        getIntCellValue(row.getCell(3)),     // quantity
                        getIntCellValue(row.getCell(4)),     // availableStock
                        getStringCellValue(row.getCell(5)),  // discountCode
                        getStringCellValue(row.getCell(6)),  // paymentMethod
                        getStringCellValue(row.getCell(7)),  // paymentReference
                        getStringCellValue(row.getCell(8)),  // expectedResult
                        getDoubleCellValue(row.getCell(9))   // expectedFinalAmount
                ));
            }

            workbook.close();
            return data.stream();

        } catch (Exception e) {
            throw new RuntimeException("Failed to read Excel sheet: " + sheetName, e);
        }
    }

    private static boolean isRowEmpty(Row row) {
        for (int i = 0; i < 10; i++) {
            Cell cell = row.getCell(i);
            if (cell != null && !getStringCellValue(cell).isBlank()) {
                return false;
            }
        }
        return true;
    }

    private static String getStringCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        DataFormatter formatter = new DataFormatter();
        return formatter.formatCellValue(cell).trim();
    }

    private static double getDoubleCellValue(Cell cell) {
        String value = getStringCellValue(cell);
        if (value.isBlank()) {
            return 0.0;
        }
        value = value.replace(",", "");
        return Double.parseDouble(value);
    }

    private static int getIntCellValue(Cell cell) {
        return (int) getDoubleCellValue(cell);
    }
}