package com.example;

public enum SelectCalcType {

    NOMAL(new Calculator()),
    FLASH_HUM(new TimeSale());

    private final CalcInterface calcInterface;

    SelectCalcType(CalcInterface calcInterface){
        this.calcInterface = calcInterface;
    }

    public CalcInterface getCalcInterface() {
        return calcInterface;
    }
}
