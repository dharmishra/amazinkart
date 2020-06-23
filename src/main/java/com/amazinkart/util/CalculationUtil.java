package com.amazinkart.util;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationUtil {

    public static double getPercentage(double val, int percentage) {

        double discount =  val*(percentage/100d);
        return  BigDecimal.valueOf(discount)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
