package se.kth.iv1350.seminar3.dto;

import java.util.ArrayList;
import se.kth.iv1350.seminar3.modell.Sale;

public class SaleDTO {
    private final double snapshotOfTheCurrentTotalPrice;
    private final ArrayList<ItemDTO> snapshotOfPurchasedItems;
    private final int saleDTOID;

    /**
     * Constructs a SaleDTO by copying values from the Sale object.
     * @param sale The Sale object to copy data from.
     */
    public SaleDTO(Sale sale) {
        this.snapshotOfTheCurrentTotalPrice = sale.getCurrentTotalPrice();
        this.snapshotOfPurchasedItems = copyItems(sale.getPurchasedItems());
        this.saleDTOID = sale.getSaleID();
    }

    /**
     * Copies the list of purchased items.
     * @param purchased The original list of purchased items.
     * @return A copy of the list of purchased items.
     */
    private ArrayList<ItemDTO> copyItems(ArrayList<ItemDTO> purchased) {
        ArrayList<ItemDTO> itemsCopy = new ArrayList<>(purchased.size());
        for (ItemDTO item : purchased) {
            itemsCopy.add(item); // Use the copy constructor to create a deep copy
        }
        return itemsCopy;
    }

    /**
     * Gets the snapshot of the current total price.
     * @return The total price at the time of DTO creation.
     */
    public double getTheCurrentTotalPrice() {
        return snapshotOfTheCurrentTotalPrice;
    }

    /**
     * Gets the snapshot of purchased items.
     * @return A list of purchased items at the time of DTO creation.
     */
    public ArrayList<ItemDTO> getTheListOfPurchasedItems() {
        return snapshotOfPurchasedItems;
    }

    /**
     * Returns the sale ID.
     * @return The sale ID.
     */
    public int getSaleDTOID() {
        return saleDTOID;
    }
}
