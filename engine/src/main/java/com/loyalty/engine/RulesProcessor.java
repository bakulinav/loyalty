package com.loyalty.engine;

import com.loyalty.engine.model.EngineMode;
import com.loyalty.engine.model.ShoppingCart;
import org.kie.api.KieBase;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RulesProcessor {

    private StatefulRulesEngine statefulEngine;
    private StatelessRulesEngine statelessEngine;
    private KieContainer kc;

    public RulesProcessor() {
    }

    @Autowired
    public RulesProcessor(KieContainer kc, StatefulRulesEngine statefulEngine, StatelessRulesEngine statelessEngine) {
        this.kc = kc;
        this.statefulEngine = statefulEngine;
        this.statelessEngine = statelessEngine;
    }

    public void process(EngineMode mode, ShoppingCart shoppingCart, String knowledgeBaseId) {

        KieBase kBase = getKnowledgeBase(knowledgeBaseId);

        switch (mode) {
            case STATEFUL: statefulEngine.run(shoppingCart, kBase); break;
            case STATELESS: statelessEngine.run(shoppingCart, kBase); break;
            default:
                throw new RuntimeException("Unexpected EngineMode for processor: " + mode.toString());
        }
    }

    private KieBase getKnowledgeBase(String knowledgeBaseId) {
        return this.kc.getKieBase(knowledgeBaseId);
    }
}
