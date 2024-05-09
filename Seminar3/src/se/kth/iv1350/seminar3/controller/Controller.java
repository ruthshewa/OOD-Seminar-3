package se.kth.iv1350.seminar3.controller;

import se.kth.iv1350.seminar3.view.View;
import se.kth.iv1350.seminar3.modell.Payment;
import se.kth.iv1350.seminar3.modell.Receipt;
import se.kth.iv1350.seminar3.modell.Sale;
import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.DiscountDTO;
import se.kth.iv1350.seminar3.integration.InventorySystem;
import se.kth.iv1350.seminar3.integration.AccountingSystem;
import se.kth.iv1350.seminar3.integration.DiscountRegister;
import se.kth.iv1350.seminar3.integration.Printer;




/**
 * The Controller class acts as the mediator between the view and the model.
 * It handles user actions, fetches data from the model, and determines the response for the view.
 */
public class Controller {

    private AccountingSystem accSys;
    private DiscountRegister discountReg;
    private InventorySystem invSys;
    private Printer printer;
    private Payment payment;
    private Sale sale;
    private Receipt receipt;

    // Data transfer objects
    private SaleDTO saleDTO;
    private ItemDTO itemDTO;

    private double currentTotalPrice;
    private double totalPriceAfterDiscount;
    private int customerID;
    private Printer printed;
  


    /**
     * Initializes a new instance of Controller.
     * It sets up all system components required to process sales.
     */

    public Controller() {
        accSys = new AccountingSystem();
        discountReg = new DiscountRegister();
        invSys = new InventorySystem();
        printer = new Printer();
    }

    /**
     * Starts a new sale transaction by initializing a Sale object.
     */
    public void startSale() {
        sale = new Sale();
    }
    
     /**
     * Adds an item to the current sale and updates the sale information.
     * If the item is found and added successfully, it updates the SaleDTO with new sale data.
     *
     * @param itemDTO the item to add
     * @param quantity the quantity of the item
     * @return updated sale information as a SaleDTO
     */
    public SaleDTO scanItem(int itemID, int quantity) {

        ItemDTO itemDTO= invSys.fetchIteminfo(itemID);
        SaleDTO saleDTO =  sale.addItem(itemDTO, quantity);
        return saleDTO;
    }

    /**
     * Ends the current sale process.
     * @return The calculated total price including tax.
     */
    public double endSale() {
        currentTotalPrice = sale.getCurrentTotalPrice();
        return currentTotalPrice;
    }

     /**
     * Processes payment for the current sale, applies any available discounts,
     * prints the receipt, and updates external systems with the sale information.
     *
     * @param amountPaid the amount paid by the customer
     * @param paymentMethod the method used for payment
     */
    public void pay(double amountPaid, String paymentMethod) {
        
        // Should this be in view
        payment = new Payment( amountPaid, totalPriceAfterDiscount, paymentMethod);
        receipt = new Receipt(payment,saleDTO);
        printer.print(receipt);
        updateExternalSystems();
        
    }    


    /**
 * Applies a discount to the sale based on different criteria.
 * This method handles three scenarios:
 * 1. Item-based discount calculation.
 * 2. Total cost-based percentage discount.
 * 3. Customer ID based percentage discount.
 *
 * @param saleDTO List of all purchased items in the sale.
 * @param currentTotalPrice Total cost of all items before any discount is applied.
 * @param customerId Unique identifier for the customer.
 * discountReg The discount register containing discount configurations.
 * @return The total price after applying the applicable discount.
 */

    public void requestDiscount(int customerID) {
        DiscountDTO discountDTO = discountReg.fetchDiscountFromRegister(customerID, saleDTO, currentTotalPrice);
        totalPriceAfterDiscount = sale.applyDiscount(discountDTO);       
    }

     /**
     * Updates external systems with the details of the current sale.
     * Sends sale information to the inventory and accounting systems for processing.
     */
     private void updateExternalSystems() {
        invSys.sendSaleInfo(saleDTO);
        accSys.sendSaleInfo(saleDTO);
    }

    
}
