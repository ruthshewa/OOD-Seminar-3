package se.kth.iv1350.seminar3.view;

import java.text.DecimalFormat;
import se.kth.iv1350.seminar3.controller.Controller;
import se.kth.iv1350.seminar3.dto.ItemDTO;

/**
 * This class is a placeholder for the user interface.
 */
public class View {
    private Controller contr;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public View(Controller contr){
        this.contr = contr;
    }

    /**
     * Presents an item that has been added to the sale.
     */
    private void presentItem(ItemDTO item, int quantity) {
        System.out.println("Added item: " + item.getItemName() + ", quantity: " 
                           + quantity + ", Price per unit: " + df.format(item.getItemPrice()) 
                           + ", Total price: " + df.format(item.getItemPrice() * quantity));
    }

    /**
     * Simulates interactions with the sale system, such as adding items.
     */
    private void chooseItem(int itemId, int quantity) {
        
            ItemDTO foundItem = contr.scanItem(itemId, quantity);
            presentItem(foundItem, quantity);    
    }

    /**
     * Simulates a series of user actions, adding items, and completing the sale.
     */
    public void runFakeExecution() {
        System.out.println("Starting a new sale...");
        contr.startSale();

        chooseItem(1, 2);
        chooseItem(2,1);
        chooseItem(3,3);
        chooseItem(5,1);
        chooseItem(6, 4);  


        double totalPrice = contr.endSale();
        System.out.println("\n\nSale ended. Total price (Inc VAT): " + df.format(totalPrice));

        System.out.println("\nCustomer Requests Discount");
        boolean customerRequestsDiscount = true;
        int customerId = 1111;
        System.out.println("Customer hands out gives his customer ID:  "+ customerId);
        double discount = contr.requestDiscount(customerId);
        System.out.println("Customer got this amount discount: " + 23 + "  \n");

        contr.pay(500, totalPrice, "Cash");
        System.out.println("Payment of " + df.format(500) + " received. Change given: " 
                           + df.format(500 - totalPrice));

        System.out.println("\nInventory system has been updated");
        System.out.println("Accounting system has been updated");
    }
}
