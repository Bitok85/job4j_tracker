package ru.job4j.pojo;

public class ShopDrop extends Shop{

    public ShopDrop(String name, int count) {
        super(name, count);
    }

    public static Product[] delete(Product[] products, int index) {
        products[index] = null;
        for (int i = 0; i < products.length - 1; i++) {
            if (products[i] == null) {
                for (int j = i; j < products.length - 1; j++) {
                    products[j] = products[j + 1];
                }
                break;
            }
        }
        products[products.length - 1] = null;
        return products;
    }
}
