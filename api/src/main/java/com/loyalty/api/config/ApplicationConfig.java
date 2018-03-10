package com.loyalty.api.config;

import com.loyalty.api.service.ShoppingCartProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ShoppingCartProcessor shoppingCartProcessor() {
        return new ShoppingCartProcessor();
    }
}
