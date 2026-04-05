package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountServiceTest {

    @Test
    void should_apply10PercentDiscount_when_codeIsSAVE10() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        double result = discountService.applyDiscount(1000, "SAVE10");

        // Assert
        assertEquals(900, result);
    }

    @Test
    void should_apply20PercentDiscount_when_codeIsSAVE20() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        double result = discountService.applyDiscount(1000, "SAVE20");

        // Assert
        assertEquals(800, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsInvalid() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        double result = discountService.applyDiscount(1000, "WRONGCODE");

        // Assert
        assertEquals(1000, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsNull() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        double result = discountService.applyDiscount(1000, null);

        // Assert
        assertEquals(1000, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsBlank() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        double result = discountService.applyDiscount(1000, " ");

        // Assert
        assertEquals(1000, result);
    }

    @Test
    void should_returnZero_when_subtotalIsZero() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        double result = discountService.applyDiscount(0, "SAVE10");

        // Assert
        assertEquals(0, result);
    }

    @Test
    void should_throwException_when_subtotalIsNegative() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> discountService.applyDiscount(-100, "SAVE10"));
    }

    @Test
    void should_returnTrue_when_codeIsValid() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        boolean result = discountService.isValidCode("SAVE10");

        // Assert
        assertTrue(result);
    }

    @Test
    void should_returnFalse_when_codeIsInvalid() {
        // Arrange
        DiscountService discountService = new DiscountService();

        // Act
        boolean result = discountService.isValidCode("INVALID");

        // Assert
        assertFalse(result);
    }
}