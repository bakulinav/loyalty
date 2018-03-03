package com.loyalty.demo.api;

import com.loyalty.demo.ShoppingCart;
import com.loyalty.demo.service.ShoppingCartProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ShoppingCartProcessorController {

    ShoppingCartProcessor processor;

    @Autowired
    public ShoppingCartProcessorController(ShoppingCartProcessor processor) {
        this.processor = processor;
    }

    @PostMapping("/cart-processor")
    @ResponseBody
    public String processShoppingCart(@RequestBody ShoppingCart cart) {
        return processor.processShoppingCart(cart);
    }
}
