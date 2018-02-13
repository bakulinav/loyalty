package com.loyalty.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Created by abakulin on 07/02/2018.
 */
@NoArgsConstructor
@Getter
public class Rule {
    private Condition condition;
    private RuleAction<Double> effect;

    public Rule(Condition condition, RuleAction<Double> effect) {
        this.condition = condition;
        this.effect = effect;
    }
}
