package com.loyalty.demo;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by abakulin on 27/01/2018.
 */
@Getter
@Setter
public class CartItem {
    String product;
    String sku;
    Double price;
    Integer count;

    public CartItem() {
    }

    public CartItem(String product, String sku, Double price, Integer count) {
        this.product = product;
        this.sku = sku;
        this.price = price;
        this.count = count;
    }
}
