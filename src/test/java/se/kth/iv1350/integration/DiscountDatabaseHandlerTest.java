package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Customer;

import static org.junit.jupiter.api.Assertions.*;

class DiscountDatabaseHandlerTest {
    private DiscountDatabaseHandler dbh;
    private Customer testCustomer;
    private String message;
    @BeforeEach
    void setUp() {
        dbh = new DiscountDatabaseHandler();
        testCustomer = new Customer(2488, "Andrew");
        message = "The discount eligible for ID '2488' is not 10%";
    }
    @AfterEach
    void tearDown() {
        dbh = null;
        testCustomer = null;
        message = null;
    }
    @Test
    void getCorrectDiscountByCustomerID() {
        assertEquals(10, dbh.searchDiscount(testCustomer), message);
    }

}