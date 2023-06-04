package se.kth.iv1350.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.AccountingSystemHandler;
import se.kth.iv1350.integration.DiscountNotFoundException;
import se.kth.iv1350.integration.SaleManagementSystem;
import se.kth.iv1350.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class ControllerTest {
    private Customer testCustomer;
    private Controller testController;
    private String message;

    @BeforeEach
    void setUp() {
        testCustomer = new Customer(3781, "TestHuman");
        testController = new Controller(new SaleManagementSystem(), new AccountingSystemHandler());
        message = "Discount exception should be thrown";
    }

    @AfterEach
    void tearDown() {
        testCustomer = null;
        testController = null;
        message = null;
    }

    @Test
    void throwDiscountException() {
        assertThrows(DiscountNotFoundException.class,
                () -> testController.searchDiscountByCustomerID(testCustomer), message);
    }
}