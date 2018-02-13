package com.loyalty.demo.config;

import com.loyalty.demo.Rule;
import com.loyalty.demo.RuleAction;
import com.loyalty.demo.RulesRepository;
import com.loyalty.demo.rule.condition.CartTotalCondition;
import com.loyalty.demo.rule.condition.ProductNameCondition;
import com.loyalty.demo.rule.effect.PercentDiscountForProductAction;
import com.loyalty.demo.rule.effect.PercentDiscountForTotalAction;
import com.loyalty.demo.service.RulesMatcher;
import com.loyalty.demo.service.ShoppingCartProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

@Configuration
public class ApplicationConfig {
    @Bean
    public RulesRepository discountsRepository() {
        List<Rule> cache = new ArrayList<>();

        Rule discount5percOnTv = new Rule(
                new ProductNameCondition("ShoppingCart -> CartItem -> Product:name", "tv"),
                new PercentDiscountForProductAction("ShoppingCart -> CartItem:price", "Discount 5%", 0.05, "tv")
        );

        Rule discount10percOnSmartphone = new Rule(
                new ProductNameCondition("ShoppingCart->CartItem->Product:name", "smartphone"),
                new PercentDiscountForProductAction("ShoppingCart -> CartItem:price", "Discount 10%", 0.1, "smartphone")
        );

        Rule discount5percOnTotalGreatThan500 = new Rule(
                new CartTotalCondition("ShoppingCart:total", 500.0),
                new PercentDiscountForTotalAction("ShoppingCart:total", "Discount 5%", 0.05)
        );

        cache.add(discount5percOnTv);
        cache.add(discount10percOnSmartphone);
        cache.add(discount5percOnTotalGreatThan500);

        return new RulesRepository(cache);
    }

    @Bean
    public RulesMatcher rulesMatcher(RulesRepository repository) {
        return new RulesMatcher(repository.findAll());
    }

    @Bean
    public ShoppingCartProcessor shoppingCartProcessor(RulesMatcher rulesMatcher) {
        return new ShoppingCartProcessor(rulesMatcher);
    }
}
