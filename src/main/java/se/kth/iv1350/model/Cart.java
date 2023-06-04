package se.kth.iv1350.model;

import se.kth.iv1350.integration.InventorySystemHandler;

import java.util.HashMap;

/**
 * Collects all items the customer buys
 * mapOf(Item object, quantity of item)
 */
public class Cart extends HashMap<Item, Integer> {
    private final InventorySystemHandler inventory;
    private Customer customer;

    /**
     * Creates a new instance of Cart for a customer
     */
    public Cart(InventorySystemHandler inventory) {
        this.inventory = inventory;
    }

    public Integer getNumberOfItems(Item item) {
        return this.get(item);
    }

    /**
     * Set a new customer that owns the cart. Only registered customers can add items to cart.
     *
     * @param customer The customer that will be registered.
     * @throws IllegalStateException if this method is called twice during the
     *                               same rental. A rental is ended when <code>pay</code> is called.
     */
    public void setCustomerToCart(Customer customer) {
        this.customer = customer;
    }

    /**
     * @return The customer to the cart
     */
    public Customer getCustomerForTheCart() {
        return this.customer;
    }

    /**
     * Handles the cart by identifying, counting and adding item to cart.
     * Calculates total price during scanning process in order to print total running price
     *
     * @param itemName is the product name from scanning process
     * @param quantity is the quantity of the item
     * @return information about the item in the cart
     * @throws ItemNotFoundException if item retrieved is null
     */
    public Item handleItemInCart(String itemName, Integer quantity) {
        Item item = inventory.getItem(itemName);
        if (item == null) {
            throw new ItemNotFoundException(itemName);
        } else {
            Integer newQuantity = checkIfItemAlreadyExistsInCartMap(item, quantity);
            addItemToCart(item, newQuantity);
        }
        return item;
    }

    private Integer checkIfItemAlreadyExistsInCartMap(Item item, Integer quantity) {
        return this.containsKey(item) ? this.get(item) + quantity : quantity;
    }

    private void addItemToCart(Item item, Integer numberOfProducts) {
        this.put(item, numberOfProducts);
    }
}
