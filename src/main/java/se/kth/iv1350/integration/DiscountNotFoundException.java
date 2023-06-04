package se.kth.iv1350.integration;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.model.Customer;

/**
 * Thrown when retrieving a discount is not successful
 */
public class DiscountNotFoundException extends IllegalArgumentException {
    private final Integer customerID;

    public DiscountNotFoundException(Customer customer) {
        super("Unable to retrieve discount "+ "for customer ID: " + customer.getId());
        this.customerID = customer.getId();
    }

    public Integer getCustomerIDForUnavailableDiscount() {
        return customerID;
    }
}
