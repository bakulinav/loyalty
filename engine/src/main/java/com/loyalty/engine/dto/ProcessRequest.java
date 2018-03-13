package com.loyalty.engine.dto;

import com.loyalty.engine.model.EngineMode;
import com.loyalty.engine.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProcessRequest {
    private ShoppingCart shoppingCart;
    private String clientId;
    private EngineMode state;

    public ProcessRequest() {}

    public ProcessRequest(ShoppingCart shoppingCart, String clientId, EngineMode state) {
        this.shoppingCart = shoppingCart;
        this.clientId = clientId;
        this.state = state;
    }

    @Override
    public String toString() {
        return "ProcessRequest{" +
                "shoppingCart=" + shoppingCart +
                ", clientId='" + clientId + '\'' +
                ", state=" + state +
                '}';
    }
}
