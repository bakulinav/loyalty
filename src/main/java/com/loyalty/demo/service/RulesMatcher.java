package com.loyalty.demo.service;

import com.loyalty.demo.Rule;
import com.loyalty.demo.ShoppingCart;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by abakulin on 07/02/2018.
 */
public class RulesMatcher {

    private List<Rule> rules;

    public RulesMatcher(List<Rule> rules) {
        this.rules = rules;
    }

    public List<Rule> apply(ShoppingCart cart) {
        return rules.stream().filter(rule -> rule.getCondition().isMatch(cart))
                .collect(Collectors.toList());
    }
}
