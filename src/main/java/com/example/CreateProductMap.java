package com.example;

import java.util.HashMap;
import java.util.Map;

public class CreateProductMap {

    private final Map<Product,Integer> productMap;

    CreateProductMap(){
        productMap = new HashMap<>();
    }

    public Map<Product,Integer> create(Map<String,Integer> purchaseMap){

        SearchProduct s = new SearchProduct();

        for (Map.Entry<String, Integer> e : purchaseMap.entrySet()){
            Product product = s.search(e.getKey());
            productMap.put(product,e.getValue());
        }

        return productMap;
    }

}
