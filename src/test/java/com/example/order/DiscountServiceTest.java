package com.example.order;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("fast")
public class DiscountServiceTest {

    @Test
    void should_apply10PercentDiscount_when_codeIsSAVE10() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, "SAVE10");

        assertEquals(900, result);
    }

    @Test
    void should_apply20PercentDiscount_when_codeIsSAVE20() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, "SAVE20");

        assertEquals(800, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsInvalid() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, "WRONGCODE");

        assertEquals(1000, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsNull() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, null);

        assertEquals(1000, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsBlank() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, " ");

        assertEquals(1000, result);
    }

    @Test
    void should_returnZero_when_subtotalIsZero() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(0, "SAVE10");

        assertEquals(0, result);
    }

    @Test
    void should_throwException_when_subtotalIsNegative() {
        DiscountService discountService = new DiscountService();

        assertThrows(IllegalArgumentException.class,
                () -> discountService.applyDiscount(-100, "SAVE10"));
    }

    @Test
    void should_returnTrue_when_codeIsValid() {
        DiscountService discountService = new DiscountService();

        boolean result = discountService.isValidCode("SAVE10");

        assertTrue(result);
    }

    @Test
    void should_returnFalse_when_codeIsInvalid() {
        DiscountService discountService = new DiscountService();

        boolean result = discountService.isValidCode("INVALID");

        assertFalse(result);
    }
}