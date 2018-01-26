package com.loyalty.demo.config;

import com.loyalty.demo.DiscountRule;
import com.loyalty.demo.DiscountsRepository;
import com.loyalty.demo.MatchType;
import com.loyalty.demo.service.ShoppingCartProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ApplicationConfig {
    @Bean
    public DiscountsRepository discountsRepository() {
        Map<MatchType, List<DiscountRule>> cache = new HashMap<>();
        cache.put(MatchType.PRODUCT, Collections.singletonList(new DiscountRule(MatchType.PRODUCT, "tv", 10)));
        cache.put(MatchType.SKU, Collections.singletonList(new DiscountRule(MatchType.SKU, "iphone 7 black", 15)));
        cache.put(MatchType.TOTAL, Collections.singletonList(new DiscountRule(MatchType.TOTAL, "750", 20)));
        cache.put(MatchType.DATETIME, new ArrayList<>());
        cache.put(MatchType.CUSTOMER, Collections.singletonList(new DiscountRule(MatchType.CUSTOMER, "John Snow", 5)));

        return new DiscountsRepository(cache);
    }

    @Bean
    public ShoppingCartProcessor shoppingCartProcessor(DiscountsRepository repository) {
        return new ShoppingCartProcessor(repository);
    }
}
