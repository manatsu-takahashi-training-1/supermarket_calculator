package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.*;

public class CreateProductMapTest {

    Map<String,Integer> purchaseMap;

    @Nested
    class Create_a_purchase_list{

        @BeforeEach
        void Makes_List(){
            purchaseMap = new HashMap<>();
        }

        @Test
        void Two_apple_and_Three_orange_were_bought(){

            purchaseMap.put("apple",2);
            purchaseMap.put("orange",3);

            CreateProductMap createProductMap = new CreateProductMap();
            Map <Product,Integer> productMap = createProductMap.create(purchaseMap);

            Map <Product,Integer> verificationMap = new HashMap<>();
            verificationMap.put(Product.APPLE,2);
            verificationMap.put(Product.ORANGE,3);
            verificationMap.put(Product.GRAPE,0);


            for (final Product product : verificationMap.keySet()){
                assertEquals(verificationMap.get(product),productMap.getOrDefault(product,0));
            }

        }
    }
}
