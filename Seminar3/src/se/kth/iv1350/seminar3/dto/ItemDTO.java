package se.kth.iv1350.seminar3.dto;

public class ItemDTO {
    private String itemName;
    private int itemID;
    private double itemPrice;
    private double itemVAT;
    private int quantity;
    
    public ItemDTO(String itemName,int itemID,double itemPrice,double itemVAT, int quantity){// added quantity of the Item

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

    public int getQuantity(){
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the item by one.
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Decreases the quantity of the item by one.
     */
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }



}
