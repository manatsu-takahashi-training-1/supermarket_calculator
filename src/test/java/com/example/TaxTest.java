package com.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class TaxTest {
    @Nested
    public class テスト１ {
        @Test
        public void テスト() {
            Tax test = new Tax(100);
            int actual = test.getTaxIncludedPrice(100);
            assertEquals(108,actual);
        }
    }

}

//内税か外税か is 内税みたいなフィールド