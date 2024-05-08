package se.kth.iv1350.seminar3.modell;

import java.util.Random;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import java.util.ArrayList;
import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.integration.InventorySystem;
import se.kth.iv1350.seminar3.dto.DiscountDTO;

public class Sale {
    private ArrayList<ItemDTO> purchased;
    private double currentTotalPrice;
    private SaleDTO saleDTO;
    public boolean itemFound = false;
    private int saleID;
    


    public Sale(){

        this.saleID = generateRandomSaleID();  // Call to generate a random sale ID
        purchased = new ArrayList<ItemDTO>();
        currentTotalPrice = 0;
    }

    /**
     * Generates a random Sale ID.
     * @return a random integer to be used as a Sale ID
     */
    private int generateRandomSaleID() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);  // Ensure a non-negative integer
    }

    public int getSaleID() {
        return saleID;
    }

    public ArrayList getList(){

        return purchased;
    }



    // add a meth
    //Inclusive Vat

    public double getCurrentTotalPrice(){

       
        return currentTotalPrice;
    }

    public double calculationOfCurrentTotalPrice(ItemDTO itemDTO){

        currentTotalPrice = (itemDTO.getItemPrice() * (1 + (itemDTO.getItemVAT())/100));

        return currentTotalPrice;

    }

    //not needed
    private ArrayList <ItemDTO > getCurrentPurchasedListOfItems(){
        return purchased;
    }


    public void previouslyScannedItem(ItemDTO itemDTO){
        if(itemFound){

            itemDTO.increasedQuantityOfItem();

        }else{
            InventorySystem.fetchItem(itemDTO.getItemID());
            
        }
    }

    /**
     * 
     * The sale is updated when a new item is added to the list.
     * @param itemDTO
     */

    public void addItem(ItemDTO itemDTO, int quantity){

        purchased.add(itemDTO);

        // write the logic of if item is found or not here


    }

    private boolean findItemInfo(int itemID){// CHANGED THIS FROM ITEMID TO ITEMDTO SINCE THE ARRAYLIST CONTAINS DTO

        

        if (getTheListOfPurschasedItems(purchased).contains(itemID)){ // prev
            itemFound =true;
            previouslyScannedItem(itemDTO);

        }

        return itemFound;

    }

    
    
}

