package se.kth.iv1350.seminar3.controller;

import se.kth.iv1350.seminar3.modell.Payment;
import se.kth.iv1350.seminar3.modell.Sale;
import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.view.View;
import se.kth.iv1350.seminar3.integration.InventorySystem;
import se.kth.iv1350.seminar3.integration.AccountingSystem;
import se.kth.iv1350.seminar3.integration.Printer;
import se.kth.iv1350.seminar3.integration.DiscountRegister;
import se.kth.iv1350.seminar3.modell.SaleLog;

/**
 * The Controller class acts as the mediator between the view and the model.
 * It handles user actions, fetches data from the model, and decides the view to respond with.
 */
public class Controller {

    private AccountingSystem accSys;
    private DiscountRegister discount;
    private InventorySystem invSys;
    private Printer printer;

    private Sale sale;
    private SaleLog saleLog;

    // Data transfer objects
    private SaleDTO saleDTO;
    private ItemDTO itemDTO;

  

    /**
     * Initializes a new instance of Controller.
     * It sets up all system components required to process sales.
     */

    public Controller() {
        accSys = new AccountingSystem();
        discount = new DiscountRegister();
        invSys = new InventorySystem();
        printer = new Printer();
        saleLog = new SaleLog();
    }

    /**
     * Starts a new sale transaction by initializing a Sale object.
     */
    public void startSale() {
        sale = new Sale();
    }
    
    /**
     * Adds an item to the current sale and updates the sale information.
     *
     * @param itemDTO the item to add
     * @param quantity the quantity of the item
     * @return updated sale information as a SaleDTO
     */
    public SaleDTO scanItem(ItemDTO itemDTO, int quantity) {
        if (sale.findItemInfo(itemDTO)) {
            saleDTO = new SaleDTO(sale);
        }
        return saleDTO;
    }

    /**
     * Ends the current sale process.
     * @return The calculated total price including tax.
     */
    public void endSale() {
        return scanItem(null); 
    }

    /**
     * Processes payment for the current sale.
     *
     * @param amountPaid the amount paid by the customer
     */
    public void pay(double amountPaid) {
        currentTotalPrice = sale.getCurrentTotalPrice();
        Payment payment = new Payment(currentTotalPrice, amountPaid);
    }    
}
