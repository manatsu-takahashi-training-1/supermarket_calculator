package com.example;

import java.util.*;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class DiscountTest {
    Map<Product, Integer> purchasedProductMap;
    Discount discount;

    @BeforeEach
    void 準備() {
        discount = new Discount();

    }

    @Test
    void りんご3個で20円出力する() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.APPLE, 3);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(-20, discountValue);
    }

    @Test
    void りんご0個で0円出力する() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.APPLE, 0);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }

    @Test
    void りんご1個で0円出力する() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.APPLE, 1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }

    @Test
    void りんごー1個で0円出力する() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.APPLE, 1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }

    @Test
    void りんご7個でー40円出力する() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.APPLE, 7);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(-40, discountValue);
    }
}
