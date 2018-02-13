package com.loyalty.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public abstract class Condition<V> {
    protected String attribute;
    protected V value;

    public Condition(String attribute, V value) {
        this.attribute = attribute;
        this.value = value;
    }

    abstract public boolean isMatch(ShoppingCart cart);
}
