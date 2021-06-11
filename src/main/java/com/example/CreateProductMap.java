package com.example;

import java.util.Map;
import java.util.HashMap;

public class CreateProductMap {

    private final Map<Product, Integer> productMap;

    public CreateProductMap(){
        productMap = new HashMap<>();
    }

    public Map<Product,Integer> create(Map<String, Integer> purchasedMap) {

        SearchProduct s = new SearchProduct();

        for (final String productName: purchasedMap.keySet()) {
            Product product = s.search(productName);
            productMap.put(product, purchasedMap.get(product.getProductName())); //TODO
        }

        return productMap;

    }

}

