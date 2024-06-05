package se.kth.iv1350.seminar3.controller;

import static org.junit.jupiter.api.Assertions.*;



import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.SaleDTO;

public class ControllerTest {
    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        
    }

    /*@AfterEach
    public void tearDown() {
        controller = null;
    }
    
    */
    
    /**
     * Test to see 
     */

    @Test
    void testStartSale() {
        // Act: Call the method to start a sale
        controller.startSale();
        
        // Assert: Check that the sale is initialized
        assertNotNull(controller.sale, "Sale should be initialized");
    }

    @Test
    public void testScanItem() {
        int itemID = 1;
        int quantity = 2;
        ItemDTO item = controller.scanItem(itemID, quantity);
        assertNotNull(item, "Item should be found and added to the sale");
        assertEquals(itemID, item.getItemID(), "Item ID should match the scanned item ID");
        assertEquals(quantity, item.getQuantity(), "Item quantity should match the scanned quantity");
    }

    @Test
    public void testScanItemWithDefaultQuantity() {
        int itemID = 1;
        ItemDTO item = controller.scanItem(itemID);
        assertNotNull(item, "Item should be found and added to the sale");
        assertEquals(itemID, item.getItemID(), "Item ID should match the scanned item ID");
        assertEquals(1, item.getQuantity(), "Default item quantity should be 1");
    }

    @Test
    public void testEndSale() {
        double totalPrice = controller.endSale();
        assertTrue(totalPrice > 0, "Total price should be greater than 0 after ending the sale");
    }
    @Test
    public void testPay() {
        // Set up the sale by scanning an item
        int itemID = 1;
        int quantity = 2;
        controller.scanItem(itemID, quantity);
        
        // End the sale to calculate the total price
        controller.endSale();
        

        double amountPaid = 100.0;
        double discount = 10.0;
        String paymentMethod = "Cash";

        controller.pay(amountPaid, discount, paymentMethod);

        // Additional assertions based on the state after payment
        // Example: Check if the payment object is not null
        assertNotNull(controller, "Controller should not be null after payment");
    }

    @Test
    public void testRequestDiscount() {
        int customerId = 123;
        double discount = controller.requestDiscount(customerId);
        assertTrue(discount >= 0, "Discount should be 0 or greater");
    }

    /*
    @Test
    public void testUpdateExternalSystems() {
        SaleDTO saleDTO = new SaleDTO(new Sale());
        controller.updateExternalSystems(saleDTO);
        // Add assertions to verify that external systems were updated correctly
    }
    */
}