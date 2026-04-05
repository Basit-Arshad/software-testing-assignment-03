package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    @Test
    void should_addStockSuccessfully_when_validInputIsProvided() {
        // Arrange
        InventoryService inventoryService = new InventoryService();

        // Act
        inventoryService.addStock("P101", 10);

        // Assert
        assertEquals(10, inventoryService.getStock("P101"));
    }

    @Test
    void should_accumulateStock_when_sameProductIsAddedTwice() {
        // Arrange
        InventoryService inventoryService = new InventoryService();

        // Act
        inventoryService.addStock("P101", 5);
        inventoryService.addStock("P101", 3);

        // Assert
        assertEquals(8, inventoryService.getStock("P101"));
    }

    @Test
    void should_returnTrue_when_stockIsAvailable() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 10);

        // Act
        boolean result = inventoryService.isAvailable("P101", 5);

        // Assert
        assertTrue(result);
    }

    @Test
    void should_returnFalse_when_stockIsInsufficient() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 2);

        // Act
        boolean result = inventoryService.isAvailable("P101", 5);

        // Assert
        assertFalse(result);
    }

    @Test
    void should_returnTrue_when_quantityEqualsAvailableStock() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 5);

        // Act
        boolean result = inventoryService.isAvailable("P101", 5);

        // Assert
        assertTrue(result);
    }

    @Test
    void should_reduceStock_when_reserveStockIsCalled() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 10);

        // Act
        inventoryService.reserveStock("P101", 4);

        // Assert
        assertEquals(6, inventoryService.getStock("P101"));
    }

    @Test
    void should_throwException_when_stockIsInsufficientDuringReservation() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 2);

        // Act & Assert
        assertThrows(IllegalStateException.class,
                () -> inventoryService.reserveStock("P101", 5));
    }

    @Test
    void should_throwException_when_productIdIsBlank() {
        // Arrange
        InventoryService inventoryService = new InventoryService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addStock(" ", 5));
    }

    @Test
    void should_throwException_when_quantityIsNegative() {
        // Arrange
        InventoryService inventoryService = new InventoryService();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addStock("P101", -1));
    }
}