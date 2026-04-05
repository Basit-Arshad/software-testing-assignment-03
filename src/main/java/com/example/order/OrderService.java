package com.example.order;

public class OrderService {
    private final InventoryService inventoryService;
    private final DiscountService discountService;
    private final PaymentService paymentService;

    public OrderService(InventoryService inventoryService,
                        DiscountService discountService,
                        PaymentService paymentService) {
        if (inventoryService == null || discountService == null || paymentService == null) {
            throw new IllegalArgumentException("Dependencies cannot be null");
        }

        this.inventoryService = inventoryService;
        this.discountService = discountService;
        this.paymentService = paymentService;
    }

    public double placeOrder(ShoppingCart cart, String discountCode, String paymentMethod, String paymentReference) {
        if (cart == null) {
            throw new IllegalArgumentException("Cart cannot be null");
        }
        if (cart.isEmpty()) {
            throw new IllegalArgumentException("Cart cannot be empty");
        }

        for (CartItem item : cart.getItems()) {
            if (!inventoryService.isAvailable(item.getProduct().getId(), item.getQuantity())) {
                throw new IllegalStateException("Product out of stock: " + item.getProduct().getName());
            }
        }

        for (CartItem item : cart.getItems()) {
            inventoryService.reserveStock(item.getProduct().getId(), item.getQuantity());
        }

        double subtotal = cart.getSubtotal();
        double finalAmount = discountService.applyDiscount(subtotal, discountCode);

        boolean paymentSuccess = paymentService.processPayment(paymentMethod, finalAmount, paymentReference);
        if (!paymentSuccess) {
            throw new IllegalStateException("Payment failed");
        }

        return finalAmount;
    }
}