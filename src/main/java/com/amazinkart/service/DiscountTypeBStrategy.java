package com.amazinkart.service;

import com.amazinkart.model.Discount;
import com.amazinkart.model.ProductDetails;
import com.amazinkart.util.CalculationUtil;

public class DiscountTypeBStrategy implements DiscountStrategy {

    private Discount applyAndGetInventoryBasedDiscount(ProductDetails productDetails) {
        if (productDetails.getInventory() > 20d) {
            double amount =  CalculationUtil.getPercentage(productDetails.getPrice(), 12);
            return new Discount(amount, "get 12% off");
        } else {
            return new Discount(0, "No Discount ");
        }
    }


    public Discount applyAndGetDiscountType(ProductDetails productDetails) {
        double max = 0d;
        String tag = "";
        Discount inventoryBasedDiscount = applyAndGetInventoryBasedDiscount(productDetails);
        if (max < inventoryBasedDiscount.getAmount()) {
            max = inventoryBasedDiscount.getAmount();
            tag = inventoryBasedDiscount.getDiscountTag();
        }

        if (max == 0d && productDetails.getPrice() > 1000d) {
            max = CalculationUtil.getPercentage(productDetails.getPrice(), 2);
            tag = "get 2% off";
        }
        return new Discount(max, tag);
    }

}
