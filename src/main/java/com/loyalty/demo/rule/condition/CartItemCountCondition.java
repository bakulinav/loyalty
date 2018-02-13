package com.loyalty.demo.rule.condition;

import com.loyalty.demo.Condition;
import com.loyalty.demo.ShoppingCart;
import lombok.Getter;

@Getter
public class CartItemCountCondition extends Condition<String> {

    private Integer productCount;

    public CartItemCountCondition(String attribute, String productName, Integer productCount) {
        super(attribute, productName);
        this.productCount = productCount;
    }

    @Override
    public boolean isMatch(ShoppingCart cart) {
        return cart.getShoppingItems().stream()
                .anyMatch(ci -> ci.getProduct().equals(this.value)
                        && ci.getCount() >= this.productCount);
    }
}
