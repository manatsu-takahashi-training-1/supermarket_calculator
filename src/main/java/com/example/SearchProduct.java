package com.example;

import java.util.HashMap;
import java.util.Map;

public class SearchProduct {

    private final Map<String, Product> productMap;

    public SearchProduct() {

        this.productMap = new HashMap<>();

        for (Product product: Product.values()) {
            this.productMap.put(product.getProductName(), product);
        }

    }

    public Product search(String productName) throws NullPointerException {
        return this.productMap.get(productName);
    }

}

