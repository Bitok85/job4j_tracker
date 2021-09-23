package ru.job4j.function;

import java.util.Comparator;

public class LambdaUsage {
    public static void main(String[] args) {
        Comparator<String> comp = (left, right) -> {
            System.out.println("Compare - " + right.length() + " : " + left.length());
            return Integer.compare(right.length(), left.length());
        };
    }
}
