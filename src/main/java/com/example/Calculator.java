package com.example;

import java.util.Map;

public class Calculator implements CalcInterface {

    @Override
    public int calculate(Map<String, Integer> purchasedProductStringMap) {

        CreateProductMap createProductMap = new CreateProductMap();

        Map<Product, Integer> purchasedProductMap = createProductMap.create(purchasedProductStringMap);

        //TODO create Sum class when refactoring
        int totalAmount = 0;
        for (final Product product: purchasedProductMap.keySet()) {
            final int quantity = purchasedProductMap.get(product);
            totalAmount += product.getPrice() * quantity;
        }

        totalAmount += (new Discount()).calcDiscount(purchasedProductMap);

        totalAmount = (new Tax()).getTaxIncludedPrice(totalAmount);

        // System.out.println(totalAmount);

        return totalAmount;

    }

}

