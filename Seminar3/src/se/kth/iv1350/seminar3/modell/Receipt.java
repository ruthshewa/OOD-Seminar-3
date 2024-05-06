package se.kth.iv1350.seminar3.modell;

import java.time.LocalTime;
import java.util.ArrayList;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.SaleDTO;


public class Receipt {
    private LocalTime receiptTime;
 
    private final SaleDTO SaleDTO();
    private double amountPaid;

    public Receipt(Payment payment, Sale) {
        setTimeOfReceipt();
        this.amountPaid = payment.getAmountPaid();
        this.purchasedItems = purchasedItems;
    }

    private void setTimeOfReceipt(){
        receiptTime = LocalTime.now();
    }

    // Getter for receiptTime
    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    // Getter for purchasedItems
    public ArrayList<ItemDTO> getPurchasedItems() {
        return purchasedItems;
    }

    // Getter for amountPaid
    public double getAmountPaid() {
        return amountPaid;
    }
}



