package com.example;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;

public class CalculatorTest {

    private Calculator c;

    @BeforeEach
    public void prepare() {
        this.c = new Calculator();
    }

    @Nested
    class Lemma1 {

        @Test
        public void returns_100_for_an_apple() {
            assertEquals(c.calculate(Product.APPLE), 100);
        }

        /*@Test
        public void returns_40_for_an orange() {
            assertEquals(c.calculate(Product.ORANGE), 40);
        }*/

    }

}
