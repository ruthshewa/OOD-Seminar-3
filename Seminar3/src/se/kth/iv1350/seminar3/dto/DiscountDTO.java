package se.kth.iv1350.seminar3.dto;



public class DiscountDTO {
    private double discountAmount;
    private double discountRate;

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
}