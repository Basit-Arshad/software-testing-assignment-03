package com.example.order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.api.Tag;
import static org.junit.jupiter.api.Assertions.assertEquals;
@Tag("slow")
public class CsvDiscountParameterizedTest {

    @ParameterizedTest(name = "[{0}] subtotal={1}, code={2}, expected={3}")
    @CsvFileSource(resources = "/discount-data.csv", numLinesToSkip = 1)
    void should_applyDiscountCorrectly_when_csvDataIsProvided(String category,
                                                              double subtotal,
                                                              String codeToken,
                                                              double expectedTotal) {
        // Arrange
        DiscountService discountService = new DiscountService();
        String code = normalizeCode(codeToken);

        // Act
        double result = discountService.applyDiscount(subtotal, code);

        // Assert
        assertEquals(expectedTotal, result, 0.001,
                "Failed for category: " + category + ", code: " + codeToken);
    }

    private String normalizeCode(String codeToken) {
        if (codeToken == null) {
            return null;
        }
        if ("NULL".equalsIgnoreCase(codeToken)) {
            return null;
        }
        if ("BLANK".equalsIgnoreCase(codeToken)) {
            return " ";
        }
        return codeToken;
    }
}