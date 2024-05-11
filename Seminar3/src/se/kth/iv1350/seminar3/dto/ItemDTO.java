package se.kth.iv1350.seminar3.dto;

public class ItemDTO {
    private String itemName;
    private int itemID;
    private double itemPrice;
    private double itemVAT;
    private int quantity;

    public ItemDTO(String itemName, int itemID, double itemPrice, double itemVAT, int quantity) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.itemPrice = itemPrice;
        this.itemVAT = itemVAT;
        this.quantity = quantity;
    }

    // Copy constructor
    public ItemDTO(ItemDTO item) {
        this.itemName = item.itemName;
        this.itemID = item.itemID;
        this.itemPrice = item.itemPrice;
        this.itemVAT = item.itemVAT;
        this.quantity = item.quantity;
    }

    public int getItemID() {
        return itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public double getItemVAT() {
        return itemVAT;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Increases the quantity of the item by a specified amount.
     */
    public void increaseQuantity(int amount) {
        this.quantity += amount;
    }

    /**
     * Decreases the quantity of the item by a specified amount.
     */
    public void decreaseQuantity(int amount) {
        this.quantity -= amount;
    }
}
