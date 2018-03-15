package com.loyalty.engine.api;

import com.loyalty.engine.ShoppingCartProcessor;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public ShoppingCartProcessor getCartProcessor() {
        return Mockito.mock(ShoppingCartProcessor.class);
    }
}
