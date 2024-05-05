package se.kth.iv1350.seminar3.integration;

import se.kth.iv1350.seminar3.dto.ItemDTO;

public class InventorySystem {
    


    public InventorySystem(){



    }


    public ItemDTO findItemInInventory(int itemID){

        for(ItemDTO item : inventory){

            if(item.getItemID() == itemID){
                Sale.addItemFoundInInventorysytem(item);
                return item;
            }else{
                System.out.println("The identifier is invalid");

            }
        }



    }
}
