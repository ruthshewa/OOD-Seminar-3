package se.kth.iv1350.seminar3.modell;

import se.kth.iv1350.seminar3.dto.SaleDTO;


public class Payment {
    private double totalSaleAmount;
    private double amountPaidByCustomer;
    private String methodOfPayment;

    public Payment(double amountPaidByCustomer, double totalSaleAmount, String methodOfPayment) {
        this.amountPaidByCustomer = amountPaidByCustomer;
        this.totalSaleAmount = totalSaleAmount;
        this.methodOfPayment = methodOfPayment;
    }

    private double calculateChange(double amountPaid, double totalSaleAmount) {
        return amountPaid - totalSaleAmount;
    }

    /**
     * Public method to get the calculated change for the customer.
     * This leverages the private method to encapsulate the calculation logic.
     *
     * @return the change that should be given back to the customer
     */
    public double getCustomerChange() {
        return calculateChange(this.amountPaidByCustomer, this.totalSaleAmount);
    }

    /**
     * Returns the payment method used by the customer.
     *
     * @return the payment method used by the customer
     */
    public String getMethodOfPayment() {
        return this.methodOfPayment;
    }

    public double getAmountPaid() {
        return this.amountPaidByCustomer;
    }

    public double getTotalSaleAmount() {
        return this.totalSaleAmount;
    }
}
