package com.example;

import java.util.HashMap;
import java.util.Map;

public class CreateProductMap {

    private final Map<Product,Integer> productMap;

    CreateProductMap(){
        productMap = new HashMap<>();
    }

    public Map<Product,Integer> create(Map<String,Integer> purchasedProductMap) {

        SearchProduct s = new SearchProduct();

        for (String productName : purchasedProductMap.keySet()){
            Product product = s.search(productName);
            productMap.put(product,purchasedProductMap.get(productName));
        }

        return productMap;
    }

}
