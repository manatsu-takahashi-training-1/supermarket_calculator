package com.example;

public class Tax {

    private final int taxRateInPercent = 8;

    public int getTaxIncludedPrice(int beforeTax) {
        return (beforeTax * (100 + taxRateInPercent) / 100);
    }

}

