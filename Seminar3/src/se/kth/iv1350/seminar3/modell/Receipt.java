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

    public Receipt(Payment payment, SaleDTO saleDTO) {
        setTimeOfReceipt();
        this.saleDTO= saleDTO;
        this.amountPaid = payment.getAmountPaid();

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
        return saleDTO.getTheListOfPurschasedItems();
    }


     /**
     * Builds a receipt in the form of a String
     * @return the receipt represented by a string
     */
    public String receiptPaperFormat() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        DecimalFormat df = new DecimalFormat("#.00");

        StringBuilder builder = new StringBuilder();
        builder.append("------------RECEIPT------------\n");
        builder.append("Store Name: Ruth Store\n");
        builder.append("Store Address:Råcksta gårdsväg 25 t\n");
        builder.append("City: Stockholm\n");
        builder.append("Sale ID: ").append(saleDTO.getSaleDTOID()).append("\n");
        builder.append("Time of Sale: ").append(receiptTime.format(dtf)).append("\n");
        builder.append("Items Purchased:\n").append(printPurchasedItems(df)).append("\n");
        builder.append("Total cost (incl VAT): ").append(df.format(saleDTO.getTheCurrentTotalPrice())).append("\n");
        builder.append("Discount: ").append(df.format(saleDTO.getDiscount())).append("\n");
        builder.append("Amount to Pay: ").append(df.format(amountPaid)).append("\n");
        builder.append("Payment Method: ").append(payment.paymentMethod).append("\n");
        builder.append("Amount Changed: ").append(df.format(amountPaid - saleDTO.getTotalCostWithVAT())).append("\n");
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
            items.append(item.getName())
                 .append("  ")
                 .append(item.getQuantity())
                 .append(" x ")
                 .append(df.format(item.getUnitPrice()))
                 .append("      ")
                 .append(df.format(item.getUnitPrice() * item.getQuantity()))
                 .append("\n");
        }
        return items.toString();
    }


    
}


// Store name
// store address
// store city
// sale id

// list of item purchased

// Total cost ( incl VAT )
// Discount
// Amount to pay
//=======
// PaymentMethod
//Amount changed

// Tack för besök, Välkommen åter
// öppet köp 30 dagar
// mot uppvisande av kvitto
// Kundtjänst: ruth


