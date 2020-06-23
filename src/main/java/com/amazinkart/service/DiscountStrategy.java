package com.amazinkart.service;

import com.amazinkart.model.Discount;
import com.amazinkart.model.ProductDetails;

public interface DiscountStrategy {
    Discount applyAndGetDiscountType(ProductDetails productDetails);
}
