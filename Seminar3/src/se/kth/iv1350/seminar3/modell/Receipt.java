package se.kth.iv1350.seminar3.modell;

import java.time.LocalTime;

public class Receipt {
    private  LocalTime receiptTime;

    public Receipt(){
        
    }

    private void setTimeOfReceipt(){
        receiptTime = LocalTime.now();
    }
    
}
