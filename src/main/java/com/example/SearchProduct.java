package com.example;

import java.util.HashMap;
import java.util.Map;

public class SearchProduct {

    private final Map<String, Product> productMap;

    SearchProduct(){

        productMap= new HashMap<>();

        Product[] products = Product.values();
        for (Product product : products){
            productMap.put(product.getProductName(), product);
        }

    }

    public Product search(String productName) throws NullPointerException{
        return productMap.get(productName);
    }


}
