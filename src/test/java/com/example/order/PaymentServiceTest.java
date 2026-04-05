package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentServiceTest {

    @Test
    void should_returnTrue_when_cardPaymentIsValid() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act
        boolean result = paymentService.processPayment("CARD", 5000, "1234");

        // Assert
        assertTrue(result);
    }

    @Test
    void should_returnTrue_when_walletPaymentIsWithinLimit() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act
        boolean result = paymentService.processPayment("WALLET", 10000, "WALLET123");

        // Assert
        assertTrue(result);
    }

    @Test
    void should_returnTrue_when_codPaymentIsValid() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act
        boolean result = paymentService.processPayment("COD", 2000, "COD123");

        // Assert
        assertTrue(result);
    }

    @Test
    void should_returnFalse_when_cardReferenceIsTooShort() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act
        boolean result = paymentService.processPayment("CARD", 5000, "12");

        // Assert
        assertFalse(result);
    }

    @Test
    void should_returnFalse_when_walletAmountExceedsLimit() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act
        boolean result = paymentService.processPayment("WALLET", 60000, "WALLET999");

        // Assert
        assertFalse(result);
    }

    @Test
    void should_throwException_when_paymentMethodIsUnsupported() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("BANK", 1000, "ABC123"));
    }

    @Test
    void should_throwException_when_amountIsZero() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("CARD", 0, "1234"));
    }

    @Test
    void should_throwException_when_amountIsNegative() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("CARD", -500, "1234"));
    }

    @Test
    void should_throwException_when_paymentReferenceIsBlank() {
        // Arrange
        PaymentService paymentService = new PaymentService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> paymentService.processPayment("CARD", 1000, " "));
    }
}