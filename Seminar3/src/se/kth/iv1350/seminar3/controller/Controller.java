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
    private Printer printed;


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
     * @return updated sale information for the view
     */
    public ItemDTO scanItem(int itemID, int quantity) {// can we not return ItemDTO to the view 

        itemDTO= invSys.fetchIteminfo(itemID);
        sale.addItem(itemDTO, quantity);
        return itemDTO;
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
    public void pay(double amountPaid, double discount, String paymentMethod) {
        
        double TotalPriceAfterDiscountApplied = sale.getCurrentTotalPrice() - discount;
        System.out.println("blabla finalTotalPrice: " + TotalPriceAfterDiscountApplied+ " current sale price: " + sale.getCurrentTotalPrice());

        payment = new Payment(amountPaid, TotalPriceAfterDiscountApplied, paymentMethod);

        System.out.println("yes Payment " + payment.getCustomerChange());

        receipt = new Receipt(payment, sale);
        printer.print(receipt);
        updateExternalSystems(saleDTO);
    } 

    public double requestDiscount(int customerId) {
        saleDTO = new SaleDTO(sale);
        double totalPrice = sale.getCurrentTotalPrice();
        return discountReg.fetchDiscountFromRegister(customerId, saleDTO, totalPrice);
    }


     /**
     * Updates external systems with the details of the current sale.
     * Sends sale information to the inventory and accounting systems for processing.
     */
    private void updateExternalSystems(SaleDTO saleDTO) {
        invSys.sendSaleInfo(saleDTO);
        accSys.sendSaleInfo(saleDTO);
    }

    
}
