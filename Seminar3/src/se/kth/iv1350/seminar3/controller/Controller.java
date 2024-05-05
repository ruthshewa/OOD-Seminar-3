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


public class Controller {

    private AccountingSystem accSys;
    private DiscountRegister discount;
    private InventorySystem invSys;
    private Printer printer;

    private Sale sale;
    private SaleLog saleLog;


    private SaleDTO saleDTO;
    private ItemDTO itemDTO;

    private double currentTotalPrice;

    
    public  Controller(){
        accSys=new AccountingSystem();
        discount = new DiscountRegister();
        invSys = new InventorySystem();
        printer = new Printer();
        saleLog = new SaleLog();

    }

    public void startSale(){
        sale = new Sale();
    }
    

    public SaleDTO scanItem(ItemDTO itemDTO, int quantity){

        if(sale.findItemInfo(itemDTO)){

            saleDTO = new SaleDTO (sale);
        }

        return saleDTO;

    }

     public void endSale(){
       // sale.getCurrentTotalPrice();
        scanItem(null);

    }


    public void pay(double amountPaid){
        

        currentTotalPrice= sale.getCurrentTotalPrice();
        Payment payment= new Payment(currentTotalPrice, amountPaid);

    }

      
}

