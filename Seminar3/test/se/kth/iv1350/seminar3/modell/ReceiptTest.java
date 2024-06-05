package se.kth.iv1350.seminar3.modell;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import se.kth.iv1350.seminar3.dto.ItemDTO;

public class ReceiptTest {
    private Sale sale;
    private Payment payment;
    private Receipt receipt;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
        payment = new Payment(100.0, 80.0, "Cash");
        receipt = new Receipt(payment, sale);
    }

    @AfterEach
    public void tearDown() {
        sale = null;
        payment = null;
        receipt = null;
    }

    @Test
    public void testGetTimeOfReceipt() {
        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime receiptTime = receipt.getTimeOfReceipt();
        
        // Check if the receipt time is within a second of the current time
        assertTrue(currentTime.minusSeconds(1).isBefore(receiptTime) && currentTime.plusSeconds(1).isAfter(receiptTime));
    }

    @Test
    public void testReceiptPaperFormat() {
        ItemDTO item1 = new ItemDTO("Apple", 1, 3.7, 0.12, 2);
        ItemDTO item2 = new ItemDTO("Banana", 2, 4.3, 0.12, 3);
        sale.addItem(item1, 2);
        sale.addItem(item2, 3);
        
        String receiptText = receipt.receiptPaperFormat();
        
        // Check if the receipt text contains specific strings representing sale details
        assertTrue(receiptText.contains("Store Name: Ruth Store"));
        assertTrue(receiptText.contains("Sale ID: " + sale.getSaleID()));
        assertTrue(receiptText.contains("Time of Sale: " + receipt.getTimeOfReceipt().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"))));
        assertTrue(receiptText.contains("Items Purchased:"));
        assertTrue(receiptText.contains(item1.getItemName()));
        assertTrue(receiptText.contains(item2.getItemName()));
        assertTrue(receiptText.contains("Total cost (incl VAT): " + sale.getCurrentTotalPrice()));
        assertTrue(receiptText.contains("Amount Paid: " + payment.getAmountPaid()));
        assertTrue(receiptText.contains("Payment Method: " + payment.getMethodOfPayment()));
        assertTrue(receiptText.contains("Change Given: " + (payment.getAmountPaid() - payment.getTotalSaleAmount())));
        assertTrue(receiptText.contains("Tack för besök, Välkommen åter"));
    }
}
