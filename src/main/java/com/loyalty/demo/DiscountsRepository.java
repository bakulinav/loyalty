package com.loyalty.demo;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by abakulin on 27/01/2018.
 */
public class DiscountsRepository {

    // TODO: get from data source (in-memory cache, NoSQL)
    Map<MatchType, ? extends List<DiscountRule>> cache = new HashMap<>();

    public DiscountsRepository(Map<MatchType, List<DiscountRule>> cache) {
        this.cache = cache;
    }

    public List<DiscountRule> findByProduct(String product) {
        List<DiscountRule> rules = cache.get(MatchType.PRODUCT);
        List<DiscountRule> matchRules = new ArrayList<>();

        for (DiscountRule rule : rules) {
            if (rule.getMatchByValue().equals(product)) matchRules.add(rule);
        }
        return matchRules;
    }

    public List<DiscountRule> findBySku(String sku) {
        List<DiscountRule> rules = cache.get(MatchType.SKU);
        List<DiscountRule> matchRules = new ArrayList<>();

        for (DiscountRule rule : rules) {
            if (rule.getMatchByValue().equals(sku)) matchRules.add(rule);
        }
        return matchRules;
    }

    public List<DiscountRule> findByTotal(Double total) {
        List<DiscountRule> rules = cache.get(MatchType.TOTAL);
        List<DiscountRule> matchRules = new ArrayList<>();

        for (DiscountRule rule : rules) {
            if (total >= Double.parseDouble(rule.getMatchByValue())) matchRules.add(rule);
        }
        return matchRules;
    }

    public List<DiscountRule> findByDateTime(ZonedDateTime shoppingDateTime) {
        List<DiscountRule> rules = cache.get(MatchType.DATETIME);
        List<DiscountRule> matchRules = new ArrayList<>();

        for (DiscountRule rule : rules) {
            if (ZonedDateTime.parse(rule.getMatchByValue()).equals(shoppingDateTime)) matchRules.add(rule);
        }
        return matchRules;
    }

    public List<DiscountRule> findByCustomer(String customerId) {
        List<DiscountRule> rules = cache.get(MatchType.CUSTOMER);
        List<DiscountRule> matchRules = new ArrayList<>();

        for (DiscountRule rule : rules) {
            if (rule.getMatchByValue().equals(customerId)) matchRules.add(rule);
        }
        return matchRules;
    }
}
