package se.kth.iv1350.seminar3.integration;

import se.kth.iv1350.seminar3.modell.Receipt;

public class Printer {
    
    public Printer(){

    }
     
    public void print(Receipt receipt) {
        receipt.receiptPaperFormat();
    }        
}
