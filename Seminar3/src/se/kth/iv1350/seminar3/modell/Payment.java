package se.kth.iv1350.seminar3.modell;

import se.kth.iv1350.seminar3.dto.SaleDTO;

/**
 * Represents a payment transaction.
 * This class handles the calculation of change due to the customer after a sale.
 */
public class Payment {
    private double currentTotalPrice;
    private double amountPaid;

    /**
     * Constructs a Payment instance.
     *
     * @param currentTotalPrice the total price of the current sale
     * @param amountPaid the amount paid by the customer
     */
    public Payment(double currentTotalPrice, double amountPaid) {
        this.currentTotalPrice = currentTotalPrice;
        this.amountPaid = amountPaid;
    }

    /**
     * Calculates the change due to the customer based on the amount paid and total price.
     * This method is private because it is an internal utility function.
     *
     * @param amountPaid the amount paid by the customer
     * @param currentTotalPrice the total price of the sale
     * @return the calculated change to be given to the customer
     */
    private double calculateCustomerChange(double amountPaid, double currentTotalPrice) {
        return amountPaid - currentTotalPrice;
    }

    /**
     * Public method to get the calculated change for the customer.
     * This leverages the private method to encapsulate the calculation logic.
     *
     * @return the change that should be given back to the customer
     */
    public double getCustomerChange() {
        return calculateCustomerChange(amountPaid, currentTotalPrice);
    }    
}
