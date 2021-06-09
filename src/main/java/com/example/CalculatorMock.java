package com.example;

import java.util.Map;

public class CalculatorMock {
    public int calculate(Map<String, Integer> purchasedProductMap) {
        int totalAmount = 0;
        for (String productName: purchasedProductMap.keySet()) {
            final int quantity = purchasedProductMap.get(productName);
            int price;
            if (productName == "りんご") {
                price = 100;
            } else if (productName == "ぶどう") {
                price = 500;
            } else if (productName == "みかん") {
                price = 50;
            } else {
                price = 300;
            }
            totalAmount += price * quantity;
        }
        return totalAmount;
    }
}

