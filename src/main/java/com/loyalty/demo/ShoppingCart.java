package com.loyalty.demo;

import com.loyalty.demo.service.ShoppingCartProcessor;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

/**
 * Created by abakulin on 27/01/2018.
 */
@Getter
@Setter
public class ShoppingCart {
    List<CartItem> shoppingItems;

    Double total;

    // 2011-12-03T10:15:30+01:00
    ZonedDateTime shoppingDateTime;

    String customerId;

    public ShoppingCart() {
    }

    public ShoppingCart(String customerId) {
        this.customerId = customerId;
    }

    public ShoppingCart(List<CartItem> shoppingItems, Double total, ZonedDateTime shoppingDateTime, String customerId) {
        this.shoppingItems = shoppingItems;
        this.total = total;
        this.shoppingDateTime = shoppingDateTime;
        this.customerId = customerId;
    }
}
