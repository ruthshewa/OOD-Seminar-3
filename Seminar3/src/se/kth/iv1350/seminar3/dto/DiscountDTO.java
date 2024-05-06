package se.kth.iv1350.seminar3.dto;

public class DiscountDTO {
    private double discountAmount; // Fixed discount amount
    private double discountRate;   // Discount rate in percentage

    public DiscountDTO(double discountAmount, double discountRate) {
        this.discountAmount = discountAmount;
        this.discountRate = discountRate;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double applyDiscount(double totalPrice) {
        double discountValue = this.discountAmount + (totalPrice * (this.discountRate / 100));
        return Math.max(0, totalPrice - discountValue);
    }
}
