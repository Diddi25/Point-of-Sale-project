package se.kth.iv1350.model;

/**
 * Thrown when scanning an item that is not valid
 */
public class ItemNotFoundException extends NullPointerException {
    private final String invalidItem;

    /**
     * Creates a new instance with a message specifying for which item that is not valid.
     *
     * @param itemNotValid the item that cannot be found in inventory
     */
    public ItemNotFoundException(String itemNotValid) {
        super("Unable to find the item " + itemNotValid + "in inventory");
        this.invalidItem = itemNotValid;
    }

    /**
     * @return the item that cannot be found
     */
    public String getInvalidItemName() {
        return invalidItem;
    }
}
