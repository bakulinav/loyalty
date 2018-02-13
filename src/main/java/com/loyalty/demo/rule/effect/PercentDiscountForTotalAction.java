package com.loyalty.demo.rule.effect;

import com.loyalty.demo.RuleAction;
import com.loyalty.demo.ShoppingCart;
import lombok.Getter;

@Getter
public class PercentDiscountForTotalAction extends RuleAction<Double> {

    private final Double discountValue;

    public PercentDiscountForTotalAction(String scope, String action, Double discountValue) {
        super(scope, action);
        this.discountValue = discountValue;
    }

    @Override
    public Double calculate(ShoppingCart cart) {
        PercentDiscountExplain explain = new PercentDiscountExplain(this, cart, cart.getTotal(), cart.getTotal() * (1 - this.discountValue));

        return explain.getEffect();
    }

    public static class PercentDiscountExplain extends RuleActionExplain<PercentDiscountForTotalAction, ShoppingCart, Double> {
        public PercentDiscountExplain(PercentDiscountForTotalAction action,
                                      ShoppingCart target,
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
