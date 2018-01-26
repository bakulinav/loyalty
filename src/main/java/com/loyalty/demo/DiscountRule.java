package com.loyalty.demo;

import lombok.Getter;

/**
 * Simple discount rule match by single criteria type, like product or total
 */
@Getter
public class DiscountRule {
    MatchType matchByCriteria;
    String matchByValue;

    // abstract value of profit from this discount
    Integer profit;

    public DiscountRule(MatchType matchByCriteria, String matchByValue, Integer profit) {
        this.matchByCriteria = matchByCriteria;
        this.matchByValue = matchByValue;
        this.profit = profit;
    }
}
