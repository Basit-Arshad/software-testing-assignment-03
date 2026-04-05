package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    void should_returnTrue_when_cardPaymentIsValid() {
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.processPayment("CARD", 5000, "1234");

        assertTrue(result);
    }

    @Test
    void should_returnFalse_when_cardReferenceIsTooShort() {
        PaymentService paymentService = new PaymentService();

        boolean result = paymentService.processPayment("CARD", 5000, "12");

        assertFalse(result);
    }

    @Test
    void should_throwException_when_paymentMethodIsUnsupported() {
        PaymentService paymentService = new PaymentService();

        assertThrows(IllegalArgumentException.class, () ->
                paymentService.processPayment("BANK", 1000, "ABC123"));
    }
}