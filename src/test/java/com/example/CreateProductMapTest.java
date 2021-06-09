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

            Map<String,Integer> verificationMap = new HashMap<>();


            ArrayList<String> nameVerificationList = new ArrayList<>();
            ArrayList<Integer> numVerificationList = new ArrayList<>();

            nameVerificationList.add("apple");
            nameVerificationList.add("orange");

            numVerificationList.add(2);
            numVerificationList.add(3);

            int counter = 0;
            for (Map.Entry<Product, Integer> entry : productMap.entrySet()){
                assertEquals(nameVerificationList.get(counter),entry.getKey().getProductName());
                assertEquals(numVerificationList.get(counter),entry.getValue());
                counter++;
            }

        }
    }
}
