package com.example;

import java.util.Map;

public class Calculator {
    public int calculate(Map<Product, Integer> purchasedProductMap) {
        int totalAmount = 0;
        for (final Product product: purchasedProductMap.keySet()) {
            final int quantity = purchasedProductMap.get(product);
            totalAmount += product.getPrice() * quantity;
        }
        return totalAmount;
    }
}

