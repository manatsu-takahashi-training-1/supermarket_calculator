package com.example;

import java.util.HashMap;
import java.util.Map;

public class CreateProductMap {

    Map<Product,Integer> createProductMap;

    CreateProductMap(){
        createProductMap = new HashMap<>();
    }

    public Map<Product,Integer> create(Map<String,Integer> productMap){

        SearchProduct searchProduct = new SearchProduct();
        for (Map.Entry<String, Integer> entry : productMap.entrySet()){
            Product product = searchProduct.searchProduct(entry.getKey());
            createProductMap.put(product,entry.getValue());
        }

        return createProductMap;
    }

}
