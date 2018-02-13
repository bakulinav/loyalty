package com.loyalty.demo.rule.condition;

import com.loyalty.demo.Condition;
import com.loyalty.demo.ShoppingCart;
import lombok.Getter;

@Getter
public class ProductNameCondition extends Condition<String> {

    public ProductNameCondition(String attribute, String value) {
        super(attribute, value);
    }

    @Override
    public boolean isMatch(ShoppingCart cart) {
        return cart.getShoppingItems().stream()
                .anyMatch(ci -> ci.getProduct().equals(this.value));
    }
}
