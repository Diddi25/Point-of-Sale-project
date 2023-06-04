package se.kth.iv1350.integration;

import se.kth.iv1350.model.IObserver;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 * Observes the class Register and writes the total revenue to a file
 */
public class TotalRevenueFileOutput implements IObserver {

    private PrintWriter fileStream;

    /**
     * Creates a new instance.
     * IMPORTANT NOTE: If there's no output in revenue.txt it's because you are using mac
     * This works excellent in Windows/PC
     */
    public TotalRevenueFileOutput() {
        try {
            fileStream = new PrintWriter(new FileWriter("revenue.txt"), true);
        } catch (IOException ex) {
            System.err.println("Error creating file: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    @Override
    public void showTotalRevenue(double totalRevenue) {
        try {
            fileStream.println(LocalDateTime.now() + " Total revenue: " + totalRevenue);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

}
