package se.kth.iv1350.seminar3.modell;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PaymentTest {
    private Payment payment;

    @BeforeEach
    void setUp() {
        // Setting up a payment scenario where the customer pays 200 for a total sale of 150
        payment = new Payment(200.0, 150.0, "Cash");
    }

    @Test
    void testCalculateChange() {
        double expectedChange = 50.0; // Expected change is the amount paid minus the total sale amount
        double actualChange = payment.getCustomerChange();
        assertEquals(expectedChange, actualChange, "The calculated change should be correct.");
    }

    @Test
    void testGetMethodOfPayment() {
        String expectedMethod = "Cash";
        assertEquals(expectedMethod, payment.getMethodOfPayment(), "The payment method should be correctly retrieved.");
    }

    @Test
    void testGetAmountPaid() {
        double expectedAmountPaid = 200.0;
        assertEquals(expectedAmountPaid, payment.getAmountPaid(), "The amount paid should match the input.");
    }

    @Test
    void testGetTotalSaleAmount() {
        double expectedTotalSale = 150.0;
        assertEquals(expectedTotalSale, payment.getTotalSaleAmount(), "The total sale amount should match the input.");
    }
}
