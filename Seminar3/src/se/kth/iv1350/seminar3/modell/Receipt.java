package se.kth.iv1350.seminar3.modell;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.DecimalFormat;
import se.kth.iv1350.seminar3.dto.ItemDTO;

public class Receipt {
    private LocalDateTime timeOfReceipt;
    private final Sale sale;
    private double amountPaidByCustomer;
    private double totalSaleAmount;
    private String methodOfPayment;

   
    public Receipt(Payment paymentTransaction, Sale sale) {
        this.timeOfReceipt = LocalDateTime.now();
        this.sale = sale;
        this.amountPaidByCustomer = paymentTransaction.getAmountPaid();
        this.totalSaleAmount = paymentTransaction.getTotalSaleAmount();
        this.methodOfPayment = paymentTransaction.getMethodOfPayment();
    }
    

    public LocalDateTime getTimeOfReceipt() {
        return timeOfReceipt;
    }

    public ArrayList<ItemDTO> getPurchasedItems() {
        return sale.getPurchasedItems();
    }

   /**
     * Builds a receipt in the form of a String.
     * 
     * @return the receipt represented by a string.
     */
    public String receiptPaperFormat() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#.00");

        StringBuilder builder = new StringBuilder();
        builder.append("------------RECEIPT------------\n");
        builder.append("Store Name: Ruth Store\n");
        builder.append("Store Address: Råcksta gårdsväg 25 t\n");
        builder.append("City: Stockholm\n");
        builder.append("Sale ID: ").append(sale.getSaleID()).append("\n");
        builder.append("Time of Sale: ").append(timeOfReceipt.format(dtf)).append("\n");
        builder.append("Items Purchased:\n").append(formatPurchasedItems(df)).append("\n");
        builder.append("Total cost (incl VAT): ").append(df.format(sale.getCurrentTotalPrice())).append("\n");
        builder.append("Amount Paid: ").append(df.format(amountPaidByCustomer)).append("\n");
        builder.append("Payment Method: ").append(methodOfPayment).append("\n");
        builder.append("Change Given: ").append(df.format(amountPaidByCustomer - totalSaleAmount)).append("\n");
        builder.append("--------------------------------\n");
        builder.append("Tack för besök, Välkommen åter\n");
        builder.append("Öppet köp 30 dagar\n");
        builder.append("Mot uppvisande av kvitto\n");
        builder.append("Kundtjänst: 0733823065\n");
        builder.append("--------------------------------\n");

        return builder.toString();
    }

    private String formatPurchasedItems(DecimalFormat df) {
        StringBuilder items = new StringBuilder();
        for (ItemDTO item : getPurchasedItems()) {
            items.append(item.getItemName())  
                 .append("  ")
                 .append(item.getQuantity())
                 .append(" x ")
                 .append(df.format(item.getItemPrice()))  
                 .append("      ")
                 .append(df.format(item.getItemPrice() * item.getQuantity()))
                 .append("\n");
        }
        return items.toString();
    }
}
