package com.example.order;

import java.util.HashMap;
import java.util.Map;

public class DiscountService {
    private final Map<String, Double> discountCodes = new HashMap<>();

    public DiscountService() {
        discountCodes.put("SAVE10", 10.0);
        discountCodes.put("SAVE20", 20.0);
    }

    public double applyDiscount(double subtotal, String code) {
        if (subtotal < 0) {
            throw new IllegalArgumentException("Subtotal cannot be negative");
        }

        if (code == null || code.isBlank()) {
            return subtotal;
        }

        double discountPercent = discountCodes.getOrDefault(code, 0.0);
        return subtotal - (subtotal * discountPercent / 100.0);
    }

    public boolean isValidCode(String code) {
        return code != null && discountCodes.containsKey(code);
    }
}