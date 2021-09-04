package ru.job4j.pojo;

public class Shop extends Product {

    public Shop(String name, int count) {
        super(name, count);
    }

    public static int indexOfNull(Product[] products) {
        int rsl = -1;
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                rsl = i;
                break;
            }
        }
        return rsl;
    }
}
