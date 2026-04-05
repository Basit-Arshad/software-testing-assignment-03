package com.example.order;

public class PaymentService {

    public boolean processPayment(String paymentMethod, double amount, String reference) {
        if (paymentMethod == null || paymentMethod.isBlank()) {
            throw new IllegalArgumentException("Payment method cannot be null or blank");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be greater than zero");
        }
        if (reference == null || reference.isBlank()) {
            throw new IllegalArgumentException("Payment reference cannot be null or blank");
        }

        if (!paymentMethod.equalsIgnoreCase("CARD")
                && !paymentMethod.equalsIgnoreCase("WALLET")
                && !paymentMethod.equalsIgnoreCase("COD")) {
            throw new IllegalArgumentException("Unsupported payment method");
        }

        if (paymentMethod.equalsIgnoreCase("CARD") && reference.length() < 4) {
            return false;
        }

        if (paymentMethod.equalsIgnoreCase("WALLET") && amount > 50000) {
            return false;
        }

        return true;
    }
}