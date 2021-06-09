package com.example;

import java.util.Map;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        //prepares `purchaseMap`
        Map<String, Integer> purchaseMap = new HashMap<>();
        purchaseMap.put("apple", 4);
        purchaseMap.put("orange", 3);
        CreateProductMap createProductMap = new CreateProductMap();
        Map <Product,Integer> productMap = createProductMap.create(purchaseMap);

        int totalAmountWithoutTax = (new Calculator()).calculate(productMap);
        // System.out.println(totalAmountWithoutTax);
        totalAmountWithoutTax += (new Discount()).calcDiscount(productMap);
        // System.out.println(totalAmountWithoutTax);
        int totalAmount = (new Tax(0)).getTaxIncludedPrice(totalAmountWithoutTax);
        // System.out.println(totalAmount);

    }
}

