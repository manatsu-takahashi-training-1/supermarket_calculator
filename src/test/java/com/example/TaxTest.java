package com.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class TaxTest {
    @Nested
    public class ちゃんと計算できているかテスト {
        @Test
        public void _100円を入れて108円になってるかな() {
            Tax test = new Tax(100);
            int actual = test.getTaxIncludedPrice(100);
            assertEquals(108,actual);
        }
    }

}

//内税か外税か is 内税みたいなフィールド