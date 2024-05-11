package se.kth.iv1350.seminar3.modell;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.text.DecimalFormat;
import se.kth.iv1350.seminar3.dto.ItemDTO;
import se.kth.iv1350.seminar3.dto.SaleDTO;

public class Receipt {
    private LocalTime receiptTime;
    private final SaleDTO saleDTO;
    private double amountPaid;
    private String paymentMethod;
    private double totalCostWithVAT;

    /**
     * Constructs a Receipt.
     * 
     * @param payment The payment details.
     * @param saleDTO The sale data transfer object.
     */
    public Receipt(Payment payment, SaleDTO saleDTO) {
        setTimeOfReceipt();
        this.saleDTO = saleDTO;
        this.amountPaid = payment.getAmountPaid();
        this.paymentMethod = payment.getPaymentMethod();
        this.totalCostWithVAT = saleDTO.getTheCurrentTotalPrice();
    }

    private void setTimeOfReceipt() {
        receiptTime = LocalTime.now();
    }

    public LocalTime getReceiptTime() {
        return receiptTime;
    }

    public ArrayList<ItemDTO> getPurchasedItems() {
        return saleDTO.getTheListOfPurchasedItems();
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
        builder.append("Sale ID: ").append(saleDTO.getSaleDTOID()).append("\n");
        builder.append("Time of Sale: ").append(receiptTime.format(dtf)).append("\n");
        builder.append("Items Purchased:\n").append(printPurchasedItems(df)).append("\n");
        builder.append("Total cost (incl VAT): ").append(df.format(totalCostWithVAT)).append("\n");
        builder.append("Amount Paid: ").append(df.format(amountPaid)).append("\n");
        builder.append("Payment Method: ").append(paymentMethod).append("\n");
        builder.append("Amount Changed: ").append(df.format(amountPaid - totalCostWithVAT)).append("\n");
        builder.append("--------------------------------\n");
        builder.append("Tack för besök, Välkommen åter\n");
        builder.append("Öppet köp 30 dagar\n");
        builder.append("Mot uppvisande av kvitto\n");
        builder.append("Kundtjänst: 0733823065\n");
        builder.append("--------------------------------\n");

        return builder.toString();
    }

    private String printPurchasedItems(DecimalFormat df) {
        StringBuilder items = new StringBuilder();
        for (ItemDTO item : getPurchasedItems()) {
            items.append(item.getItemName())  // Ensure getItemName is correct
                 .append("  ")
                 .append(item.getQuantity())
                 .append(" x ")
                 .append(df.format(item.getItemPrice()))  // Ensure getItemPrice is correct
                 .append("      ")
                 .append(df.format(item.getItemPrice() * item.getQuantity()))
                 .append("\n");
        }
        return items.toString();
    }
}
