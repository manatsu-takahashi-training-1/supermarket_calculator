package com.example;

import java.util.*;

public class Discount {
    public int calcDiscount(Map<Product, Integer> purchasedProductMap) {
        // int numApple = purchasedProductMap.getOrDefault(Product.APPLE, 0);
        // int discountValue = 0;
        // discountValue -= (numApple / 3)*20;

        //Bento discount
        // if((purchasedProductMap.containsKey(Product.NORI_BENTO) || purchasedProductMap.containsKey(Product.SALMON_BENTO))
        //         && (purchasedProductMap.containsKey(Product.TEA)||purchasedProductMap.containsKey(Product.COFFEE)))
        // {
        //     discountValue -= 20;
        // }


        // init discountedProductMap
        Map<Product, Integer> discountedProductMap = new HashMap<>();
        for (Product product : purchasedProductMap.keySet()){
            discountedProductMap.put(product, 0);
        }
        discountedProductMap = defaultDiscount(purchasedProductMap, discountedProductMap);
        discountedProductMap = appleDiscount(purchasedProductMap, discountedProductMap);
        discountedProductMap = lighterDiscount(purchasedProductMap, discountedProductMap);
        discountedProductMap = bentoDiscount(purchasedProductMap, discountedProductMap);

        int discountValue = 0;
        for (Product product : purchasedProductMap.keySet()){
            discountValue += discountedProductMap.get(product);
        }

        return discountValue;
    }





    private Map<Product, Integer> defaultDiscount(Map<Product, Integer> purchasedProductMap, Map<Product, Integer> discountedProductMap) {
        for (Product product : purchasedProductMap.keySet()){
            Integer updateDiscounteValue = Math.min(discountedProductMap.get(product), -purchasedProductMap.get(product)/11 *product.getPrice());
            discountedProductMap.replace(product,updateDiscounteValue);
        }
        return discountedProductMap;
    }

    private Map<Product, Integer> appleDiscount(Map<Product, Integer> purchasedProductMap, Map<Product, Integer> discountedProductMap){
        int price = Product.APPLE.getPrice();
        int num = purchasedProductMap.getOrDefault(Product.APPLE,0);
        int discountSetPrice = - 20 * (num/3);
        Integer updateDiscounteValue =  Math.min(discountSetPrice, discountedProductMap.getOrDefault(Product.APPLE, 0));
        discountedProductMap.replace(Product.APPLE,updateDiscounteValue);
        return discountedProductMap;
    }

    private Map<Product, Integer> lighterDiscount(Map<Product, Integer> purchasedProductMap, Map<Product, Integer> discountedProductMap){
        int price = Product.LIGHTER.getPrice();
        int numCigarette = purchasedProductMap.getOrDefault(Product.CIGARETTE,0);
        int numMentholCigarette = purchasedProductMap.getOrDefault(Product.MENTHOL_CIGARETTE,0);

        int discountSetPrice = - price * ((numCigarette + numMentholCigarette)/10);
        Integer updateDiscounteValue =  Math.min(discountSetPrice,discountedProductMap.getOrDefault(Product.LIGHTER, 0));
        discountedProductMap.replace(Product.LIGHTER,updateDiscounteValue);
        return discountedProductMap;
    }

    private Map<Product, Integer> bentoDiscount(Map<Product, Integer> purchasedProductMap, Map<Product, Integer> discountedProductMap){
        int numNori = purchasedProductMap.getOrDefault(Product.NORI_BENTO,0);
        int numSalmon = purchasedProductMap.getOrDefault(Product.SALMON_BENTO,0);
        int numTea = purchasedProductMap.getOrDefault(Product.COFFEE,0) + purchasedProductMap.getOrDefault(Product.TEA,0);

        int regNori = discountedProductMap.getOrDefault(Product.NORI_BENTO, 0);
        int regSalmon = discountedProductMap.getOrDefault(Product.SALMON_BENTO, 0);
        int totalDiscount = regNori + regSalmon;


        for (int i = 0 ; i <= numTea ; i++){
            if((i > numNori)||((numTea-i)) > numSalmon){
                continue;
            }
            int discountNori = Math.min(discountedProductMap.getOrDefault(Product.NORI_BENTO, 0), -20 * i);
            int discountSalmon = Math.min(discountedProductMap.getOrDefault(Product.SALMON_BENTO, 0), -20 * (numTea - i));
            if(totalDiscount > discountNori + discountSalmon){
                regNori = discountNori;
                regSalmon = discountSalmon;
                totalDiscount = discountNori + discountSalmon;
                System.out.println(i);
                System.out.println(totalDiscount);
            }


        }

        discountedProductMap.replace(Product.NORI_BENTO, regNori);
        discountedProductMap.replace(Product.SALMON_BENTO, regSalmon);

        return discountedProductMap;
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


