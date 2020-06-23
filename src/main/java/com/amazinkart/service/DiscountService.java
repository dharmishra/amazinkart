package com.amazinkart.service;

import com.amazinkart.model.DiscountType;
import com.amazinkart.model.ProductDetails;

@FunctionalInterface
public interface DiscountService {
    public ProductDetails applyDiscount(DiscountType discountType, ProductDetails productDetails);
}
