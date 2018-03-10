package com.loyalty.api.controller;

import com.loyalty.api.dto.BatchShoppingCart;
import com.loyalty.api.model.ShoppingCart;
import com.loyalty.api.service.ShoppingCartProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShoppingCartProcessorController {

    private static final Logger log = LoggerFactory.getLogger(ShoppingCartProcessorController.class);

    ShoppingCartProcessor processor;

    @Autowired
    public ShoppingCartProcessorController(ShoppingCartProcessor processor) {
        this.processor = processor;
    }

    @PostMapping("/cart-processor")
    @ResponseBody
    public String processShoppingCart(@RequestBody ShoppingCart cart) {
        log.debug("Get Shopping cart for processing: " + cart.toString());
        return processor.process(cart);
    }

    @PostMapping("/batch-cart-processor")
    @ResponseBody
    public String processBatchShoppingCart(@RequestBody BatchShoppingCart batch) {
        log.debug("Get Shopping cart Batch for processing: " + batch.toString());
        return processor.processBatch(batch);
    }
}
