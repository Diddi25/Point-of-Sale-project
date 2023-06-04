package se.kth.iv1350.controller;

import se.kth.iv1350.integration.*;
import se.kth.iv1350.model.*;

/**
 * Represents all the actions the cashier does in POS by the method names
 * Calls methods from other layers to do operations
 */
public class Controller {
    private Cart cart;
    private final InventorySystemHandler inventorySystemHandler;
    private final AccountingSystemHandler accountingSystemHandler;
    private final DiscountDatabaseHandler discountDatabaseHandler;
    private final PaymentAttributeHandler paymentAttributeHandler;
    private final TotalRevenueFileOutput totalRevenueFileOutput;

    /**
     * Uses cart for collecting items
     * Class PaymentAttributeHandler calculates change, VAT and final price with the help of other attributes.
     * SaleManagementSystem is mainly communicating with integration layer classes
     */
    public Controller(
            SaleManagementSystem saleManagementSystem,
            AccountingSystemHandler accountingSystemHandler
    ) {
        this.inventorySystemHandler = saleManagementSystem.getInventorySystemHandler();
        this.discountDatabaseHandler = saleManagementSystem.getDiscountDatabaseHandler();
        this.totalRevenueFileOutput = saleManagementSystem.getTotalRevenueFileOutput();
        this.accountingSystemHandler = accountingSystemHandler;
        this.paymentAttributeHandler = new PaymentAttributeHandler();
    }

    public double getFinalPrice() {
        return paymentAttributeHandler.getFinalPrice();
    }

    public double getTotalRunningPrice() {
        return paymentAttributeHandler.getTotalPrice();
    }

    public Integer getQuantity(Item item) {
        return cart.getNumberOfItems(item);
    }

    /**
     * Starts a new sale.
     */
    public void startSale() {
        this.cart = new Cart(inventorySystemHandler);
    }

    /**
     * Makes the cart (@link Cart) after each scan of product
     *
     * @param product  The product ...
     * @param quantity The number of the product ...
     * @throws IllegalStateException if this method is called before startSale() or if quantity is negative
     */
    public Item scanItem(String product, Integer quantity) {
        if (cart == null) {
            throw new IllegalStateException("Call startSale() before add item.");
        }
        if (quantity < 1) {
            throw new IllegalStateException("Quantity can not be negative.");
        }
        Item item = cart.handleItemInCart(product, quantity);
        this.paymentAttributeHandler.calculateTotalRunningPrice(item, quantity);
        return item;
    }

    /**
     * Registers a new customer. If runTestSale() is edited for other use, the startSale() has to be called.
     *
     * @param customer The customer that will be registered.
     * @throws IllegalStateException if startSale() is not called first
     * @throws IllegalStateException if this method is called twice during the same sale.
     */
    public void registerCustomer(Customer customer) {
        if (cart == null) {
            throw new IllegalStateException("Call startSale() before register customer.");
        }
        if (cart.getCustomerForTheCart() != null) {
            throw new IllegalStateException("Customer already exist.");
        }
        cart.setCustomerToCart(customer);
    }

    /**
     * Retrieves discount by customer ID
     *
     * @throws DiscountNotFoundException if no discount is retrieved
     */
    public Integer searchDiscountByCustomerID(Customer customer) {
        Integer discount = discountDatabaseHandler.searchDiscount(customer);
        if (discount == 0) {
            throw new DiscountNotFoundException(customer);
        }
        this.paymentAttributeHandler.setDiscount(discount);
        return discount;
    }

    /**
     * Handles and calculates payment attributes in @link PaymentAttributeHandler
     */
    public void calculatePaymentAttributes(Integer amountPaid) {
        this.paymentAttributeHandler.calculateFinalPrice(paymentAttributeHandler.getDiscount());
        this.paymentAttributeHandler.calculateTotalVAT(cart);
        this.paymentAttributeHandler.calculateChange(amountPaid);
    }

    /**
     * returns receipt of sale information as a StringBuilder
     */
    public StringBuilder getReceipt() {
        return new Receipt(cart, paymentAttributeHandler).makeReceipt();
    }

    /**
     * The totalRevenueView comes from view and is added in @link AccountingSystemHandler
     * totalRevenueFileOutPut is part of integration layer and added as well
     *
     * @param observer is the TotalRevenueView
     */
    public void handleRevenue(IObserver observer) {
        accountingSystemHandler.registerObserver(observer);
        accountingSystemHandler.registerObserver(totalRevenueFileOutput);
        accountingSystemHandler.notifyObservers(paymentAttributeHandler.getFinalPrice());
    }

    /**
     * Logs the sale in external system handlers
     */
    public void saleLog() {
        inventorySystemHandler.makeNotis(cart);
        accountingSystemHandler.makeNotis(cart, paymentAttributeHandler);
    }

}
