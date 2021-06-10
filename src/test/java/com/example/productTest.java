package com.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ProductTest {

    @Test
    void getNameTest(){
        Product product = Product.APPLE;
        assertEquals("apple",product.getProductName());
    }

    @Test
    void getPriceTest(){
        Product product = Product.APPLE;
        assertEquals(100,product.getPrice());
    }

    @Test
    void toStringTest(){
        Product product = Product.APPLE;
        assertEquals("Product{productName='apple', price=100}",product.toString());
    }

}
