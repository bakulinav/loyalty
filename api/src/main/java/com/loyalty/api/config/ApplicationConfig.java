package com.loyalty.api.config;

import com.loyalty.api.client.EngineClient;
import com.loyalty.api.service.ShoppingCartProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ShoppingCartProcessor shoppingCartProcessor(
            @Autowired EngineClient engineClient
    ) {
        return new ShoppingCartProcessor(engineClient);
    }
}
