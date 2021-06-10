package com.example;

import java.util.Map;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class CalculatorTest {

    private Calculator calculator;
    private Map<Product, Integer> purchasedProductMap;

    @BeforeEach
    public void prepare() {
        this.calculator          = new Calculator();
        this.purchasedProductMap = new HashMap<>();
    }

    //lemma 1
    @Test
    public void returns_100_when_1_apple() {
        purchasedProductMap.put(Product.APPLE, 1);
        assertEquals(calculator.calculate(purchasedProductMap), 100);
    }

    //lemma 2
    @Test
    public void returns_290_when_1_apple_1_orange_1_grape() {
        purchasedProductMap.put(Product.APPLE, 1);
        purchasedProductMap.put(Product.ORANGE, 1);
        purchasedProductMap.put(Product.GRAPE, 1);
        assertEquals(calculator.calculate(purchasedProductMap), 290);
    }

    //main
    @Test
    public void returns_1290_when_2_apple_1_orange_3_noribento() {
        purchasedProductMap.put(Product.APPLE, 2);
        purchasedProductMap.put(Product.ORANGE, 1);
        purchasedProductMap.put(Product.NORI_BENTO, 3);
        assertEquals(calculator.calculate(purchasedProductMap), 1290);
    }

// 1. りんご 100円
// 2. みかん 40円
// 3. ぶどう 150円
// 4. のり弁 350円
// 5. しゃけ弁 400円
// 6. タバコ 420円
// 7. メンソールタバコ 440円
// 8. ライター 100円
// 9. お茶 80円
// 10. コーヒー 100円

}

