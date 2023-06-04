package se.kth.iv1350.model;

import java.time.LocalDate;
import java.util.Objects;

/**
 * All fields for each item created in @link InventorySystem
 */
public class Item {
    private final String name;
    private final Integer price;
    private final Integer VAT;
    private final LocalDate expireDate;

    public Item(String name, Integer price, Integer VAT, LocalDate expireDate) {
        this.name = name;
        this.price = price;
        this.VAT = VAT;
        this.expireDate = expireDate;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getVAT() {
        return VAT;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public String toString() {
        return "Item {" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", VAT= " + VAT +
                ", expirationDate=" + expireDate +
                '}';
    }

    /**
     * Made for the test @link InventorySystemHandlerTest
     * @param obj an object to compare with, in this case Item
     * @return true if all attributes for specific item matches
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Item other = (Item) obj;
        return Objects.equals(name, other.name) &&
                price == other.price &&
                VAT == other.VAT &&
                Objects.equals(expireDate, other.expireDate);
    }
}
