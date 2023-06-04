package se.kth.iv1350.integration;


/**
 * Thrown when something goes wrong while performing an operation in the <code>InventorySystemHandler</code>.
 */
public class ItemInventoryException extends RuntimeException {

    /**
     * Creates a new instance representing the condition described in the specified message.
     *
     * @param msg A message that describes what went wrong.
     */
    public ItemInventoryException(String msg) {
        super(msg);
    }
}
