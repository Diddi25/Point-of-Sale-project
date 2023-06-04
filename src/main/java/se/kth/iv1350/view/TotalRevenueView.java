package se.kth.iv1350.view;

import se.kth.iv1350.model.IObserver;

/**
 * Observes the class Register and writes the total revenue to the console when
 * it changes.
 */
public class TotalRevenueView implements IObserver {

    /**
     * Shows the total revenue to the view.
     *
     * @param totalRevenue the total revenue
     */
    @Override
    public void showTotalRevenue(double totalRevenue) {
        System.out.println("Total revenue: " + totalRevenue + "\n");
    }
}