package com.loyalty.engine.service;

import com.loyalty.engine.model.ProcessReport;
import com.loyalty.model.EngineMode;
import com.loyalty.model.ShoppingCart;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ShoppingCartProcessor {

    private StatefulRulesEngine statefulEngine;
    private StatelessRulesEngine statelessEngine;
    private KieContainer kc;

    public ShoppingCartProcessor() {
    }

    @Autowired
    public ShoppingCartProcessor(KieContainer kc, StatefulRulesEngine statefulEngine, StatelessRulesEngine statelessEngine) {
        this.kc = kc;
        this.statefulEngine = statefulEngine;
        this.statelessEngine = statelessEngine;
    }

    public ProcessReport process(EngineMode mode, ShoppingCart shoppingCart, String knowledgeBaseId) {

        KieBase kBase = getKnowledgeBase(knowledgeBaseId);

        ProcessReport report;
        switch (mode) {
            case STATEFUL: report = statefulEngine.run(shoppingCart, kBase); break;
            case STATELESS: report = statelessEngine.run(shoppingCart, kBase); break;
            default:
                throw new RuntimeException("Unexpected EngineMode for processor: " + mode.toString());
        }

        return report;
    }

    private KieBase getKnowledgeBase(String knowledgeBaseId) {
        return this.kc.getKieBase(knowledgeBaseId);
    }
}
