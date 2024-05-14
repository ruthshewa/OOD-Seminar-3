package se.kth.iv1350.seminar3.view;

import java.text.DecimalFormat;
import java.util.Random;

import se.kth.iv1350.seminar3.controller.Controller;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.modell.Payment;
import se.kth.iv1350.seminar3.modell.Receipt;
import se.kth.iv1350.seminar3.modell.Sale;
import se.kth.iv1350.seminar3.startup.*;;
/**
 * Simulates user/Casheir interactions.
 */

public class View {
    private Controller contr;
    public int quantity;
    public double totalPrice;
    private Sale sale;

    public View(Controller contr){

        this.contr = contr;
    }


 /**
     * Simulates a user adding items, requesting discounts, and making a payment.
     */
    public void runFakeExecution() {
        System.out.println("Starting a new sale...");

        contr.startSale();

        // Simulate adding items to the sale
        System.out.println("Adding items to the sale...");
        ItemDTO item1 = contr.scanItem(1, 2); // Adding 2 units of item with ID 1
        System.out.println("Added item: " + item1.getItemName() + ", quantity: " + item1.getQuantity());

        ItemDTO item2 = contr.scanItem(2, 3); // Adding 3 units of item with ID 2
        System.out.println("Added item: " + item2.getItemName() + ", quantity: " + item2.getQuantity());

        
        // Simulate ending the sale
        contr.endSale();
        System.out.println("Sale ended. Total price: " + contr.endSale());

        // Simulate if customer requests discount otherwise go to pay
        boolean customerRequestsDiscount = true; // Simulated flag for requesting a discount
        int customerId = 1111; // Simulated customer ID
        double discount = 0; // Simulated discount value

        if (customerRequestsDiscount) {
             discount = contr.requestDiscount(customerId);
             System.out.println("DiscountAmount " + discount);

        }
        contr.pay(200,  discount, "Cash");
        System.out.println("Payment completed.");
      
    }

}
