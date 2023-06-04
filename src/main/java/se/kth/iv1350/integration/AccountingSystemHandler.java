package se.kth.iv1350.integration;

import se.kth.iv1350.model.Cart;
import se.kth.iv1350.model.IObserver;
import se.kth.iv1350.model.PaymentAttributeHandler;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Makes accounting and notifications about accounting updates
 * Makes the accounting in map structure (customerID, items bought by customerID)
 */
public class AccountingSystemHandler {
    private final ArrayList<IObserver> observers = new ArrayList<>();
    private final Map<Integer, Cart> accountLog = new HashMap<>();

    /**
     * Registers TotalRevenueView and TotalRevenueFileOutput as observers
     * @param observer the observer that will be added
     */
    public void registerObserver(IObserver observer) {
        observers.add(observer);
    }

    /**
     * Notifies and prints revenue to all observers
     * @param totalAmount the revenue
     */
    public void notifyObservers(double totalAmount) {
        for (IObserver observer : observers) {
            observer.showTotalRevenue(totalAmount);
        }
    }

    /**
     * Logs accounting in map structure, map(customerID, itemsPurchasedAndQuantity)
     *
     * @param cart for retrieving information about items bought and its product quantity
     * @return sale information including customer ID, items bought by customer, discounts used and date
     */
    public void makeNotis(Cart cart, PaymentAttributeHandler pah) {
        this.accountLog.put(cart.getCustomerForTheCart().getId(), cart);
        System.out.println("\nAccounting System notified");
        accountLogNotification(cart, pah);
    }

    private void accountLogNotification(Cart cart, PaymentAttributeHandler pah) {
        System.out.println("CustomerID = " + cart.getCustomerForTheCart().getId() +
                    ", Discounts used: " + pah.getDiscount() + "%, " +
                    "Payment: " + pah.getAmountPaid() +
                    "\nItems : " + accountLog.get(cart.getCustomerForTheCart().getId()) +
                    "\n" + getCurrentDate());
    }

    private String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return "Date: " + formatter.format(new Date());
    }
}
