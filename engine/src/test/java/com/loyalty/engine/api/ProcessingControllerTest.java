package com.loyalty.engine.api;

import com.loyalty.engine.service.ShoppingCartProcessor;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class ProcessingControllerTest {

    @Autowired
    private ShoppingCartProcessor scp;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(new ProcessingController(scp)).build();
    }

    @Test
    public void testProcessCart() throws Exception {
        this.mockMvc
                .perform(
                        post("/process/cart")
                                .content(scJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().is(200));
    }

    @Test
    public void testProcessBatchCart() throws Exception {
        this.mockMvc
                .perform(
                        post("/process/batch-cart")
                                .content(batchJson)
                                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(
                        status().is(200));
    }

    private final String scJson = "{\n"+
            "    \"shoppingCart\": {\n"+
            "        \"shoppingItems\": [\n"+
            "            {\n"+
            "                \"product\": \"smartphone\",\n"+
            "                \"sku\": \"iphone 7 black\",\n"+
            "                \"price\": 780,\n"+
            "                \"count\": 1\n"+
            "            }\n"+
            "        ],\n"+
            "        \"total\": 780,\n"+
            "        \"shoppingDateTime\": 1515737730,\n"+
            "        \"customerId\": \"Tyrion Lannister\",\n"+
            "        \"discounted\": false\n"+
            "    },\n"+
            "    \"clientId\": \"clientID1\",\n"+
            "    \"firedRules\": []\n"+
            "}";

    private final String batchJson = "{\n" +
            "    \"count\": 2,\n" +
            "    \"items\": [\n" +
            "        {\n" +
            "            \"shoppingCart\": {\n" +
            "                \"shoppingItems\":[\n" +
            "                    {\"product\": \"smartphone\", \"sku\": \"iphone 7 black\", \"price\": \"780.0\", \"count\": 1}\n" +
            "                ],\n" +
            "                \"total\": 780.0,\n" +
            "                \"shoppingDateTime\": \"2018-01-12T10:15:30+04:00\",\n" +
            "                \"customerId\": \"Tyrion Lannister\"\n" +
            "            },\n" +
            "            \"clientId\": \"clientID1\",\n" +
            "            \"state\": \"STATELESS\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"shoppingCart\": {\n" +
            "                \"shoppingItems\":[\n" +
            "                    {\"product\": \"laptop\", \"sku\": \"lenovo ideapad\", \"price\": \"850.0\", \"count\": 1}\n" +
            "                ],\n" +
            "                \"total\": 850.0,\n" +
            "                \"shoppingDateTime\": \"2018-01-12T10:15:31+04:00\",\n" +
            "                \"customerId\": \"Mike Coolman\"\n" +
            "            },\n" +
            "            \"clientId\": \"test_client_2\",\n" +
            "            \"state\": \"STATELESS\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";
}