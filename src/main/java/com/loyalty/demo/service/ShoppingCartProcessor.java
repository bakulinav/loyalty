package com.loyalty.demo.service;

import com.loyalty.demo.CartItem;
import com.loyalty.demo.DiscountRule;
import com.loyalty.demo.DiscountsRepository;
import com.loyalty.demo.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by abakulin on 27/01/2018.
 */
public class ShoppingCartProcessor {
    private DiscountsRepository discountsRepository;

    public ShoppingCartProcessor(DiscountsRepository discountsRepository) {
        this.discountsRepository = discountsRepository;
    }

    public DiscountRule processShoppingCart(ShoppingCart shoppingCart) {
        // get cart items, cart attributes

        List<CartItem> items = shoppingCart.getShoppingItems();

        List<DiscountRule> discountsByProduct = new ArrayList<>();
        List<DiscountRule> discountsBySku = new ArrayList<>();
        for (CartItem item : items) {
            // find match discounts
            discountsByProduct.addAll(discountsRepository.findByProduct(item.getProduct()));
            discountsBySku.addAll(discountsRepository.findBySku(item.getSku()));
            // TODO: by price? by count?
        }

        List<DiscountRule> discountsByTotal = discountsRepository.findByTotal(shoppingCart.getTotal());
        List<DiscountRule> discountsByDateTime = discountsRepository.findByDateTime(shoppingCart.getShoppingDateTime());
        List<DiscountRule> discountsByCustomer = discountsRepository.findByCustomer(shoppingCart.getCustomerId());

        // filter more profit discount

        List<DiscountRule> discounts = new ArrayList<>();
        discounts.addAll(discountsByProduct);
        discounts.addAll(discountsBySku);
        discounts.addAll(discountsByTotal);
        discounts.addAll(discountsByDateTime);
        discounts.addAll(discountsByCustomer);

        return findMuchProfitableDiscountRule(discounts);
    }

    // brute force search by enumeration
    private DiscountRule findMuchProfitableDiscountRule(List<DiscountRule> discounts) {
        DiscountRule win = null;
        for (DiscountRule discount : discounts) {
            if (win == null) win = discount;
            else
                win = (discount.getProfit() > win.getProfit()) ? discount : win;
        }

        return win;
    }

}
