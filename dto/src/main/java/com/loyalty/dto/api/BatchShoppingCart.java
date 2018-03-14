package com.loyalty.dto.api;

import com.loyalty.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Shopping cart batch container.
 * Has meta field "count" and list of "items".
 * The "count" should be equals to number of elements in "items"
 */
@Getter
@Setter
public class BatchShoppingCart {
    private Long count;
    private List<ShoppingCart> items;

    public BatchShoppingCart() {}

    public BatchShoppingCart(long count, List<ShoppingCart> items) {
        this.count = count;
        this.items = items;
    }

    @Override
    public String toString() {
        return "BatchShoppingCart{" +
                "count=" + count +
                '}';
    }
}
