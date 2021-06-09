package com.example;

import java.util.HashMap;
import java.util.Map;

public class ProductMap {

    Map<String, Product> productMap;

    ProductMap(){

        productMap= new HashMap<>();

        Product[] products = Product.values();
        for (Product product : products){
            productMap.put(product.getProductName(), product);
        }

    }

    public Product searchProduct(String productName) throws NullPointerException{
        return productMap.get(productName);
    }


}
