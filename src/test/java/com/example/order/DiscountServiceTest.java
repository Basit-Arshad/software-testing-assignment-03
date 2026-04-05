package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DiscountServiceTest {

    @Test
    void should_apply10PercentDiscount_when_codeIsSAVE10() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, "SAVE10");

        assertEquals(900, result);
    }

    @Test
    void should_returnSameAmount_when_codeIsInvalid() {
        DiscountService discountService = new DiscountService();

        double result = discountService.applyDiscount(1000, "WRONGCODE");

        assertEquals(1000, result);
    }

    @Test
    void should_throwException_when_subtotalIsNegative() {
        DiscountService discountService = new DiscountService();

        assertThrows(IllegalArgumentException.class, () ->
                discountService.applyDiscount(-100, "SAVE10"));
    }
}