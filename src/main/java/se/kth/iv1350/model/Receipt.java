package se.kth.iv1350.model;

import java.util.Date;
import java.util.Map;

/**
 * Creates receipt of sale
 */
public class Receipt {
    private final Cart cart;
    private final PaymentAttributeHandler pah;
    private final StringBuilder receipt = new StringBuilder();

    /**
     * All attributes needed for receipt
     *
     * @param cart for iterating items bought
     * @param pah  for retrieving payment attributes
     */
    public Receipt(Cart cart, PaymentAttributeHandler pah) {
        this.cart = cart;
        this.pah = pah;
    }

    /**
     * Makes receipt in specific format
     *
     * @return the receipt as StringBuilder object
     */
    public StringBuilder makeReceipt() {
        appendHeader();
        appendItems();
        appendPaymentAttributes();
        appendDate();
        return receipt;
    }

    private void appendHeader() {
        receipt.append("\n\nHere is the receipt:\n\n");
    }

    private void appendItems() {
        for (Map.Entry<Item, Integer> item : cart.entrySet()) {
            receipt.append(item.getKey().getName())
                    .append(" {quantity ").append(item.getValue()).append("}")
                    .append(" {price: ").append(item.getKey().getPrice())
                    .append(", VAT: ").append(item.getKey().getVAT())
                    .append(", expireDate: ").append(item.getKey().getExpireDate()).append("} \n");
        }
    }

    private void appendPaymentAttributes() {
        receipt.append("Total price : ").append(pah.getTotalPrice()).append("\n")
                .append("Total VAT: ").append(String.format("%.2f", pah.getTotalVAT())).append("\n")
                .append("Discounts : ").append(pah.getDiscount()).append("%\n")
                .append("Final price : ").append(pah.getFinalPrice()).append("\n")
                .append("Amount paid: ").append(pah.getAmountPaid()).append("\n")
                .append("Change : ").append(String.format("%.1f", pah.getChange())).append("\n");
    }

    private void appendDate() {
        receipt.append(new Date()).append("\n");
    }
}
