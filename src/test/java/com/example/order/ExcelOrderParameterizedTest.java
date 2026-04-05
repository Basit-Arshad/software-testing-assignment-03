package com.example.order;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.*;

public class ExcelOrderParameterizedTest {

    @ParameterizedTest(name = "[Valid] {0} - expected={8}")
    @MethodSource("com.example.order.ExcelDataReader#validOrders")
    void should_processValidSheetData_when_excelInputIsProvided(String productId,
                                                                String productName,
                                                                double price,
                                                                int quantity,
                                                                int availableStock,
                                                                String discountCode,
                                                                String paymentMethod,
                                                                String paymentReference,
                                                                String expectedResult,
                                                                double expectedFinalAmount) {
        executeOrderScenario(productId, productName, price, quantity, availableStock,
                discountCode, paymentMethod, paymentReference, expectedResult, expectedFinalAmount);
    }

    @ParameterizedTest(name = "[Invalid] {0} - expected={8}")
    @MethodSource("com.example.order.ExcelDataReader#invalidOrders")
    void should_processInvalidSheetData_when_excelInputIsProvided(String productId,
                                                                  String productName,
                                                                  double price,
                                                                  int quantity,
                                                                  int availableStock,
                                                                  String discountCode,
                                                                  String paymentMethod,
                                                                  String paymentReference,
                                                                  String expectedResult,
                                                                  double expectedFinalAmount) {
        executeOrderScenario(productId, productName, price, quantity, availableStock,
                discountCode, paymentMethod, paymentReference, expectedResult, expectedFinalAmount);
    }

    @ParameterizedTest(name = "[Edge] {0} - expected={8}")
    @MethodSource("com.example.order.ExcelDataReader#edgeOrders")
    void should_processEdgeSheetData_when_excelInputIsProvided(String productId,
                                                               String productName,
                                                               double price,
                                                               int quantity,
                                                               int availableStock,
                                                               String discountCode,
                                                               String paymentMethod,
                                                               String paymentReference,
                                                               String expectedResult,
                                                               double expectedFinalAmount) {
        executeOrderScenario(productId, productName, price, quantity, availableStock,
                discountCode, paymentMethod, paymentReference, expectedResult, expectedFinalAmount);
    }

    @ParameterizedTest(name = "[Stress] {0} - expected={8}")
    @MethodSource("com.example.order.ExcelDataReader#stressOrders")
    void should_processStressSheetData_when_excelInputIsProvided(String productId,
                                                                 String productName,
                                                                 double price,
                                                                 int quantity,
                                                                 int availableStock,
                                                                 String discountCode,
                                                                 String paymentMethod,
                                                                 String paymentReference,
                                                                 String expectedResult,
                                                                 double expectedFinalAmount) {
        executeOrderScenario(productId, productName, price, quantity, availableStock,
                discountCode, paymentMethod, paymentReference, expectedResult, expectedFinalAmount);
    }

    private void executeOrderScenario(String productId,
                                      String productName,
                                      double price,
                                      int quantity,
                                      int availableStock,
                                      String discountCode,
                                      String paymentMethod,
                                      String paymentReference,
                                      String expectedResult,
                                      double expectedFinalAmount) {

        if ("SUCCESS".equalsIgnoreCase(expectedResult)) {
            // Arrange
            InventoryService inventoryService = new InventoryService();
            DiscountService discountService = new DiscountService();
            PaymentService paymentService = new PaymentService();
            OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

            Product product = new Product(productId, productName, price);
            ShoppingCart cart = new ShoppingCart();
            cart.addItem(product, quantity);
            inventoryService.addStock(productId, availableStock);

            // Act
            double result = orderService.placeOrder(
                    cart,
                    normalizeValue(discountCode),
                    normalizeValue(paymentMethod),
                    normalizeValue(paymentReference)
            );

            // Assert
            assertEquals(expectedFinalAmount, result, 0.001);
        } else if ("EXCEPTION".equalsIgnoreCase(expectedResult)) {
            // Arrange + Act + Assert
            assertThrows(Exception.class, () -> {
                InventoryService inventoryService = new InventoryService();
                DiscountService discountService = new DiscountService();
                PaymentService paymentService = new PaymentService();
                OrderService orderService = new OrderService(inventoryService, discountService, paymentService);

                Product product = new Product(productId, productName, price);
                ShoppingCart cart = new ShoppingCart();
                cart.addItem(product, quantity);
                inventoryService.addStock(productId, availableStock);

                orderService.placeOrder(
                        cart,
                        normalizeValue(discountCode),
                        normalizeValue(paymentMethod),
                        normalizeValue(paymentReference)
                );
            });
        } else {
            fail("Unsupported expectedResult value: " + expectedResult);
        }
    }

    private String normalizeValue(String value) {
        return value == null ? null : value.trim();
    }
}