package com.loyalty.demo.rule.effect;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.loyalty.demo.RuleAction;
import lombok.Getter;

@Getter
public abstract class RuleActionExplain<A extends RuleAction, T, V> {
    protected final T target;
    protected final V valueBefore;
    protected final V valueAfter;
    @JsonBackReference
    protected final A action;

    public RuleActionExplain(A action, T target, V valueBefore, V valueAfter) {
        this.action = action;
        this.target = target;
        this.valueBefore = valueBefore;
        this.valueAfter = valueAfter;
    }

    abstract public V getEffect();
}
