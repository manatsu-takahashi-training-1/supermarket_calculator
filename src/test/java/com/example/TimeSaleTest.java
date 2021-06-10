package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TimeSaleTest {

    Map<String, Integer> purchasedProductStringMap;
    TimeSale timeSale;

    @BeforeEach
    void 準備() {
        timeSale = new TimeSale();
        purchasedProductStringMap = new HashMap<>();
    }

    @Nested
    class りんご{
        @Test
        void りんご10個で0円出力する() {
            purchasedProductStringMap.put("apple", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void りんご11個でー100円出力する() {
            purchasedProductStringMap.put("apple", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class みかん{
        @Test
        void みかん10個で0円出力する() {
            purchasedProductStringMap.put("orange", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void みかん11個でーx円出力する() {
            purchasedProductStringMap.put("orange", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class ぶどう{
        @Test
        void ぶどう10個で0円出力する() {
            purchasedProductStringMap.put("grape", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void ぶどう11個でーx円出力する() {
            purchasedProductStringMap.put("grape", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class のり弁{
        @Test
        void のり弁10個で0円出力する() {
            purchasedProductStringMap.put("noriBento", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void のり弁11個でーx円出力する() {
            purchasedProductStringMap.put("noriBento", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class シャケ弁{
        @Test
        void シャケ弁10個で0円出力する() {
            purchasedProductStringMap.put("salmonBento", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void シャケ弁11個でーx円出力する() {
            purchasedProductStringMap.put("salmonBento", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class タバコ{
        @Test
        void タバコ10個で0円出力する() {
            purchasedProductStringMap.put("cigarette", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void tタバコ11個でーx円出力する() {
            purchasedProductStringMap.put("cigarette", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class メンソールタバコ{
        @Test
        void メンソールタバコ10個で0円出力する() {
            purchasedProductStringMap.put("mentholCigarette", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void メンソールタバコ11個でーx円出力する() {
            purchasedProductStringMap.put("mentholCigarette", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class お茶{
        @Test
        void お茶10個で0円出力する() {
            purchasedProductStringMap.put("tea", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void お茶11個でーx円出力する() {
            purchasedProductStringMap.put("tea", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }

    @Nested
    class コーヒー{
        @Test
        void コーヒー10個で0円出力する() {
            purchasedProductStringMap.put("coffee", 0);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(0, bonusValue);
        }

        @Test
        void コーヒー11個でーx円出力する() {
            purchasedProductStringMap.put("coffee", 1);
            int bonusValue = timeSale.calcSale(purchasedProductStringMap);
            assertEquals(1 * 108 / 100, bonusValue);
        }
    }
}
