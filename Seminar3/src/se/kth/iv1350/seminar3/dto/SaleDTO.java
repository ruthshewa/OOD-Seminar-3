package se.kth.iv1350.seminar3.dto;

import java.util.ArrayList;
import se.kth.iv1350.seminar3.modell.Sale;

public class SaleDTO {

    private double snapshotOfTheCurrentTotalPrice;// The saledto should also include an itemdescription besider the price and the running total
    private ArrayList<ItemDTO>snapshotOfPurscasedItems;

    public SaleDTO(Sale sale){

        this.snapshotOfTheCurrentTotalPrice = sale.getCurrentTotalPrice();
        this.snapshotOfPurscasedItems = sale.getCurrentPurchasedListOfItems();
 
    }

    public double getTheCurrentTotalPrice(){
        return snapshotOfTheCurrentTotalPrice;
    }

    public ArrayList<ItemDTO> getTheListOfPurschasedItems(ArrayList<ItemDTO> purchased){
        return snapshotOfPurscasedItems;
    }


    
}
