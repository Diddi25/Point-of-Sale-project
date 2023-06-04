package se.kth.iv1350.integration;

/**
 * Creates the classes calling external systems
 */
public class SaleManagementSystem {
    private final InventorySystemHandler inventorySystemHandler;
    private final DiscountDatabaseHandler discountDatabaseHandler;
    private final TotalRevenueFileOutput totalRevenueFileOutput;

    /**
     * Creates instances of classes calling external systems
     */
    public SaleManagementSystem() {
        this.inventorySystemHandler = new InventorySystemHandler();
        this.discountDatabaseHandler = new DiscountDatabaseHandler();
        this.totalRevenueFileOutput = new TotalRevenueFileOutput();

    }

    public TotalRevenueFileOutput getTotalRevenueFileOutput() {
        return totalRevenueFileOutput;
    }

    public InventorySystemHandler getInventorySystemHandler() {
        return this.inventorySystemHandler;
    }

    public DiscountDatabaseHandler getDiscountDatabaseHandler() {
        return this.discountDatabaseHandler;
    }

}