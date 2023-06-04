package se.kth.iv1350.integration;

import se.kth.iv1350.model.Customer;

import java.util.HashMap;

/**
 * Represents information about which customer is eligible for discounts
 */
public class DiscountDatabaseHandler {
    private static final HashMap<Integer, Integer> discountCatalog = new HashMap<>();

    /**
     * In memory database where the keys are customerIDs and values are discounts %
     * (not considered as external system, discussed in report)
     */
    static {
        discountCatalog.put(3410, 20);
        discountCatalog.put(4593, 50);
        discountCatalog.put(1234, 30);
        discountCatalog.put(2488, 10);
    }

    /**
     * If the discount catalog contains specific customerID, the discount will be returned
     *
     * @param customer ID is used for searching in catalog
     * @throws DiscountNotFoundException if customer is not eligible for Discount
     * @return the discount
     */
    public Integer searchDiscount(Customer customer) throws DiscountNotFoundException {
        return discountCatalog.getOrDefault(customer.getId(), 0);
    }

}
