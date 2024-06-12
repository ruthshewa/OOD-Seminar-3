package se.kth.iv1350.seminar3.modell;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminar3.dto.ItemDTO;

class SaleTest {

    private Sale sale;

    @BeforeEach
    void setUp() {
        sale = new Sale();
    }

    @Test
    void testAddNewItem() {
        ItemDTO newItem = new ItemDTO("Milk", 101, 20.0, 1.25, 10);
        sale.addItem(newItem, 10);
        assertFalse(sale.getPurchasedItems().isEmpty(), "Sale should contain items after adding.");
    }

    @Test
    void testItemAlreadyInSale() {
        ItemDTO newItem = new ItemDTO("Bread", 102, 25.0, 1.12, 5);
        sale.addItem(newItem, 5);
        assertNotNull(sale.itemAlreadyInSale(102), "Item should be found in the sale.");
    }

    @Test
    void testIncreaseItemQuantity() {
        ItemDTO newItem = new ItemDTO("Butter", 103, 30.0, 1.06, 2);
        sale.addItem(newItem, 2);
        sale.increaseItemQuantity(newItem, 3);
        assertEquals(5, newItem.getQuantity(), "Item quantity should be increased to 5.");
    }

    @Test
    void testTotalPriceCalculation() {
        sale.addItem(new ItemDTO("Cheese", 104, 40.0, 1.25, 2), 2);
        sale.addItem(new ItemDTO("Egg", 105, 10.0, 1.06, 12), 12);
        double expectedTotal = 2 * 40.0 + 12 * 10.0;
        assertEquals(expectedTotal, sale.getCurrentTotalPrice(), "Total price should be correctly calculated.");
    }

    @Test
    void testApplyDiscount() {
        sale.addItem(new ItemDTO("Yogurt", 106, 15.0, 1.06, 3), 3);
        double discount = 5.0;
        sale.applyDiscount(discount);
        double expectedDiscountedPrice = sale.getCurrentTotalPrice() - discount;
        assertEquals(expectedDiscountedPrice, sale.getDiscountedTotalPrice(), "Discounted price should be calculated correctly.");
    }

}
