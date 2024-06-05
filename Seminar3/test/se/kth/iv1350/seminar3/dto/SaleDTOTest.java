package se.kth.iv1350.seminar3.dto;

import static org.junit.jupiter.api.Assertions.*;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import se.kth.iv1350.seminar3.modell.Sale;


import java.util.List;

public class SaleDTOTest {
    private SaleDTO saleDTO;

    @BeforeEach
    public void setUp() {
        
        Sale sale = new Sale();
        sale.addItem(new ItemDTO("Item 1", 1, 10.0, 0.25, 1), 1);
        sale.addItem(new ItemDTO("Item 2", 2, 20.0, 0.25, 1), 2);
        

        
        saleDTO = new SaleDTO(sale);
    }

    @Test
    public void testGetTheListOfPurchasedItems() {
      
        List<ItemDTO> purchasedItems = saleDTO.getTheListOfPurchasedItems();

      
        assertNotNull(purchasedItems, "Purchased items list should not be null");

        
        assertEquals(2, purchasedItems.size(), "Expected number of purchased items should be 2");

   
        assertEquals("Item 1", purchasedItems.get(0).getItemName(), "First item name should be 'Item 1'");
        assertEquals("Item 2", purchasedItems.get(1).getItemName(), "Second item name should be 'Item 2'");
    }

    @Test
    public void testGetTheCurrentTotalPrice() {
       
        double expectedTotalPrice = 50.0; // 10.0 * 1 + 20.0 * 2

        
        double totalPrice = saleDTO.getTheCurrentTotalPrice();

      
        assertEquals(expectedTotalPrice, totalPrice, "Total price should match the expected value");
    }

    @Test
    public void testGetSaleDTOID() {
       
        int expectedSaleDTOID = 1; 

        
        int saleDTOID = saleDTO.getSaleDTOID();

      
        assertEquals(expectedSaleDTOID, saleDTOID, "SaleDTO ID should match the expected value");
    }
}
