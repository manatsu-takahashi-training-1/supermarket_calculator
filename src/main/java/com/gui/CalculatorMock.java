package com.gui;

import java.util.Map;

public class CalculatorMock {

    public int calculate(Map<String, Integer> purchasedProductMap) {

        int totalAmount = 0;

        for (String productName: purchasedProductMap.keySet()) {

            final int quantity = purchasedProductMap.get(productName);

            int price;

            if (productName.equals("apple")) {
                price = 100;
            } else if (productName.equals("orange")) {
                price = 40;
            } else if (productName.equals("grape")) {
                price = 150;
            } else if (productName.equals("noriBento")) {
                price = 350;
            } else if (productName.equals("salmonBento")) {
                price = 400;
            } else if (productName.equals("cigarette")) {
                price = 420;
            } else if (productName.equals("mentholCigarette")) {
                price = 440;
            } else if (productName.equals("lighter")) {
                price = 100;
            } else if (productName.equals("tea")) {
                price = 80;
            } else if (productName.equals("coffee")) {
                price = 100;
            } else if (productName.equals("flashHamburger")) {
                price = 100000;
            } else {
                price = 10000;
            }

            totalAmount += price * quantity;

        }

        return totalAmount;

    }

}

