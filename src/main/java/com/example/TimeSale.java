package com.example;

import java.util.Map;

public class TimeSale implements CalcInterface {

    @Override
    public int calculate(Map<String, Integer> purchasedProductStringMap) { //TODO (もっときれいにできるのでは？)

        CreateProductMap createProductMap = new CreateProductMap();

        Map<Product, Integer> purchasedProductMap = createProductMap.create(purchasedProductStringMap);

        //TODO create Sum class when refactoring
        int totalAmount = 0;
        for (final Product product: purchasedProductMap.keySet()) {
            final int quantity = purchasedProductMap.get(product);
            totalAmount += quantity;
        }

        totalAmount = (new Tax()).getTaxIncludedPrice(totalAmount);

        // System.out.println(totalAmount);

        return totalAmount;

    }

}

