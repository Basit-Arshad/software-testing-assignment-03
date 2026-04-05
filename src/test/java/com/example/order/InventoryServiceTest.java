package com.example.order;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("fast")
public class InventoryServiceTest {

    @Test
    void should_addStockSuccessfully_when_validInputIsProvided() {
        InventoryService inventoryService = new InventoryService();

        inventoryService.addStock("P101", 10);

        assertEquals(10, inventoryService.getStock("P101"));
    }

    @Test
    void should_accumulateStock_when_sameProductIsAddedTwice() {
        InventoryService inventoryService = new InventoryService();

        inventoryService.addStock("P101", 5);
        inventoryService.addStock("P101", 3);

        assertEquals(8, inventoryService.getStock("P101"));
    }

    @Test
    void should_returnTrue_when_stockIsAvailable() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 10);

        boolean result = inventoryService.isAvailable("P101", 5);

        assertTrue(result);
    }

    @Test
    void should_returnFalse_when_stockIsInsufficient() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 2);

        boolean result = inventoryService.isAvailable("P101", 5);

        assertFalse(result);
    }

    @Test
    void should_returnTrue_when_quantityEqualsAvailableStock() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 5);

        boolean result = inventoryService.isAvailable("P101", 5);

        assertTrue(result);
    }

    @Test
    void should_reduceStock_when_reserveStockIsCalled() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 10);

        inventoryService.reserveStock("P101", 4);

        assertEquals(6, inventoryService.getStock("P101"));
    }

    @Test
    void should_throwException_when_stockIsInsufficientDuringReservation() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 2);

        assertThrows(IllegalStateException.class,
                () -> inventoryService.reserveStock("P101", 5));
    }

    @Test
    void should_throwException_when_productIdIsBlank() {
        InventoryService inventoryService = new InventoryService();

        assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addStock(" ", 5));
    }

    @Test
    void should_throwException_when_quantityIsNegative() {
        InventoryService inventoryService = new InventoryService();

        assertThrows(IllegalArgumentException.class,
                () -> inventoryService.addStock("P101", -1));
    }
}