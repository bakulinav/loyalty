package com.loyalty.demo.rule.effect;

import com.loyalty.demo.CartItem;
import com.loyalty.demo.RuleAction;
import com.loyalty.demo.ShoppingCart;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class PercentDiscountForProductAction extends RuleAction<Double> {

    private final Double discountValue;
    private final String productName;
    private List<PercentDiscountExplain> explains;

    public PercentDiscountForProductAction(String scope, String action, Double discountValue, String productName) {
        super(scope, action);
        this.discountValue = discountValue;
        this.productName = productName;
    }

    @Override
    public Double calculate(ShoppingCart cart) {
        this.explains = cart.getShoppingItems().stream()
                .filter(ci -> ci.getProduct().equals(this.productName))
                .map(ci -> new PercentDiscountExplain(this, ci, ci.getPrice(), ci.getPrice() * (1 - this.discountValue)))
                .collect(Collectors.toList());


        return this.explains.stream().mapToDouble(e -> e.getEffect()).sum();
    }

    @Getter
    public static class PercentDiscountExplain extends RuleActionExplain<PercentDiscountForProductAction, CartItem, Double> {
        public PercentDiscountExplain(PercentDiscountForProductAction action,
                                      CartItem target,
                                      Double valueBefore,
                                      Double valueAfter) {
            super(action, target, valueBefore, valueAfter);
        }

        @Override
        public Double getEffect() {
            return this.valueBefore - this.valueAfter;
        }
    }
}
