package com.example;

import java.util.Map;

public class Discount {

    public int calcDiscount(Map<Product, Integer> purchasedProductMap) {
        int numApple = purchasedProductMap.get(Product.APPLE);
        int discountValue = 0;
        discountValue -= (numApple / 3)*20;
        return discountValue;
    }
}
