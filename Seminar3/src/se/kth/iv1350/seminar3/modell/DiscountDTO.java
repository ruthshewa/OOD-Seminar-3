package se.kth.iv1350.seminar3.modell;

/**
 * Represents a discount in both amount and percentage rate.
 */
public class DiscountDTO {
    private double discountAmount; // Fixed amount discount
    private double discountRate; // Percentage discount

    /**
     * Creates an instance of DiscountDTO.
     * 
     * @param discountAmount The fixed amount of discount.
     * @param discountRate The percentage rate of discount.
     */
    public DiscountDTO(double discountAmount, double discountRate) {
        this.discountAmount = discountAmount;
        this.discountRate = discountRate;
    }

    /**
     * Gets the fixed amount of discount.
     * 
     * @return The discount amount.
     */
    public double getDiscountAmount() {
        return discountAmount;
    }

    /**
     * Gets the percentage rate of discount.
     * 
     * @return The discount rate.
     */
    public double getDiscountRate() {
        return discountRate;
    }

    /**
     * Applies this discount to a given price.
     * 
     * @param originalPrice The original price before discount.
     * @return The price after applying the discount.
     */
    public double applyDiscount(double originalPrice) {
        double discountedPrice = originalPrice - discountAmount;
        discountedPrice -= discountedPrice * (discountRate / 100);
        return discountedPrice;
    }
}
