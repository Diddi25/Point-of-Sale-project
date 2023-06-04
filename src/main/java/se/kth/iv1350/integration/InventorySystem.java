package se.kth.iv1350.integration;

import se.kth.iv1350.model.Item;

/**
 * Specifies the use of InventorySystemHandler
 */
public interface InventorySystem {
    Item getItem(String productName);
}
