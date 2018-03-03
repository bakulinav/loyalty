package com.loyalty.demo.config;


import org.kie.api.KieServices;
import org.kie.api.logger.KieLoggers;
import org.kie.api.runtime.KieContainer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {
    private static final Logger log = LoggerFactory.getLogger(DroolsConfig.class);

    @Bean
    public KieServices kieServices() {
        return KieServices.Factory.get();
    }

    @Bean
    public KieContainer kieContainer(KieServices ks) {
        return ks.getKieClasspathContainer();
    }

    @Bean
    public KieLoggers kieLogger(KieServices ks) {
        return ks.getLoggers();
    }
}
