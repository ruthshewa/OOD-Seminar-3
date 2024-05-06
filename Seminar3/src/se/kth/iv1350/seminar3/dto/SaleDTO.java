package se.kth.iv1350.seminar3.dto;

import java.util.ArrayList;
import se.kth.iv1350.seminar3.modell.Sale;

public class SaleDTO {

    private double snapshotOfTheCurrentTotalPrice;// The saledto should also include an itemdescription besider the price and the running total
    private ArrayList<ItemDTO>snapshotOfPurscasedItems;
    private int saleDTOID;  // Sale ID to identify the sale

    public SaleDTO(Sale sale){

        this.snapshotOfTheCurrentTotalPrice = sale.getCurrentTotalPrice();
        this.snapshotOfPurscasedItems = sale.getCurrentPurchasedListOfItems();
        this.saleDTOID = sale.getSaleID(); // Store the Sale ID
 
    }

    public double getTheCurrentTotalPrice(){
        return snapshotOfTheCurrentTotalPrice;
    }

    public ArrayList<ItemDTO> getTheListOfPurschasedItems(){
        return snapshotOfPurscasedItems;
    }

   /**
     * Returns the sale ID.
     * @return the sale ID
     */
    public int getSaleDTOID() {
        return saleDTOID;
    }

    public 


    
}
