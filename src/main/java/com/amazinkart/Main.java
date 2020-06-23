package com.amazinkart;

import com.amazinkart.model.DiscountType;
import com.amazinkart.service.AmazinKartService;

import java.util.logging.Logger;

public class Main {

    private  static  final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {

        String discountTypeArg;
        DiscountType discountType = null;
        if (args != null && args.length > 0) {
            discountTypeArg = args[0];
            if (discountTypeArg.equals("promotionSetA")) {
                discountType = DiscountType.PROMOTION_SET_A;
            } else  if (discountTypeArg.equals("promotionSetB")) {
                discountType = DiscountType.PROMOTION_SET_B;
            } else {
                logger.warning("Incorrect argument provided, Existing system");
                System.exit(0);
            }
        }  else {
            logger.warning("No argument provided, Existing system");
            System.exit(0);
        }
        logger.info("Getting discount for products for discount Type " + discountType);
        AmazinKartService service = new AmazinKartService();
        service.applyDiscountToCart(discountType);
    }
}
