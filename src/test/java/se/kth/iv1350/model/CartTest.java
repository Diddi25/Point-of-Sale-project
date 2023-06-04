package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.integration.InventorySystemHandler;
import se.kth.iv1350.integration.ItemInventoryException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private InventorySystemHandler ish;
    private Cart cart;

    @BeforeEach
    void setUp() {
        ish = new InventorySystemHandler();
        cart = new Cart(ish);

    }

    @AfterEach
    void tearDown() {
        ish = null;
        cart = null;
    }

    @Test
    void throwItemInventoryException() {
        assertThrows(ItemNotFoundException.class, () -> cart.handleItemInCart("peanutbutter", 3));
    }
}