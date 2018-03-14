package com.loyalty.model;

import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Setter
public class ShoppingCart {
    private List<CartItem> shoppingItems;

    private Double total;

    // 2011-12-03T10:15:30+01:00
    private ZonedDateTime shoppingDateTime;

    private String customerId;

    private Boolean discounted;

    public ShoppingCart() {
        discounted = false;
    }

    public ShoppingCart(String customerId) {
        this();
        this.customerId = customerId;
    }

    public ShoppingCart(List<CartItem> shoppingItems, Double total, ZonedDateTime shoppingDateTime, String customerId) {
        this();
        this.shoppingItems = shoppingItems;
        this.total = total;
        this.shoppingDateTime = shoppingDateTime;
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "shoppingItems=" + shoppingItems +
                ", total=" + total +
                ", shoppingDateTime=" + shoppingDateTime +
                ", customerId='" + customerId + '\'' +
                ", discounted=" + discounted +
                '}';
    }
}
