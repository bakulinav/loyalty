package com.loyalty.demo.service;

import com.loyalty.demo.*;
import com.loyalty.demo.rule.condition.CartTotalCondition;
import com.loyalty.demo.rule.condition.ProductNameCondition;
import com.loyalty.demo.rule.effect.PercentDiscountForProductAction;
import com.loyalty.demo.rule.effect.PercentDiscountForTotalAction;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class RulesMatcherTest {

    @Test
    public void testFindMatchedRulesByShoppingCart() {
        //given
        Rule discount5percOnTv = new Rule(
                new ProductNameCondition("ShoppingCart -> CartItem -> Product:name", "tv"),
                new PercentDiscountForProductAction("ShoppingCart -> CartItem:price", "Discount 5%", 0.05, "tv")
        );

        Rule discount10percOnSmartphone = new Rule(
                new ProductNameCondition("ShoppingCart->CartItem->Product:name", "smartphone"),
                new PercentDiscountForProductAction("ShoppingCart -> CartItem:price", "Discount 5%", 0.1, "smartphone")
        );

        Rule discount5percOnTotalGreatThan500 = new Rule(
                new CartTotalCondition("ShoppingCart:total", 500.0),
                new PercentDiscountForTotalAction("ShoppingCart:total", "Discount 5%", 0.05)
        );

        RulesMatcher matcher = new RulesMatcher(Arrays.asList(
                discount5percOnTv,
                discount10percOnSmartphone,
                discount5percOnTotalGreatThan500
        ));

        ShoppingCart cart = new ShoppingCart(
                Collections.singletonList(new CartItem("smartphone", "iphone 7 black", 780.0, 1)),
                780.0,
                ZonedDateTime.parse("2018-01-12T10:15:30+04:00"),
                "Tyrion Lannister");

        //when
        List<Rule> matchRules = matcher.apply(cart);

        //then
        assertEquals(2, matchRules.size());
        assertTrue(matchRules.contains(discount10percOnSmartphone));
        assertTrue(matchRules.contains(discount5percOnTotalGreatThan500));

    }
}
