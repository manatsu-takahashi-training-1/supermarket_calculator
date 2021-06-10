package com.example;

import java.util.Map;

public class Discount {

    public int calcDiscount(Map<Product, Integer> purchasedProductMap) {
        int numApple = purchasedProductMap.getOrDefault(Product.APPLE, 0);
        int discountValue = 0;
        discountValue -= (numApple / 3)*20;

//Bento discount
        if((purchasedProductMap.containsKey(Product.NORI_BENTO) || purchasedProductMap.containsKey(Product.SALMON_BENTO))
                && (purchasedProductMap.containsKey(Product.TEA)||purchasedProductMap.containsKey(Product.COFFEE)))
        {
            discountValue -= 20;
        }

        // discount lighter
        if((purchasedProductMap.getOrDefault(Product.CIGARETTE, 0) >= 10
                || purchasedProductMap.getOrDefault(Product.MENTHOL_CIGARETTE, 0) >= 10)
                && purchasedProductMap.getOrDefault(Product.LIGHTER, 0) >= 1)
        {
            discountValue -= 100;
        }
        
        return discountValue;
    }
}
