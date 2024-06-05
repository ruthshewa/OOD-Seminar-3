package se.kth.iv1350.seminar3.integration;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import se.kth.iv1350.seminar3.dto.ItemDTO;


public class InventorySystemTest {
    private InventorySystem inventorySystem;

    @BeforeEach
    public void setUp() {
        inventorySystem = new InventorySystem();
    }

    @Test
    public void testFetchIteminfo_ItemFound() {
        int itemId = 1; 
        ItemDTO item = inventorySystem.fetchIteminfo(itemId);
        assertNotNull(item, "Item should not be null");
        assertEquals("Orange Juice", item.getItemName(), "Item name should be 'Orange Juice'");
        assertEquals(23.5, item.getItemPrice(), "Item price should be 23.5");
    }

    @Test
    public void testFetchIteminfo_ItemNotFound() {
        int itemId = 100;
        ItemDTO item = inventorySystem.fetchIteminfo(itemId);
        assertNull(item, "Item should be null as it is not found");
    }
}

