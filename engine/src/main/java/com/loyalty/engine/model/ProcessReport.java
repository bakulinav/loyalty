package com.loyalty.engine.model;

import com.loyalty.model.ShoppingCart;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class ProcessReport {
    private ShoppingCart subject;
    private List<String> firedRules;
}
