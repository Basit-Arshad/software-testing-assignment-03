package com.example.order;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Tag("integration")
public class OrderServiceTest {

    @Test
    void should_placeOrderSuccessfully_when_cartIsValidAndDiscountIsApplied() {
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
        assertEquals(4, inventoryService.getStock("P101"));
    }

    @Test
    void should_placeOrderSuccessfully_when_discountCodeIsNull() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P102", "Mouse", 500);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);
        inventoryService.addStock("P102", 5);

        double result = orderService.placeOrder(cart, null, "COD", "COD123");

        assertEquals(1000, result);
        assertEquals(3, inventoryService.getStock("P102"));
    }

    @Test
    void should_calculateCorrectTotal_when_multipleItemsExistInCart() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product1 = new Product("P201", "Keyboard", 1000);
        Product product2 = new Product("P202", "Headset", 500);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product1, 2);
        cart.addItem(product2, 2);
        inventoryService.addStock("P201", 5);
        inventoryService.addStock("P202", 5);

        double result = orderService.placeOrder(cart, "SAVE20", "CARD", "5678");

        assertEquals(2400, result);
        assertEquals(3, inventoryService.getStock("P201"));
        assertEquals(3, inventoryService.getStock("P202"));
    }

    @Test
    void should_throwException_when_cartIsNull() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(null, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_cartIsEmpty() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);
        ShoppingCart cart = new ShoppingCart();

        assertThrows(IllegalArgumentException.class,
                () -> orderService.placeOrder(cart, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_productIsOutOfStock() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P301", "Laptop", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);
        inventoryService.addStock("P301", 1);

        assertThrows(IllegalStateException.class,
                () -> orderService.placeOrder(cart, "SAVE10", "CARD", "1234"));
    }

    @Test
    void should_throwException_when_paymentFails() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P401", "Tablet", 1000);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 1);
        inventoryService.addStock("P401", 5);

        assertThrows(IllegalStateException.class,
                () -> orderService.placeOrder(cart, "SAVE10", "CARD", "12"));
    }

    @Test
    void should_throwException_when_dependenciesAreNull() {
        assertThrows(IllegalArgumentException.class,
                () -> new OrderService(null, new DiscountService(), new PaymentService()));
        assertThrows(IllegalArgumentException.class,
                () -> new OrderService(new InventoryService(), null, new PaymentService()));
        assertThrows(IllegalArgumentException.class,
                () -> new OrderService(new InventoryService(), new DiscountService(), null));
    }

    @Test
    void should_placeOrderSuccessfully_when_requestedQuantityEqualsAvailableStock() {
        InventoryService inventoryService = new InventoryService();
        DiscountService discountService = new DiscountService();
        PaymentService paymentService = new PaymentService();
        OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

        Product product = new Product("P501", "Monitor", 1500);
        ShoppingCart cart = new ShoppingCart();
        cart.addItem(product, 2);
        inventoryService.addStock("P501", 2);

        double result = orderService.placeOrder(cart, "", "COD", "COD999");

        assertEquals(3000, result);
        assertEquals(0, inventoryService.getStock("P501"));
    }
}