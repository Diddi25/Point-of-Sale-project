package se.kth.iv1350.integration;

import se.kth.iv1350.model.Cart;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.ItemNotFoundException;

import java.time.LocalDate;
import java.util.HashMap;

/**
 * Notifies quantity of specific product type left in inventory
 * Has an inbuilt static innerclass which represents the data being used in this program
 */
public class InventorySystemHandler implements InventorySystem {
    private static final HashMap<Item, Integer> inventory = new HashMap<>();
    private Integer previousQuantity;
    private Integer itemQuantityRemain;
    private Item currentItem;

    public Integer getPreviousQuantity() {
        return previousQuantity;
    }

    public Integer getItemQuantityRemain() {
        return itemQuantityRemain;
    }

    public Item getCurrentItem() {
        return currentItem;
    }

    /**
     * In memory database for all products available for purchase
     * and its quantity in inventory in this program
     * (not considered as external system, discussed in the report)
     */
    static {
        inventory.put(new Item("Apple", 13, 8, LocalDate.of(2025, 05, 07)), 30);
        inventory.put(new Item("Banana", 8, 5, LocalDate.of(2024, 04, 30)), 20);
    }

    /**
     * Is used to retrieve items from the system during scan process
     *
     * @param productName is for searching in inventory
     * @throws ItemNotFoundException if no item is found
     * @return wished item
     */
    public Item getItem(String productName) throws ItemNotFoundException {
        return inventory.keySet().stream()
                .filter(item -> item.getName().equalsIgnoreCase(productName))
                .findFirst()
                .orElse(null);
    }

    public void makeNotis(Cart cart) {
        System.out.println("Inventory System notified");
        searchInventory(cart);
    }

    /**
     * Notifies item quantity after update and search for each item
     *
     * @param cart used for checking which item quantities should be updated
     */
    private void searchInventory(Cart cart) {
        try {
            cart.entrySet().stream()
                    .filter(entry -> inventory.containsKey(entry.getKey()))
                    .forEach(entry -> {
                        this.currentItem = entry.getKey();
                        updateInventory(cart);
                    });
        } catch (ItemInventoryException iie) {
            throw new ItemInventoryException("Inventory server not running for retrieving item: " + currentItem.getName());
        }
    }

    private void updateInventory(Cart cart) throws ItemInventoryException {
        this.previousQuantity = inventory.get(currentItem);
        this.itemQuantityRemain = inventory.get(currentItem) - cart.get(currentItem);
        inventory.replace(currentItem, itemQuantityRemain);
        inventoryLogNotification();
    }

    private void inventoryLogNotification() {
        System.out.println("Item of type '" + getCurrentItem().getName() +
                            "' left in inventory: " + getItemQuantityRemain() +
                            " from " + getPreviousQuantity());
    }

}
