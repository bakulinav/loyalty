package com.loyalty.demo.rule.condition;

import com.loyalty.demo.Condition;
import com.loyalty.demo.ShoppingCart;
import lombok.Getter;

@Getter
public class CartTotalCondition extends Condition<Double> {
    public CartTotalCondition(String attribute, Double value) {
        super(attribute, value);
    }

    @Override
    public boolean isMatch(ShoppingCart cart) {
        return cart.getTotal() >= value;
    }
}
