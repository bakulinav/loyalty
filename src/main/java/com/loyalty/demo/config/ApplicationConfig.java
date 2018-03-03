package com.loyalty.demo.config;

import com.loyalty.demo.service.ShoppingCartProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ShoppingCartProcessor shoppingCartProcessor() {
        return new ShoppingCartProcessor();
    }
}
