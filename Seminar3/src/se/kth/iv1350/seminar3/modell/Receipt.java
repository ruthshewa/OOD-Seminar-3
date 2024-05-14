package se.kth.iv1350.seminar3.modell;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.DecimalFormat;
import se.kth.iv1350.seminar3.dto.ItemDTO;

public class Receipt {
    private LocalDateTime timeOfReceipt; // Stores the exact time the receipt is generated
    private final Sale sale; // Reference to the Sale object associated with this receipt
    private double amountPaidByCustomer; // The total amount paid by the customer
    private double totalSaleAmount; // The total amount of the sale after any calculations
    private String methodOfPayment; // The payment method used by the customer

    /**
     * Constructs a new Receipt object.
     * 
     * @param paymentTransaction The payment transaction details
     * @param sale The sale associated with this receipt
     */
    public Receipt(Payment paymentTransaction, Sale sale) {
        this.timeOfReceipt = LocalDateTime.now(); // Capturing the current time as the time of receipt
        this.sale = sale;
        this.amountPaidByCustomer = paymentTransaction.getAmountPaid();
        this.totalSaleAmount = paymentTransaction.getTotalSaleAmount();
        this.methodOfPayment = paymentTransaction.getMethodOfPayment();
    }

    /**
     * Returns the time when the receipt was generated.
     * 
     * @return The timestamp of receipt creation.
     */
    public LocalDateTime getTimeOfReceipt() {
        return timeOfReceipt;
    }

    /**
     * Creates a formatted string that represents the receipt.
     * 
     * @return A string representing the formatted receipt.
     */
    public String receiptPaperFormat() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#.00");

        StringBuilder builder = new StringBuilder();
        builder.append("------------RECEIPT------------\n")
               .append("Store Name: Ruth Store\n")
               .append("Store Address: Råcksta gårdsväg 25 t\n")
               .append("City: Stockholm\n")
               .append("Sale ID: ").append(sale.getSaleID()).append("\n")
               .append("Time of Sale: ").append(timeOfReceipt.format(dtf)).append("\n")
               .append("Items Purchased:\n").append(formatPurchasedItems(df)).append("\n")
               .append("Total cost (incl VAT): ").append(df.format(sale.getCurrentTotalPrice())).append("\n")
               .append("Amount Paid: ").append(df.format(amountPaidByCustomer)).append("\n")
               .append("Payment Method: ").append(methodOfPayment).append("\n")
               .append("Change Given: ").append(df.format(amountPaidByCustomer - totalSaleAmount)).append("\n")
               .append("--------------------------------\n")
               .append("Tack för besök, Välkommen åter\n")
               .append("Öppet köp 30 dagar\n")
               .append("Mot uppvisande av kvitto\n")
               .append("Kundtjänst: 0733823065\n")
               .append("--------------------------------\n");

        return builder.toString();
    }

     /**
     * Retrieves a list of purchased items.
     * 
     * @return A list of ItemDTOs representing the items purchased.
     */
    public ArrayList<ItemDTO> getPurchasedItems() {
        return sale.getPurchasedItems();
    }


    /**
     * Formats the list of purchased items for the receipt.
     * 
     * @param df The DecimalFormat to format item prices.
     * @return A formatted string listing all purchased items.
     */
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
