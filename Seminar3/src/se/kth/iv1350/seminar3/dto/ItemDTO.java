package se.kth.iv1350.seminar3.dto;

public class ItemDTO {
    public String itemName;
    public int itemID;
    public double itemPrice;
    public double itemVAT;
    public int quantity;
    
    
    public ItemDTO(String itemName,int itemID,double itemPrice,double itemVAT,int quantity){// added quantity of the Item

        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.quantity = quantity;

    }

    public int getItemID(){
        return itemID;
    }

    public String getItemName(){
        return itemName;
    }

    public double getItemPrice(){
        return itemPrice;
    }

    public double getItemVAT(){
        return itemVAT;
    }
    public int increasedQuantityOfItem(){
        return quantity++;
    }

    public int decreaseQuantityOfItem(){
        return quantity--;
    }


}
