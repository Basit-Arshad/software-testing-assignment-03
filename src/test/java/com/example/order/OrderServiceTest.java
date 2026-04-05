package com.example.order;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class OrderServiceTest {

    @Test
    void should_placeOrderSuccessfully_when_cartIsValidAndDiscountIsApplied() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P101", "Laptop", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 1);
        inventoryService.addStock("P101", 5);

        // Act
        double result = orderService.placeOrder(cart, "SAVE10", "CARD", "1234");

        // Assert
        assertEquals(900, result);
        assertEquals(4, inventoryService.getStock("P101"));
    }

    @Test
    void should_placeOrderSuccessfully_when_discountCodeIsNull() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P102", "Mouse", 500);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);
        inventoryService.addStock("P102", 5);

        // Act
        double result = orderService.placeOrder(cart, null, "COD", "COD123");

        // Assert
        assertEquals(1000, result);
        assertEquals(3, inventoryService.getStock("P102"));
    }

    @Test
    void should_calculateCorrectTotal_when_multipleItemsExistInCart() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product1 = new Product("P201", "Keyboard", 1000);
        Product product2 = new Product("P202", "Headset", 500);

        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product1, 2); // 2000
        cart.addItem(product2, 2); // 1000

        inventoryService.addStock("P201", 5);
        inventoryService.addStock("P202", 5);

        // Act
        double result = orderService.placeOrder(cart, "SAVE20", "CARD", "5678");

        // Assert
        assertEquals(2400, result);
        assertEquals(3, inventoryService.getStock("P201"));
        assertEquals(3, inventoryService.getStock("P202"));
    }

    @Test
    void should_throwException_when_cartIsNull() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(null, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_cartIsEmpty() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        ShoppingCart cart = new ShoppingCart();

        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(cart, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_productIsOutOfStock() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P301", "Laptop", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);

        inventoryService.addStock("P301", 1);

        // Act & Assert
        assertThrows(IllegalStateException.class,
                () -> orderService.placeOrder(cart, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_paymentFails() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P401", "Tablet", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 1);

        inventoryService.addStock("P401", 5);

        // Act & Assert
        assertThrows(IllegalStateException.class,
                () -> orderService.placeOrder(cart, "SAVE10", "CARD", "12"));
    }

    @Test
    void should_throwException_when_dependenciesAreNull() {
        // Act & Assert
        assertThrows(IllegalArgumentException.class,
                () -> new OrderService(null, new DiscountService(), new PaymentService()));

        assertThrows(IllegalArgumentException.class,
                () -> new OrderService(new InventoryService(), null, new PaymentService()));

        assertThrows(IllegalArgumentException.class,
                () -> new OrderService(new InventoryService(), new DiscountService(), null));
    }

    @Test
    void should_placeOrderSuccessfully_when_requestedQuantityEqualsAvailableStock() {
        // Arrange
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P501", "Monitor", 1500);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);

        inventoryService.addStock("P501", 2);

        // Act
        double result = orderService.placeOrder(cart, "", "COD", "COD999");

        // Assert
        assertEquals(3000, result);
        assertEquals(0, inventoryService.getStock("P501"));
    }
}