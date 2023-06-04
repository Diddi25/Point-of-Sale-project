package se.kth.iv1350.integration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.model.Item;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InventorySystemHandlerTest {
    private Item testItem;
    private String testStringProduct;
    private InventorySystemHandler ish;
    @BeforeEach
    void setUp(){
        testItem = new Item("Apple", 13, 8, LocalDate.of(2025, 05, 07));
        ish = new InventorySystemHandler();
        testStringProduct = "apple";

    }
    @AfterEach
    void tearDown() {
        testItem = null;
        ish = null;
        testStringProduct = null;
    }
    @Test
    void getCorrectItemByString() {
        assertEquals(testItem, ish.getItem(testStringProduct));
    }

}