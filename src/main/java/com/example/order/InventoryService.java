package com.example.order;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    public void addStock(String productId, int quantity) {
        validateProductId(productId);
        validateQuantity(quantity);

        stock.put(productId, stock.getOrDefault(productId, 0) + quantity);
    }

    public int getStock(String productId) {
        validateProductId(productId);
        return stock.getOrDefault(productId, 0);
    }

    public boolean isAvailable(String productId, int quantity) {
        validateProductId(productId);
        validateQuantity(quantity);

        return getStock(productId) >= quantity;
    }

    public void reserveStock(String productId, int quantity) {
        validateProductId(productId);
        validateQuantity(quantity);

        if (!isAvailable(productId, quantity)) {
            throw new IllegalStateException("Insufficient stock for product: " + productId);
        }

        stock.put(productId, stock.get(productId) - quantity);
    }

    private void validateProductId(String productId) {
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be null or blank");
        }
    }

    private void validateQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative");
        }
    }
}