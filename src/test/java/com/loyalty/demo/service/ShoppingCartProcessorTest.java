package com.loyalty.demo.service;

import com.loyalty.demo.*;
import org.junit.Test;

import java.time.ZonedDateTime;
import java.util.*;

import static org.junit.Assert.*;

public class ShoppingCartProcessorTest {

    @Test
    public void testProcessShoppingCart() throws Exception {
        // given
        Map<MatchType, List<DiscountRule>> cache = new HashMap<>();
        cache.put(MatchType.PRODUCT, Collections.singletonList(new DiscountRule(MatchType.PRODUCT, "tv", 10)));
        cache.put(MatchType.SKU, Collections.singletonList(new DiscountRule(MatchType.SKU, "iphone 7 black", 15)));
        cache.put(MatchType.TOTAL, Collections.singletonList(new DiscountRule(MatchType.TOTAL, "750", 20))); // winner
        cache.put(MatchType.DATETIME, new ArrayList<>());
        cache.put(MatchType.CUSTOMER, Collections.singletonList(new DiscountRule(MatchType.CUSTOMER, "John Snow", 5)));

        DiscountsRepository repository = new DiscountsRepository(cache);

        ShoppingCartProcessor processor = new ShoppingCartProcessor(repository);

        //when
        ShoppingCart cart = new ShoppingCart(
                Collections.singletonList(new CartItem("smartphone", "iphone 7 black", 780.0, 1)),
                780.0, ZonedDateTime.parse("2018-01-12T10:15:30+04:00"), "Tyrion Lannister");

        DiscountRule winner = processor.processShoppingCart(cart);
        assertEquals(MatchType.TOTAL, winner.getMatchByCriteria());
        assertEquals("750", winner.getMatchByValue());
        assertEquals(Integer.valueOf(20), winner.getProfit());
    }
}