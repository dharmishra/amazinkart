package com.amazinkart.util;

import com.amazinkart.Main;
import com.amazinkart.model.CurrencyRates;
import com.amazinkart.model.ProductDetails;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.logging.Logger;

public class HttpUtil {

    private static final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_1_1)
            .connectTimeout(Duration.ofSeconds(10))
            .build();
    private  static final Gson gson = new Gson();
    public static final String EXCHANGERATESAPI_IO_LATEST_BASE_INR = "https://api.exchangeratesapi.io/latest?base=INR";
    public static final String Product_DETAIL_REQUEST = "https://api.jsonbin.io/b/5d31a1c4536bb970455172ca/latest";
    private  static  final Logger logger = Logger.getLogger(Main.class.getName());
    public static CurrencyRates getCurrencyResponseInINR() throws IOException, InterruptedException {
        logger.info("Fetching currency Exchange Service");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(EXCHANGERATESAPI_IO_LATEST_BASE_INR))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();
        HttpResponse<String> currencyRateResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Type currencyType = new TypeToken<CurrencyRates>(){}.getType();
        return gson.fromJson(currencyRateResponse.body(), currencyType);
    }

    public static ArrayList<ProductDetails> readProductDetails() throws IOException, InterruptedException {
        logger.info("Fetching product details  Service");
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(Product_DETAIL_REQUEST))
                .setHeader("User-Agent", "Java 11 HttpClient Bot") // add request header
                .build();

        HttpResponse<String> productResponse = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        Type productDetailType = new TypeToken<ArrayList<ProductDetails>>(){}.getType();
        return gson.fromJson(productResponse.body(), productDetailType);
    }
}
