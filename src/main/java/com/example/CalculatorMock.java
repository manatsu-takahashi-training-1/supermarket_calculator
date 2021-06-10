package com.example;

import java.util.Map;

public class CalculatorMock {
    public int calculate(Map<String, Integer> purchasedProductMap) {
        int totalAmount = 0;
        for (String productName: purchasedProductMap.keySet()) {
            final int quantity = purchasedProductMap.get(productName);
            System.out.println("-----");
            System.out.printf("[%s]\n", productName);
            System.out.println(productName.equals("りんご"));
            int price;
            if (productName.equals("りんご")) {
                price = 100;
            } else if (productName.equals("ぶどう")) {
                price = 500;
            } else if (productName.equals("みかん")) {
                price = 40;
            } else {
                price = 300;
            }
            totalAmount += price * quantity;
        }
        return totalAmount;
    }
}

