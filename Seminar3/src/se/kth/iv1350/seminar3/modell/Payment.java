package se.kth.iv1350.seminar3.modell;

import se.kth.iv1350.seminar3.dto.SaleDTO;

public class Payment {
    private double currentTotalPrice;
    private double amountPaid;

    public Payment(double currentTotalPrice, double amountPaid){
        this.currentTotalPrice = currentTotalPrice;
        this.amountPaid = amountPaid;
    }

    private double calculateCustomerChange(double amountPaid, double currentTotalPrice) {
        double customerChange =  amountPaid - currentTotalPrice;
        return customerChange;
    }


    public double getCustomerChange(){
        return calculateCustomerChange(amountPaid, currentTotalPrice);
    }  
    

}
