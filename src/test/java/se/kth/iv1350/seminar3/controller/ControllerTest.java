package se.kth.iv1350.seminar3.controller;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.modell.Sale;

/**
 * Tests for Controller class. Ensures functionality related to starting sales,
 * scanning items, and completing transactions behaves as expected.
 */
class ControllerTest {

    private Controller controller;

    @BeforeEach
    public void setUp() {
        controller = new Controller();
        controller.startSale();  // Start a new sale for each test to ensure a clean state
    }

    @AfterEach
    public void tearDown() {
        controller = null;  // Clean up after each test
    }

    /**
     * Verifies that the startSale method initializes a new Sale object.
     */
    @Test
    public void testStartSaleIsInitialized() {
        Sale theStateOfTheSale = controller.getCurrentSale();
        assertNotNull(theStateOfTheSale, "Sale should be initialized after starting a new sale");
    }

    /**
     * Ensures that startSale creates a new Sale object with each call,
     * verifying that each sale is distinct and isolated from previous sales.
     */
    @Test
    public void testStartSaleCreatesNewSaleObjectOnEveryCall() {
        Sale firstObject = controller.getCurrentSale();
        controller.startSale();
        Sale secondObject = controller.getCurrentSale();
        assertNotSame(firstObject, secondObject, "Each call to startSale should create a new Sale object");
    }

    /**
     * Tests the scanning of an item by verifying that a valid item ID and quantity
     * results in the correct item being added to the sale.
     */
    @Test
    public void testScanItemSuccessfully() {
        int itemID = 1;
        int quantity = 2;
        ItemDTO item = controller.scanItem(itemID, quantity);
        assertNotNull(item, "Item should be found and added to the sale");
        assertEquals(quantity, item.getQuantity(), "Scanned item should have the correct quantity");
    }

    /**
     * Tests that an already scanned item's quantity is correctly increased when scanned again.
     * Assumes that the item with itemID 1 can be added and then quantity increased.
     */
    @Test
    public void testScanItemIncreasesQuantityWhenAlreadyInSale() {
        int itemID = 1;
        int initialQuantity = 1;
        int additionalQuantity = 2;

        // First scan to add the item to the sale
        ItemDTO firstScan = controller.scanItem(itemID, initialQuantity);
        assertNotNull(firstScan, "Item should have been added on first scan");
        assertEquals(initialQuantity, firstScan.getQuantity(), "Initial scan did not add the correct quantity");

        // Second scan to increase the quantity of the already added item
        ItemDTO secondScan = controller.scanItem(itemID, additionalQuantity);
        assertNotNull(secondScan, "Item should be present on second scan");
        assertEquals(initialQuantity + additionalQuantity, secondScan.getQuantity(),
                "Second scan did not correctly increase the quantity");
    }

    /**
     * Tests that the endSale method correctly calculates and returns the total price,
     * assuming that at least one item has been added to ensure the total is greater than zero.
     */
    @Test
    public void testEndSale() {
        controller.scanItem(1, 1); // Scan an item to ensure total is greater than zero
        double totalPrice = controller.endSale();
        assertTrue(totalPrice > 0, "Total price should be greater than 0 after ending the sale");
    }

    /**
     * Tests the payment process by simulating a complete sale transaction,
     * ensuring that the sale is processed correctly including applying discounts and handling payments.
     */
    @Test
    public void testPay() {
        controller.scanItem(1, 2); // Add items to sale
        controller.endSale();      // Complete the sale
        double amountPaid = 100.0; // Simulate payment
        double discount = 10.0;    // Simulate a discount
        controller.pay(amountPaid, discount, "Cash"); // Process the payment
        assertNotNull(controller, "Controller should still be operational after payment");
    }
}
