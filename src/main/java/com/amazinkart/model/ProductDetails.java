package com.amazinkart.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@ToString
public class ProductDetails {

    private String category;
    private long inventory;
    private double rating;
    private Currency currency;
    private double price;
    private String origin;
    private String product;

    private Discount discount;

}
