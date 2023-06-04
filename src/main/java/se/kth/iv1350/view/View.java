package se.kth.iv1350.view;

import se.kth.iv1350.controller.Controller;
import se.kth.iv1350.integration.DiscountNotFoundException;
import se.kth.iv1350.model.Customer;
import se.kth.iv1350.model.Item;
import se.kth.iv1350.model.ItemNotFoundException;
import se.kth.iv1350.model.PaymentFailureException;
import se.kth.iv1350.util.LogHandler;

import java.io.IOException;

/**
 * Represents the user interface, handling input and ouput in the program
 */
public class View {
    private final Controller controller;
    private final ErrorMessageHandler errorMsgHandler = new ErrorMessageHandler();
    private final TotalRevenueView totalRevenueView;
    private final LogHandler logger;

    /**
     * The program starts once the View is created in main
     *
     * @param controller is an instance of Controller which View only communicates with
     * @throws IOException if PrintWriter in @link LogHandler goes wrong
     */
    public View(Controller controller) throws IOException {
        this.controller = controller;
        this.totalRevenueView = new TotalRevenueView();
        this.logger = new LogHandler();
    }

    /**
     * The method names is chosen by the events in POS
     * Logs exceptions in LogHandler and ErrorMessageHandler
     */
    public void runTestSale() {
        try {
            controller.startSale();
            startScanningItems();
            customerHasMoreItems(true);
            Customer customer = new Customer(1234, "Andrew");
            controller.registerCustomer(customer);
            customerHasDiscountByCustomerID(customer);
            payment(50);
            printReceipt();
            logSaleAndHandleRevenue();
        } catch (ItemNotFoundException infe) {
            errorMsgHandler.showErrorMsg("Searched item not found for itemName: " + infe.getInvalidItemName());
        } catch (DiscountNotFoundException dnfe) {
            errorMsgHandler.showErrorMsg("Searched discount for customer ID: "
                    + dnfe.getCustomerIDForUnavailableDiscount() + "not found");
        } catch (PaymentFailureException pfe) {
            errorMsgHandler.showErrorMsg("payment failed by the given amount: " + pfe.getAmountPaid());
        } catch (Exception exc) {
            writeToLogAndUI("Failed to purchase", exc);
            exc.printStackTrace();
        }
    }

    private void startScanningItems() {
        printScannedItem(scanItem("apple", 2));
        //printScannedItem(scanItem("pineapple", 4)); //non existing item
        printScannedItem(scanItem("banana", 3));
    }

    private void customerHasMoreItems(boolean moreItems) {
        System.out.println(moreItems ? "more items of type 'Apple' with quantity +1" : "");
        printScannedItem(moreItems ? controller.scanItem("apple", 1) : null);
    }

    private Item scanItem(String product, Integer quantity) throws ItemNotFoundException {
        return controller.scanItem(product, quantity);
    }

    private void printScannedItem(Item item) {
        if (item == null) {
            return;
        }
        System.out.println("Item {" +
                "name='" + item.getName() + '\'' +
                ", price=" + item.getPrice() +
                ", VAT= " + item.getVAT() +
                ", expirationDate=" + item.getExpireDate() +
                "}, " +
                "Quantity: " + controller.getQuantity(item) +
                "\nRunning total: " + controller.getTotalRunningPrice());
        System.out.println(" ");
    }

    private void customerHasDiscountByCustomerID(Customer customer) throws DiscountNotFoundException {
        Integer discount = controller.searchDiscountByCustomerID(customer);
        String hasDiscount = "Customer has discount: " + discount + "%";
        System.out.println(discount == 0 ? "No available discount" : hasDiscount);
    }

    private void payment(Integer amountPaid) throws PaymentFailureException {
        controller.calculatePaymentAttributes(amountPaid);
        System.out.println("That will be a total of " + controller.getFinalPrice() + " please");
        System.out.println("Customer paid: " + amountPaid);
    }

    private void printReceipt() {
        System.out.println(controller.getReceipt());
    }

    private void logSaleAndHandleRevenue() {
        try {
            controller.handleRevenue(totalRevenueView);
            controller.saleLog();
        } catch (Exception exc) {
            writeToLogAndUI("Failed to log sale and print revenue", exc);
        }
    }

    private void writeToLogAndUI(String uiMsg, Exception exc) {
        errorMsgHandler.showErrorMsg(uiMsg);
        logger.logException(exc);
    }

}
