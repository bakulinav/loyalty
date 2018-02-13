package com.loyalty.demo.service;

import com.loyalty.demo.*;

import java.util.List;

public class ShoppingCartProcessor {

    private RulesMatcher rulesMatcher;

    public ShoppingCartProcessor(RulesMatcher rulesMatcher) {
        this.rulesMatcher = rulesMatcher;
    }

    public Rule processShoppingCart(ShoppingCart shoppingCart) {
        List<Rule> matchRules = rulesMatcher.apply(shoppingCart);

        // filter and return more profitable rule
        return findMuchProfitableRule(matchRules, shoppingCart);
    }

    // brute force search by enumeration
    private Rule findMuchProfitableRule(List<Rule> rules, ShoppingCart shoppingCart) {
        Rule win = null;
        for (Rule rule : rules) {

            Double profit = rule.getEffect().calculate(shoppingCart);

            if (win == null) win = rule;
            else
                win = (rule.getEffect().calculate(shoppingCart) > profit) ? rule : win;
        }

        return win;
    }

}
