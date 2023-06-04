package se.kth.iv1350.startUp;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.AccountingSystemHandler;
import se.kth.iv1350.integration.SaleManagementSystem;
import se.kth.iv1350.view.View;

import java.io.IOException;

/**
 * Contains the <code>main</code> method. Performs all startup of the
 * application.
 */
public class Main {
    /**
     * Starts the application.
     *
     * @param args The application does not take any command line parameters.
     * @throws IOException if prints does not behave correctly in @AccountingSystemHandler
     */
    public static void main(String[] args) {
        try {
            SaleManagementSystem sms = new SaleManagementSystem();
            AccountingSystemHandler ash = new AccountingSystemHandler();
            Controller controller = new Controller(sms, ash);
            new View(controller).runTestSale();
        } catch (IOException ex) {
            System.out.println("Unable to start the application");
            ex.printStackTrace();
        }
    }
}

