package com.example;

public enum Product {
    APPLE("apple",100),
    ORANGE("orange",40),
    GRAPE("grape",150),
    NORI_BENTO("noriBento",350),
    SALMON_BENTO("salmonBento",400),
    CIGARETTE("cigarette",420),
    MENTHOL_CIGARETTE("mentholCigarette",440),
    LIGHTER("lighter",100),
    TEA("tea",80),
    COFFEE("coffee",100);

    private final String productName;
    private final int price;

    Product(String productName,int price){
        this.productName = productName;
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

}