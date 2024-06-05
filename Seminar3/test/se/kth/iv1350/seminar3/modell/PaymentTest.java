package se.kth.iv1350.seminar3.modell;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class PaymentTest {
    private Payment payment;
    private double totalSaleAmount;
    private double amountPaidByCustomer;
    private String methodOfPayment;

    @BeforeEach
    public void setUp() {
        totalSaleAmount = 50.0;
        amountPaidByCustomer = 70.0;
        methodOfPayment = "Cash";
        payment = new Payment(amountPaidByCustomer, totalSaleAmount, methodOfPayment);
    }

    @Test
    public void testGetCustomerChange() {
        double expectedChange = amountPaidByCustomer - totalSaleAmount;
        assertEquals(expectedChange, payment.getCustomerChange(), "Change should be calculated correctly");
    }

    @Test
    public void testGetMethodOfPayment() {
        assertEquals(methodOfPayment, payment.getMethodOfPayment(), "Payment method should match");
    }

    @Test
    public void testGetAmountPaid() {
        assertEquals(amountPaidByCustomer, payment.getAmountPaid(), "Amount paid should match");
    }

    @Test
    public void testGetTotalSaleAmount() {
        assertEquals(totalSaleAmount, payment.getTotalSaleAmount(), "Total sale amount should match");
    }
}
