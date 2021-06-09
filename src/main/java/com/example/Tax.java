package com.example;

public class Tax {
    //TODO 合計金額クラスを生成する？他のクラスとどうやってつながるんやろ
    int beforeTax;
    int afterTax;

    // Here is the constructor
    public Tax(int beforeTax) {
        this.beforeTax = beforeTax;
        if (beforeTax < 0) {
            throw new RuntimeException();
        }
    }

    //calculating the sales tax here.
    public int getTaxIncludedPrice(int beforeTax) {
        final double taxRate = 1.08;    // Sales tax = 8%
        double temp = (double)beforeTax * taxRate;
        int applytax = (int)temp;       //apply tax
        int afterTax = Math.round(applytax); //rounded up to the nearest whole number
        return afterTax;
    }

}
