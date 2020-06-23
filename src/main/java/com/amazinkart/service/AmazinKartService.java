package com.amazinkart.service;

import com.amazinkart.Main;
import com.amazinkart.model.Currency;
import com.amazinkart.model.CurrencyRates;
import com.amazinkart.model.DiscountType;
import com.amazinkart.model.ProductDetails;
import com.amazinkart.util.HttpUtil;
import com.google.gson.Gson;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class AmazinKartService {
    private  static final Gson gson = new Gson();
    private  static  final Logger logger = Logger.getLogger(Main.class.getName());

    public void applyDiscountToCart(DiscountType discountType) {
        try {
            CurrencyRates currencyRates = HttpUtil.getCurrencyResponseInINR();
            ArrayList<ProductDetails> productDetails = HttpUtil.readProductDetails();
            DiscountType finalDiscountType = discountType;
            productDetails = productDetails.stream().map(productDetails1 ->  {
                if (!productDetails1.getCurrency().equals(Currency.INR)) {
                    productDetails1 = productDetails1
                            .toBuilder()
                            .currency(Currency.INR)
                            .price(currencyRates.getRates().get(productDetails1.getCurrency().name()))
                            .build();
                }
                return productDetails1;
            }).map( productDetails1 -> {
                DiscountService discountService = new DiscountServiceImpl();
                return discountService.applyDiscount(finalDiscountType, productDetails1);
            }).collect(Collectors.toCollection(ArrayList::new));

            logger.info("Discount applied");
            String content = gson.toJson(productDetails);
            logger.info("Writing details to file output.json");
            Files.write(Paths.get("target"+ File.separator+"output.json"), content.getBytes());
            logger.info("Product discount file output.json is created successfully");

        } catch (Exception e) {
            logger.warning("Error occurred while applying discount. Please try again ...." + e.getMessage());
        }
    }
}
