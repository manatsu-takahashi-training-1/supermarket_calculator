package com.example;

import java.util.HashMap;
import java.util.Map;

public class CreateProductMap {

    private final Map<Product,Integer> productMap;

    CreateProductMap(){
        productMap = new HashMap<>();
    }

    public Map<Product,Integer> create(Map<String,Integer> purchasedMap) {

        SearchProduct s = new SearchProduct();

        for (final String productName : purchasedMap.keySet()){
            Product product = s.search(productName);
            productMap.put(product,purchasedMap.get(product.getProductName()));
        }

        return productMap;
    }

}
