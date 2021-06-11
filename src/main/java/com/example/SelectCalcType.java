package com.example;

public enum SelectCalcType {

    NORMAL(new Calculator()),
    FLASH_HUM(new TimeSale());

    private final CalcInterface calcInterface;

    private SelectCalcType(CalcInterface calcInterface) {
        this.calcInterface = calcInterface;
    }

    public CalcInterface getCalcInterface() {
        return this.calcInterface;
    }

}

