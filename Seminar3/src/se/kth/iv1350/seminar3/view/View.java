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

    public void runFakeexecution(){
        contr.startSale();
    }
//demo item and fake scan
   


//Is not needed
    public void simulateUserInteraction() {
        int customerId = 101;  // Simulated customer ID
        contr.requestDiscount(customerId);
    }

}
