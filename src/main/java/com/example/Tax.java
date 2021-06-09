package com.example;

public class Tax {
    //合計金額クラスを生成する？他のクラスとどうやってつながるんやろ
    int beforeTax;
    int afterTax;

    public Tax(int beforeTax) {
        this.beforeTax = beforeTax;
        if (beforeTax < 0) {
            throw new RuntimeException();
        }
    }

    public int getTaxIncludedPrice(int beforeTax) {
        double temp = (double)beforeTax * 1.08;  // Sales tax = 8%
        int afterTax = (int)temp;
        return afterTax;
    }

}
