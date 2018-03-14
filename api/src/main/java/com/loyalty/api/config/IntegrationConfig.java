package com.loyalty.api.config;

import com.loyalty.api.client.EngineClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class IntegrationConfig {

    @Bean
    public String engineUri() {
        return "http://localhost:8081";
    }

    @Bean
    public EngineClient engineClient(String engineUrl) {
        return new EngineClient(engineUrl);
    }
}
