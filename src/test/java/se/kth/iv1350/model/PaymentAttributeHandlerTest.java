package se.kth.iv1350.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentAttributeHandlerTest {
    private PaymentAttributeHandler pah;
    private Item item ;

    @BeforeEach
    void setUp() {
        pah = new PaymentAttributeHandler();
        item = new Item("Banana", 8, 5, LocalDate.of(2024, 04, 30));
    }

    @AfterEach
    void tearDown() {
        pah = null;
        item = null;
    }

    @Test
    void reactOnLowPayment() {
        pah.calculateTotalRunningPrice(item,1);
        pah.calculateFinalPrice(0);

        assertThrows(PaymentFailureException.class, () -> pah.calculateChange(5));
    }
}