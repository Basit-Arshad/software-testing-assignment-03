package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class InventoryServiceTest {

    @Test
    void should_returnTrue_when_stockIsAvailable() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 10);

        boolean available = inventoryService.isAvailable("P101", 5);

        assertTrue(available);
    }

    @Test
    void should_reduceStock_when_reserveStockIsCalled() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 10);

        inventoryService.reserveStock("P101", 4);

        assertEquals(6, inventoryService.getStock("P101"));
    }

    @Test
    void should_throwException_when_stockIsInsufficient() {
        InventoryService inventoryService = new InventoryService();
        inventoryService.addStock("P101", 2);

        assertThrows(IllegalStateException.class, () ->
                inventoryService.reserveStock("P101", 5));
    }
}