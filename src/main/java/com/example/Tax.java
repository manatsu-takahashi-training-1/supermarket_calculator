package com.example;

public class Tax {
    //TODO 合計金額クラスを生成する？他のクラスとどうやってつながるんやろ
    int beforeTax;

    // Here is the constructor
    public Tax(int beforeTax) {
        this.beforeTax = beforeTax;
        if (beforeTax < 0) {
            throw new RuntimeException();
        }
    }

    //calculating the sales tax here.
    public int getTaxIncludedPrice(int beforeTax) {
        final int taxRate = 108;    // Sales tax = 8%
        int temp = beforeTax * taxRate / 100;
        int afterTax = temp;       //apply tax
        return afterTax;
    }

}
