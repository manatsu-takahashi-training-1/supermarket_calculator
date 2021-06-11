package com.example;

import java.util.Map;

public class TimeSale implements CalcInterface {

    @Override
    public int calculate(Map<String, Integer> purchasedProductStringMap) {

        int totalAmount = 0;

        for (final int quantity: purchasedProductStringMap.values()) {
            totalAmount += quantity;
        }

        // System.out.println(totalAmount);

        return totalAmount;

    }

}

