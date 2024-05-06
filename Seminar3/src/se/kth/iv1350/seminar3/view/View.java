package se.kth.iv1350.seminar3.view;

import se.kth.iv1350.seminar3.controller.Controller;

import se.kth.iv1350.seminar3.modell.Sale;
/**
 * 
 * Kommentar
 */

public class View {
    private Controller contr;
    public int quantity;
    public double totalPrice;
    private Sale sale;

    public View(Controller contr){

        this.contr = contr;
    }

    public void execution(){
        contr.startSale();
    }

   

    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    public int getQuantity(){
        return quantity;
    }


    public void simulateUserInteraction() {
        int customerId = 101;  // Simulated customer ID
        controller.requestDiscount(customerId);
    }

}
