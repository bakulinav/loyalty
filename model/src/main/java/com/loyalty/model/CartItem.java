package com.loyalty.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem {
    private String product;
    private String sku;
    private Double price;
    private Integer count;

    public CartItem() {}

    public CartItem(String product) {
        this.product = product;
    }

    public CartItem(String product, String sku, Double price, Integer count) {
        this(product);
        this.sku = sku;
        this.price = price;
        this.count = count;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "product='" + product + '\'' +
                ", sku='" + sku + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
