package com.amazinkart.model;

import lombok.*;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CurrencyRates {
    private Map<String, Double> rates;
    private String base;
    private String date;
}
