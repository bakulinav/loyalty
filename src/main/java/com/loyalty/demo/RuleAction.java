package com.loyalty.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Effect of applying Rule (Discount) to a CartItem.
 * Ex.:
 *
 *  - Discount 5% for every cart item
 */
@NoArgsConstructor
@Getter
public abstract class RuleAction<R> {
    /**
     * Scope of effect.
     * Ex.: ShoppingCart, CartItem, CustomerProfile
     */
    private String scope;
    /**
     * Action of Rule:
     * Ex.: Discount 5% to price/total
     * or Add gift product to a shopping cart.
     */
    private String action;

    public RuleAction(String scope, String action) {
        this.scope = scope;
        this.action = action;
    }

    abstract public R calculate(ShoppingCart cart);
}
