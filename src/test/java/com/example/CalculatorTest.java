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
    private Map<String, Integer> purchasedProductStringMap;
    private CreateProductMap createProductMap;

    @BeforeEach
    public void prepare() {
        this.calculator                = new Calculator();
        this.purchasedProductMap       = new HashMap<>();
        this.createProductMap          = new CreateProductMap();
        this.purchasedProductStringMap = new HashMap<>();

    }

    //lemma 1
    @Test
    public void returns_108_when_1_apple() {
        //(100*1)*1.08 = 108
        purchasedProductStringMap.put("apple", 1);
        assertEquals(calculator.calculate(purchasedProductStringMap), 108);
    }

    //lemma 2
    @Test
    public void returns_313_when_1_apple_1_orange_1_grape() {
        //(100*1 + 40*1 +150*1)*1.08 = 313
        purchasedProductStringMap.put("apple", 1);
        purchasedProductStringMap.put("orange", 1);
        purchasedProductStringMap.put("grape", 1);
        assertEquals(calculator.calculate(purchasedProductStringMap), 313);
    }

    //main
    @Test
    public void returns_1587_when_4_apple_1_orange_3_noribento() {
        //(100*4-20) + 40*1 + 350*3 = 1587
        purchasedProductStringMap.put("apple", 4);
        purchasedProductStringMap.put("orange", 1);
        purchasedProductStringMap.put("noriBento", 3);
        assertEquals(calculator.calculate(purchasedProductStringMap), 1587);
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

