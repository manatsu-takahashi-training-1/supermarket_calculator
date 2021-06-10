package com.example;

import org.junit.jupiter.api.Assertions;
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
        @Test
        public void _0円を入れてエラーが出るかテスト(){
            Assertions.assertThrows(Exception.class, () -> {
                Tax test = new Tax(-8);
            });
        }
    }
    

}

//内税か外税か is 内税みたいなフィールド