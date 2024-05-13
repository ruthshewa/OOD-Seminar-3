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
            ItemDTO itemCopy = new ItemDTO(
                item.getItemName(),
                item.getItemID(),
                item.getItemPrice(),
                item.getItemVAT(),
                item.getQuantity()
            );
            itemsCopy.add(itemCopy);
        }
        return itemsCopy;
    }

    public double getTheCurrentTotalPrice() {
        return snapshotOfTheCurrentTotalPrice;
    }

    public ArrayList<ItemDTO> getTheListOfPurchasedItems() {
        return snapshotOfPurchasedItems;
    }

    public int getSaleDTOID() {
        return saleDTOID;
    }
}
