package ru.job4j.collection;

import java.util.ArrayList;

public class UsageArrayList {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>(10);
        names.add("Petr");
        names.add("Ivan");
        names.add("Stepan");
        for (Object name : names) {
            System.out.println(name);
        }
    }
}
