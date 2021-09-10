package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] left = o1.split("\\.");
        String[] right = o2.split("\\.");
        return Integer.compare(Integer.parseInt(left[0]), Integer.parseInt(right[0]));
    }
}
