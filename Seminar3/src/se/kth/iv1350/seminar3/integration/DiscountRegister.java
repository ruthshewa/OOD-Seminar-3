package se.kth.iv1350.seminar3.integration;

import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.dto.DiscountDTO;

public class DiscountRegister {

    private int customerID;
    private SaleDTO saleDTO;
    private double currentTotalPrice;



    public DiscountRegister() {
      
    }

//demo list 
// 29393993 20%


//demo list
// tomata 0.9kr/kg

//demo 
//500 kr 10kr for discount list

    public DiscountDTO fetchDiscountFromRegister(int customerId, SaleDTO saleDTO, double currentTotalPrice) {
        // Returns the discount Amount for a given customer ID, or a default discount if no specific one exists
        return null;
    }
    
}