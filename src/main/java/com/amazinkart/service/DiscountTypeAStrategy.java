package com.amazinkart.service;

import com.amazinkart.model.Discount;
import com.amazinkart.model.ProductDetails;
import com.amazinkart.util.CalculationUtil;

import java.util.Set;

public class DiscountTypeAStrategy implements DiscountStrategy {

    public static final Set<String> ITEM_CATEGORY = Set.of("electronics", "furnishing");

    private Discount applyAndGetRegionBasedDiscount(ProductDetails productDetails) {
        if (productDetails.getOrigin().equals("Asia")) {
            double amount =  CalculationUtil.getPercentage(productDetails.getPrice(), 7);
            return new Discount(amount, "get 7% off");
        } else {
            return new Discount(0, "No Discount ");
        }
    }

    private Discount applyAndGetRatingBasedDiscount(ProductDetails productDetails) {
        if (productDetails.getRating() == 2) {
            double amount = CalculationUtil.getPercentage(productDetails.getPrice(), 4);
            return new Discount(amount, "get 5% off");
        } else if (productDetails.getRating() < 2) {
            double amount =  CalculationUtil.getPercentage(productDetails.getPrice(), 8);
            return new Discount(amount, "get 8% off");
        } else {
            return new Discount(0, "No Discount ");
        }
    }

    private Discount applyAndGetItemBasedDiscount(ProductDetails productDetails) {
        if (ITEM_CATEGORY.contains(productDetails.getCategory())
            && productDetails.getPrice() > 500) {
            double amount =  100d;
            return new Discount(amount, "get Rs 100 off");
        } else {
            return new Discount(0, "No Discount ");
        }
    }

    public Discount applyAndGetDiscountType(ProductDetails productDetails) {
        double max = 0d;

        Discount finalDiscount;
        String tag = "";
        Discount regionBasedDiscount = applyAndGetRegionBasedDiscount(productDetails);
        if (max < regionBasedDiscount.getAmount()) {
            max = regionBasedDiscount.getAmount();
            tag = regionBasedDiscount.getDiscountTag();
        }
        Discount ratingBasedDiscount = applyAndGetRatingBasedDiscount(productDetails);
        if (max < ratingBasedDiscount.getAmount()) {
            max = ratingBasedDiscount.getAmount();
            tag = ratingBasedDiscount.getDiscountTag();
        }

        Discount itemBasedDiscount = applyAndGetItemBasedDiscount(productDetails);
        if (max < itemBasedDiscount.getAmount()) {
            max = itemBasedDiscount.getAmount();
            tag = itemBasedDiscount.getDiscountTag();
        }

        if (max == 0d && productDetails.getPrice() > 1000d) {
            max = CalculationUtil.getPercentage(productDetails.getPrice(), 2);
            tag = "get 2% off";
        }
        return new Discount(max, tag);
    }

}
