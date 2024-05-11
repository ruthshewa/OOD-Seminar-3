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
    private int saleID;

    /**
     * Constructs a Sale with a unique ID and initializes it.
     */
    public Sale() {
        this.saleID = generateRandomSaleID();
        this.purchased = new ArrayList<>();
        this.currentTotalPrice = 0; // Whenever a new sales is started, the total price is set to 0.
    }

    /**
     * Generates a random, non-negative Sale ID.
     * @return Randomly generated integer.
     */
    private int generateRandomSaleID() {
        Random random = new Random();
        return random.nextInt(Integer.MAX_VALUE);
    }

    /**
     * @return unique Sale ID.
     */
    public int getSaleID() {
        return saleID;
    }

    /**
     * Retrieves the list of items purchased.
     * @return List of items.
     */
    public ArrayList<ItemDTO> getPurchasedItems() {
        return purchased;
    }

    

    public double getCurrentTotalPrice() {
        return currentTotalPrice;
    }

    /**
     * Adds or updates an item in the sale based on the item ID.
     * @param itemDTO The item to add or update.
     * @param quantity The quantity of the item.
     */
    public void addItem(ItemDTO itemDTO, int quantity) {
        if (updateItemQuantityIfExists(itemDTO, quantity)) {
            updateTotalPrice();
            return;
        }
        addItemToPurchaseList(itemDTO,quantity);
        updateTotalPrice();
    }

     /**
     * Increases the quantity of an existing item if it exists in the purchased list.
     * @param itemDTO The item DTO.
     * @param quantity The quantity to add.
     * @return true if the item exists and was updated, false otherwise.
     */
    private boolean updateItemQuantityIfExists(ItemDTO itemDTO, int quantity) {
        for (ItemDTO item : purchased) {
            if (item.getItemID() == itemDTO.getItemID()) {
                item.increaseQuantity(quantity);
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a specified quantity of a new item to the purchased list.
     * This method is used when a customer purchases multiple units of the same item.
     * @param itemDTO The item DTO to add.
     * @param quantity The quantity of the new item.
     */
    private void addItemToPurchaseList(ItemDTO itemDTO,int quantity) {
        itemDTO.setQuantity(quantity);
        purchased.add(itemDTO);
    }

    /**
     * Recalculates the total price of the sale based on the items and their quantities.
     */
    private void updateTotalPrice() {
        currentTotalPrice = 0;
        for (ItemDTO item : purchased) {
            currentTotalPrice += item.getItemPrice() * item.getQuantity();
        }
    }
/**
     * Applies a discount to the current total price using the provided DiscountDTO.
     * @param discount The discount details.
     * @return The total price after discount.
     */
    public double applyDiscount(DiscountDTO discount) {
        applyFixedDiscount(discount);
        applyPercentageDiscount(discount);
        return currentTotalPrice;
    }

    /**
     * When passed a list of all bought items, a fixed amount of discount is subtracted from the total price.
     * @param discount The discount containing the fixed amount to subtract.
     */
    private void applyFixedDiscount(DiscountDTO discount) {
        if (discount.getDiscountAmount() > 0) {
            currentTotalPrice -= discount.getDiscountAmount();
        }
    }

    /**
     * When passed the total cost or the customer id, a percentage is be reduced from the total cost of the entire sale. 
     * @param discount The discount containing the percentage rate to apply.
     */
    private void applyPercentageDiscount(DiscountDTO discount) {
        if (discount.getDiscountRate() > 0) {
            currentTotalPrice -= currentTotalPrice * (discount.getDiscountRate() / 100.0);
        }
    }
}
