package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductMapTest {

    @Test
    void Apple_was_bought(){

        String productName = "apple";
        SearchProduct searchProduct = new SearchProduct();
        assertEquals("apple",searchProduct.searchProduct(productName).getProductName());
        assertEquals(100,searchProduct.searchProduct(productName).getPrice());

    }

    @Test
    void Orange_was_bought(){

        String productName = "orange";
        SearchProduct searchProduct = new SearchProduct();
        assertEquals("orange",searchProduct.searchProduct(productName).getProductName());
        assertEquals(40,searchProduct.searchProduct(productName).getPrice());

    }


}
