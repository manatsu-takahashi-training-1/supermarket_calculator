package com.example;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;

public class CreateProductMap {

    private final Map<Product,Integer> productMap;

    CreateProductMap(){
        productMap = new HashMap<>();
    }

    private class A {
        public String name;
        public int quantity;
    }

//     public Map<Product,Integer> create(String json_string) {
    public static void main(String[] args) {

        Gson gson = new Gson();

        List<A> l = gson.fromJson("[{\"name\": \"Apple\", \"quantity\": 3}, {\"name\": \"Orange\", \"quantity\": 2}]", List<A>.class);

//         A a = gson.fromJson("{\"name\": \"Apple\", \"quantity\": 3}", A.class);
//         System.out.println(a.name);
//         System.out.println(a.quantity);

//         List<A> l = gson.fromJson(, List<A>.class);

//         List<String, Integer>

// // Deserialization
// int one = gson.fromJson("1", int.class);
// Integer one = gson.fromJson("1", Integer.class);
// Long one = gson.fromJson("1", Long.class);
// Boolean false = gson.fromJson("false", Boolean.class);
// String str = gson.fromJson("\"abc\"", String.class);
// String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);

//         Map<String,Integer> purchaseMap
// 
//         SearchProduct s = new SearchProduct();
// 
//         for (Map.Entry<String, Integer> e : purchaseMap.entrySet()){
//             Product product = s.search(e.getKey());
//             productMap.put(product,e.getValue());
//         }
// 
//         return productMap;
    }

}
