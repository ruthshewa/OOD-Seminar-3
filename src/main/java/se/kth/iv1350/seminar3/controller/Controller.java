package se.kth.iv1350.seminar3.controller;

import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.SaleDTO;
import se.kth.iv1350.seminar3.integration.AccountingSystem;
import se.kth.iv1350.seminar3.integration.DiscountRegister;
import se.kth.iv1350.seminar3.integration.InventorySystem;
import se.kth.iv1350.seminar3.integration.Printer;
import se.kth.iv1350.seminar3.modell.Payment;
import se.kth.iv1350.seminar3.modell.Receipt;
import se.kth.iv1350.seminar3.modell.Sale;


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
    private int discountValue = 23;
    private SaleDTO saleDTO;
    private double currentTotalPriceBeforeDiscount;
    private int oneItem = 1;

    /**
     * Creates a new instance, initializing all external system handlers.
     */
    public Controller() {
        accSys = new AccountingSystem();
        discountReg = new DiscountRegister();
        invSys = new InventorySystem();
        printer = new Printer();
    }

    /**
     * Starts a new sale transaction.
     */
    public void startSale() {
        sale = new Sale();
    }


    public Sale getCurrentSale() {
        return sale;
    }

    /**
     * Searches for matching item in the sale
     * If the item is not found, it fetches the itemDTO from the inventory system and adds it to the sale with the given quantity.
     * Otherwise the item founded DTO, increases with givem quantity
     * @param itemID The identifier of the item to add.
     * @param quantity The quantity of the item.
     * @return The ItemDTO of the added item.
     */
    public ItemDTO scanItem(int itemID, int quantity){

        ItemDTO itemDTO = sale.itemAlreadyInSale(itemID);
        if (itemDTO != null) {
            sale.increaseItemQuantity(itemDTO, quantity);
            return itemDTO;
        }
        itemDTO = invSys.fetchIteminfo(itemID);
        sale.addItem(itemDTO, quantity);
        return itemDTO;
    }





    /**
     * Ends the current sale process.
     * @return The calculated total price including tax.
     */
    public double endSale() {
        saleDTO = new SaleDTO(sale);
        currentTotalPriceBeforeDiscount= sale.getCurrentTotalPrice();
        return currentTotalPriceBeforeDiscount;
    }

    /**
     * The payment transaction for the current sale occurs here. Any available discount is applied,
     * the receipt is printed and the external systems are updated given the with the sale information.
     *
     * @param amountPaid the amount paid by the customer
     * @param paymentMethod the method used for payment
     * @param discount is the value that should be reduced from the totalprice of the current sale
     */

    public void pay(double amountPaid, double discount, String paymentMethod) {

        payment = new Payment(amountPaid, currentTotalPriceBeforeDiscount - discount, paymentMethod);
        receipt = new Receipt(payment, sale);
        printer.print(receipt);
        saleDTO = new SaleDTO(sale);
        updateExternalSystems(saleDTO);

    }

    /**
     * The customer asks for a discount.
     * @param customerId is number sequence that uniquely identifies a customer.
     * @return discount is the value that should be reduced from the totalprice of the sale
     */


    public double requestDiscount(int customerId) {
        return  sale.applyDiscount(23);
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
