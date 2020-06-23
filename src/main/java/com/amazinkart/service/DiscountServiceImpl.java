package com.amazinkart.service;

import com.amazinkart.model.Discount;
import com.amazinkart.model.DiscountType;
import com.amazinkart.model.ProductDetails;

public class DiscountServiceImpl implements DiscountService {

    @Override
    public ProductDetails applyDiscount(DiscountType discountType, ProductDetails productDetails) {
        Discount discount;
        DiscountStrategy discountStrategy = null;
        switch (discountType) {
            case PROMOTION_SET_A: {
                discountStrategy = new DiscountTypeAStrategy();
                discount = discountStrategy.applyAndGetDiscountType(productDetails);
            }
            break;
            case PROMOTION_SET_B: {
                discountStrategy = new DiscountTypeAStrategy();
                discount = discountStrategy.applyAndGetDiscountType(productDetails);
                break;
            }
            default: {
                discount = new Discount(0, "No Discount ");
            }
        }
        productDetails = productDetails.toBuilder().discount(discount).build();
        return productDetails;
    }
}
