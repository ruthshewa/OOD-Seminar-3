package se.kth.iv1350.seminar3.modell;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminar3.dto.ItemDTO;

public class SaleTest {

    private Sale sale;

    @BeforeEach
    public void setUp() {
        sale = new Sale();
    }

    @Test
    public void testItemAlreadyInSale() {
        ItemDTO item = new ItemDTO("Apple", 1, 3.7, 0.12, 2);
        sale.addItem(item, 2);

        ItemDTO foundItem = sale.itemAlreadyInSale(1);
        assertNotNull(foundItem);
        assertEquals(item.getItemID(), foundItem.getItemID());
    }

    @Test
    public void testAddItem() {
        ItemDTO item = new ItemDTO("Apple", 1, 3.7, 0.12, 2);
        sale.addItem(item, 2);

        assertEquals(1, sale.getPurchasedItems().size());
        assertEquals(item, sale.getPurchasedItems().get(0));
    }

    @Test
    public void testUpdateTotalPrice() {
        ItemDTO item1 = new ItemDTO("Apple", 1, 3.7, 0.12, 2);
        ItemDTO item2 = new ItemDTO("Banana", 2, 4.3, 0.12, 3);

        sale.addItem(item1, 2);
        sale.addItem(item2, 3);

        double expectedTotalPrice = item1.getItemPrice() * item1.getQuantity() + item2.getItemPrice() * item2.getQuantity();
        assertEquals(expectedTotalPrice, sale.getCurrentTotalPrice());
    }

    @Test
    public void testGetPurchasedItems() {
        ItemDTO item1 = new ItemDTO("Apple", 1, 3.7, 0.12, 2);
        ItemDTO item2 = new ItemDTO("Banana", 2, 4.3, 0.12, 3);

        sale.addItem(item1, 2);
        sale.addItem(item2, 3);

        assertEquals(2, sale.getPurchasedItems().size());
        assertTrue(sale.getPurchasedItems().contains(item1));
        assertTrue(sale.getPurchasedItems().contains(item2));
    }

    @Test
    public void testGetSaleID() {
        assertTrue(sale.getSaleID() > 0);
    }

    @Test
    public void testGetCurrentTotalPrice() {
        assertEquals(0, sale.getCurrentTotalPrice());
    }
}
