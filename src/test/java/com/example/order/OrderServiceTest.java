package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @Test
    void should_placeOrderSuccessfully_when_cartIsValid() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P101", "Laptop", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 1);

        inventoryService.addStock("P101", 5);

        double result = orderService.placeOrder(cart, "SAVE10", "CARD", "1234");

        assertEquals(900, result);
    }

    @Test
    void should_throwException_when_cartIsEmpty() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        ShoppingCart cart = new ShoppingCart();

        assertThrows(IllegalArgumentException.class, () ->
                orderService.placeOrder(cart, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_productIsOutOfStock() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P101", "Laptop", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);

        inventoryService.addStock("P101", 1);

        assertThrows(IllegalStateException.class, () ->
                orderService.placeOrder(cart, "SAVE10", "CARD", "1234"));
    }
}