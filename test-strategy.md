# Test Strategy Document

## Objective
The purpose of this testing effort is to verify the correctness, reliability, and maintainability of the custom Java-based order processing system using JUnit 5.

## Selected Classes
The following classes were selected for detailed unit testing because they contain meaningful business logic:
- InventoryService
- DiscountService
- PaymentService
- OrderService

## Functional Areas

### InventoryService
- Add stock
- Check stock availability
- Reserve stock
- Retrieve current stock

### DiscountService
- Apply valid discount codes
- Handle invalid, null, and blank discount codes
- Validate discount code existence

### PaymentService
- Process payments using CARD, WALLET, and COD
- Validate payment input values
- Handle unsupported methods
- Return false for specific failed payment conditions

### OrderService
- Validate cart before placing order
- Check product stock
- Reserve stock
- Apply discounts
- Process payment
- Return final payable amount

## Risk Zones
- Invalid product IDs
- Negative subtotal or quantity
- Empty cart
- Insufficient stock
- Invalid payment method
- Payment failure after stock reservation
- Incorrect discount calculation

## Edge Cases
- Quantity equal to available stock
- Zero subtotal
- Blank discount code
- Wallet payment above threshold
- Card reference below minimum length
- Null cart and null dependencies

## Test Design Approach
All tests follow the AAA pattern:
- Arrange
- Act
- Assert

## Naming Convention
The naming convention used is:
should_<expectedBehavior>_when_<condition>

## Test Coverage Categories
- Positive tests
- Negative tests
- Boundary tests
- Exception handling tests