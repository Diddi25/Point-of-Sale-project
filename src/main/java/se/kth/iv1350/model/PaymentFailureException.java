package se.kth.iv1350.model;

/**
 * Thrown when payment fails
 */
public class PaymentFailureException extends IllegalArgumentException {
    private final Integer amountPaid;

    /**
     * Creates a new instance with a message specifying for which item that is not valid.
     *
     * @param amountPaid the item that cannot be found in inventory
     */
    public PaymentFailureException(Integer amountPaid) {
        super("Unable to register payment of the paid amount: " + amountPaid);
        this.amountPaid = amountPaid;
    }

    /**
     * @return cashPayment for sale
     */
    public Integer getAmountPaid() {
        return amountPaid;
    }
}
