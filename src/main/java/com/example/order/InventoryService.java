package com.example.order;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {
    private final Map<String, Integer> stock = new HashMap<>();

    public void addStock(String productId, int quantity) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be null or blank");
        }
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }

        stock.put(productId, stock.getOrDefault(productId, 0) + quantity);
    }

    public boolean isAvailable(String productId, int quantity) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be null or blank");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        return stock.getOrDefault(productId, 0) >= quantity;
    }

    public void reserveStock(String productId, int quantity) {
        if (!isAvailable(productId, quantity)) {
            throw new IllegalStateException("Insufficient stock");
        }

        stock.put(productId, stock.get(productId) - quantity);
    }

    public int getStock(String productId) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("Product id cannot be null or blank");
        }
        return stock.getOrDefault(productId, 0);
    }
}