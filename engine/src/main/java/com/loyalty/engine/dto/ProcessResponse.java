package com.loyalty.engine.dto;

import com.loyalty.engine.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProcessResponse {
    private ShoppingCart shoppingCart;
    private String clientId;
    private List<String> firedRules;

    public ProcessResponse() {}

    public ProcessResponse(ShoppingCart shoppingCart, String clientId, List<String> firedRules) {
        this.shoppingCart = shoppingCart;
        this.clientId = clientId;
        this.firedRules = firedRules;
    }
}
