package se.kth.iv1350.seminar3.dto;

import java.util.ArrayList;
import se.kth.iv1350.seminar3.modell.Sale;

public class SaleDTO {

    public Sale sale;
    private double snapshotOfTheCurrentTotalPrice;// The saledto should also include an itemdescription besider the price and the running total
    private ArrayList<ItemDTO> snapshotOfPurscasedItems;
    private int saleDTOID;  // Sale ID to identify the sale


    // don't referenc directly the sale instead copy the values from the sale to the saleDTO


    public ArrayList<ItemDTO> copyItems(ArrayList<ItemDTO> purchased) {

        snapshotOfPurscasedItems = new ArrayList<>(purchased.size());

        for (ItemDTO item : purchased) {
            snapshotOfPurscasedItems.add(item); // Add each item to the new list
        }

        return snapshotOfPurscasedItems;
    }


    public SaleDTO(Sale sale){

        this.snapshotOfTheCurrentTotalPrice = sale.getCurrentTotalPrice();
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

 


    
}
