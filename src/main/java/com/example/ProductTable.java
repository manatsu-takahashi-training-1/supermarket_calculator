//for testing

package com.example;

public enum ProductTable {

    APPLE(1, "りんご", 100),
    ORANGE(2, "みかん", 40),
    GRAPE(3, "ぶどう", 150);

    private final int id;
    private final String name;
    final int price;

    private ProductTable(int id, String name, int price){
      this.id = id;
      this.name = name;
      this.price = price;
    }
}