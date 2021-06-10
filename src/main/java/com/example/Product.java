package com.example;

public enum Product {

    APPLE("apple",100,108),
    ORANGE("orange",40,108),
    GRAPE("grape",150,108),
    NORI_BENTO("noriBento",350,108),
    SALMON_BENTO("salmonBento",400,108),
    CIGARETTE("cigarette",420,0),
    MENTHOL_CIGARETTE("mentholCigarette",440,0),
    LIGHTER("lighter",100,108),
    TEA("tea",80,108),
    COFFEE("coffee",100,108);

    private final String productName;
    private final int price;
    private final int tax;

    Product(String productName,int price,int tax){
        this.productName = productName;
        this.price = price;
        this.tax = tax;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public int getTax(){
        return tax;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", price=" + price +
                '}';
    }

}
