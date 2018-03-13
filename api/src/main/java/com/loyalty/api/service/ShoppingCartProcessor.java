package com.loyalty.api.service;

import com.loyalty.api.dto.BatchShoppingCart;
import com.loyalty.api.model.ShoppingCart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ShoppingCartProcessor {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartProcessor.class);

    public String process(ShoppingCart shoppingCart) {
        log.info("Shopping cart has been processed");
        return "Shopping cart has been processed";
    }

    public String processBatch(BatchShoppingCart batch) {
        log.info("Shopping cart Batch has been processed");
        return "Shopping cart Batch has been processed";
    }
}
