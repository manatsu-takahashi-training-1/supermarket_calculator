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
    @Test
    void のり弁当とコーヒーを買って20円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.COFFEE,1);
        purchasedProductMap.put(Product.NORI_BENTO,1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(-20, discountValue);
    }
    @Test
    void しゃけ弁当とおちゃを買って20円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.TEA,1);
        purchasedProductMap.put(Product.SALMON_BENTO,1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(-20, discountValue);
    }
    @Test
    void しゃけ弁当だけ買って0円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.SALMON_BENTO,1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }

    @Test
    void のり弁当だけ買って0円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.NORI_BENTO,1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }

    @Test
    void タバコ10個とライター1つを買って100円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.CIGARETTE, 10);
        purchasedProductMap.put(Product.LIGHTER, 1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(-100, discountValue);
    }

    @Test
    void メンソールタバコ10個だけ買って0円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.MENTHOL_CIGARETTE, 10);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }

    @Test
    void メンソールタバコ9個とライターを1個買って0円引きされている() {
        purchasedProductMap = new HashMap<>();
        purchasedProductMap.put(Product.MENTHOL_CIGARETTE, 9);
        purchasedProductMap.put(Product.LIGHTER, 1);

        int discountValue = discount.calcDiscount(purchasedProductMap);
        assertEquals(0, discountValue);
    }
}
