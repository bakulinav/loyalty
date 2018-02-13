package com.loyalty.demo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abakulin on 27/01/2018.
 */
public class RulesRepository {

    // TODO: get from data source (in-memory cache, NoSQL)
    private List<Rule> cache = new ArrayList<>();

    public RulesRepository(List<Rule> cache) {
        this.cache = cache;
    }

    public List<Rule> findAll() {
        return this.cache;
    }
}
