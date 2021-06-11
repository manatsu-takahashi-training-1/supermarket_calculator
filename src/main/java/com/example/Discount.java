package com.example;

import java.util.Map;
import java.util.HashMap;

public class Discount {

    private Map<Product, Integer> discountedProductMap;

    public Discount() {
        this.discountedProductMap = new HashMap<>();
    }

    public int calcDiscount(Map<Product, Integer> purchasedProductMap) {

        for (Product product: purchasedProductMap.keySet()) {
            this.discountedProductMap.put(product, 0);
        }

        this.defaultDiscount(purchasedProductMap);
        this.appleDiscount(purchasedProductMap);
        this.lighterDiscount(purchasedProductMap);
        this.bentoDiscount(purchasedProductMap);

        int discountValue = 0;
        for (Product product: purchasedProductMap.keySet()){
            discountValue += this.discountedProductMap.get(product);
        }

        return discountValue;

    }

    private void defaultDiscount(Map<Product, Integer> purchasedProductMap) {
        for (Product product: purchasedProductMap.keySet()) {
            final int updateDiscountValue = Math.min(this.discountedProductMap.get(product), - purchasedProductMap.get(product) / 11 * product.getPrice());
            this.discountedProductMap.replace(product, updateDiscountValue);
        }
    }

    private void appleDiscount(Map<Product, Integer> purchasedProductMap) {
        if (purchasedProductMap.containsKey(Product.APPLE)) {
            final int num = purchasedProductMap.get(Product.APPLE);
            final int discountSetPrice = - 20 * (num / 3);
            final int updateDiscountValue =  Math.min(this.discountedProductMap.get(Product.APPLE), discountSetPrice);
            this.discountedProductMap.replace(Product.APPLE, updateDiscountValue);
        }
    }

    private void lighterDiscount(Map<Product, Integer> purchasedProductMap) {
        if (purchasedProductMap.containsKey(Product.LIGHTER)) {
            final int price = Product.LIGHTER.getPrice();
            final int numCigarette = purchasedProductMap.getOrDefault(Product.CIGARETTE, 0);
            final int numMentholCigarette = purchasedProductMap.getOrDefault(Product.MENTHOL_CIGARETTE, 0);
            final int discountSetPrice = - price * ((numCigarette + numMentholCigarette) / 10);
            final int updateDiscountValue = Math.min(this.discountedProductMap.get(Product.LIGHTER), discountSetPrice);
            this.discountedProductMap.replace(Product.LIGHTER, updateDiscountValue);
        }
    }

    private void bentoDiscount(Map<Product, Integer> purchasedProductMap) {

        final int numNori = purchasedProductMap.getOrDefault(Product.NORI_BENTO, 0);
        final int numSalmon = purchasedProductMap.getOrDefault(Product.SALMON_BENTO, 0);
        final int numDrink = purchasedProductMap.getOrDefault(Product.COFFEE, 0) + purchasedProductMap.getOrDefault(Product.TEA, 0);

        int regNori = this.discountedProductMap.getOrDefault(Product.NORI_BENTO, 0);
        int regSalmon = this.discountedProductMap.getOrDefault(Product.SALMON_BENTO, 0);
        int totalDiscount = regNori + regSalmon;

        //In this `for` loop, we compare all the possible discount patterns to get the maximum discount.
        //`i` means how many drinks are assigned to "noriBento"; the remaining `numDrink - i` drinks are assinged to "salmonBento".
        //
        //TODO
        //Currently there is a bug where the discount becoms zero under some condition.
        //Considering the condition which doesn't invoke `continue`,
        //  NOT (A OR B)
        //  = (NOT A) AND (NOT B)
        //  = (i <= numNori) && ((numDrink - i) <= numSalmon)
        //  = (i <= numNori) && ((numDrink - numSalmon) <= i)
        //To this to be archieved,
        //  numDrink - numSalmon <= numNori
        //  -> numDrink <= numNori + numSalmon
        //shall be fullfilled.
        //This condition is however incomplete; consider the following situation for example.
        //  numDrink == 4
        //  numNori == 1
        //  numSalmon == 2
//         for (int i = 0; i <= numDrink; ++i) {
// 
//             if ((i > numNori) || ((numDrink - i) > numSalmon)) {
//                 continue;
//             }
// 
//             final int discountNori = Math.min(this.discountedProductMap.getOrDefault(Product.NORI_BENTO, 0), -20 * i);
//             final int discountSalmon = Math.min(this.discountedProductMap.getOrDefault(Product.SALMON_BENTO, 0), -20 * (numDrink - i));
// 
//             if (discountNori + discountSalmon < totalDiscount) {
//                 regNori = discountNori;
//                 regSalmon = discountSalmon;
//                 totalDiscount = discountNori + discountSalmon;
//                 // System.out.println(i);
//                 // System.out.println(totalDiscount);
//             }
// 
//         }

        //bug fix version of the exhaustive search right above
        for (int i = 0; i <= numDrink; ++i) {

            final int numDrinkForNori = Math.min(i, numNori);
            final int numDrinkForSalmon = Math.min(numDrink - i, numSalmon);

            final int discountNori = Math.min(discountedProductMap.getOrDefault(Product.NORI_BENTO, 0), - 20 * numDrinkForNori);
            final int discountSalmon = Math.min(discountedProductMap.getOrDefault(Product.SALMON_BENTO, 0), - 20 * numDrinkForSalmon);

            if (discountNori + discountSalmon < totalDiscount) {
                regNori = discountNori;
                regSalmon = discountSalmon;
                totalDiscount = discountNori + discountSalmon;
            }

        }

        this.discountedProductMap.replace(Product.NORI_BENTO, regNori);
        this.discountedProductMap.replace(Product.SALMON_BENTO, regSalmon);

    }

}

// purchasedProductMap
// Map<Product, Integer> = <Product, 割引額>

// りんごの割引
// タバコの割引
// 弁当割引（のり、さけ）
// 5, 0
// 4, 1
// 3, 2
// 2, 3
// 1, 4
// 0, 5

// のりは-100円
// のり6, さけ2, 飲み物6
// 6, 0: (min(-100, -120), 0)
// 5, 1: (min(-100, -100), -20)
// 4, 2: (min(-100, -80), -40)

