package com.example;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {
    Map<String, Integer> purchasedProductMap;

    @Test
    void りんご3個で20円割引する() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put("りんご", 3);
        //int discountValue = Discount.calcDiscount(purchasedProductMap);
        //assertEquals(-20, discountValue);
    }

}
