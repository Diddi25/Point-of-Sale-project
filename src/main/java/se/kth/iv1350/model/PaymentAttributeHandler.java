package se.kth.iv1350.model;

import java.util.Map;

/**
 * Handles all the payment attributes from customer purchase
 * All the methods below stores attributes in this class
 */
public class PaymentAttributeHandler {
    private double totalPrice;
    private double discount;
    private double finalPrice;
    private double totalVAT;
    private Integer amountPaid;
    private double change;

    public double getTotalPrice() {
        return totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalVAT() {
        return totalVAT;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public double getAmountPaid() {
        return amountPaid;
    }

    public double getChange() {
        return change;
    }

    /**
     * Calculates total running price
     *
     * @param item     item
     * @param quantity numbers of items
     */
    public void calculateTotalRunningPrice(Item item, Integer quantity) {
        totalPrice += item.getPrice() * quantity;
    }

    /**
     * Calculates final price after discount
     *
     * @param discount used for calculating final price
     */
    public void calculateFinalPrice(double discount) {
        this.discount = discount;
        this.totalPrice = this.getTotalPrice();
        this.finalPrice = totalPrice - (totalPrice * (discount / 100));
    }

    /**
     * Calculates total VAT, used in Receipt
     *
     * @param cart for checking each VAT rate in items
     */
    public void calculateTotalVAT(Cart cart) {
        for (Map.Entry<Item, Integer> entry : cart.entrySet()) {
            double itemVAT = entry.getKey().getVAT().doubleValue() / 100;
            totalVAT = entry.getKey().getPrice() * itemVAT * entry.getValue();
        }
    }

    /**
     * Calculates change after payment
     *
     * @param amountPaid is the cashPayment
     * @throws PaymentFailureException if payment is lower than sale price
     */
    public void calculateChange(Integer amountPaid) {
        this.amountPaid = amountPaid;
        this.change = amountPaid - finalPrice;
        if (amountPaid < finalPrice) {
            throw new PaymentFailureException(amountPaid);
        }
    }
}
