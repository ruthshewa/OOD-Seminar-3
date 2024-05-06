package se.kth.iv1350.seminar3.integration;

import java.util.HashMap;

public class DiscountRegister {

    private HashMap<Integer, DiscountDTO> discountMap;

    public DiscountRegister() {
        this.discountMap = new HashMap<>();
        initializeDiscounts();
    }

    private void initializeDiscounts() {
        // Example: Customer ID 101 gets a $20 off and 15% discount
        discountMap.put(101, new DiscountDTO(20, 15));
        // Additional discounts can be added here
    }

    public DiscountDTO fetchDiscountFromRegister(int customerId) {
        // Returns the discount for a given customer ID, or a default discount if no specific one exists
        return discountMap.getOrDefault(customerId, new DiscountDTO(0, 0));
    }
    
}
