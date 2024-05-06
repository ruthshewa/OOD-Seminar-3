package se.kth.iv1350.seminar3.modell;

import se.kth.iv1350.seminar3.dto.ItemDTO;
import java.util.ArrayList;
import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.integration.InventorySystem;

public class Sale {
    private ArrayList<ItemDTO> purchased;
    private double currentTotalPrice;
    private SaleDTO saleDTO;
    public boolean itemFound = false;
    


    public Sale(){

        purchased = new ArrayList<>();
        currentTotalPrice = 0;
    }

    public boolean findItemInfo(ItemDTO itemDTO){// CHANGED THIS FROM ITEMID TO ITEMDTO SINCE THE ARRAYLIST CONTAINS DTO

        

        if (saleDTO.getTheListOfPurschasedItems(purchased).contains(itemDTO)){ // prev
            itemFound =true;
            previouslyScannedItem(itemDTO);

        }

        return itemFound;

    }

    public double getCurrentTotalPrice(){

       
        return currentTotalPrice;
    }

    public double calculationOfCurrentTotalPrice(ItemDTO itemDTO){

        currentTotalPrice = (itemDTO.getItemPrice() * (1 + (itemDTO.getItemVAT())/100));

        return currentTotalPrice;

    }

    public ArrayList <ItemDTO > getCurrentPurchasedListOfItems(){
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

    public void addItemFoundInInventorysytem(ItemDTO itemDTO){

        purchased.add(itemDTO);


    }
    
}
