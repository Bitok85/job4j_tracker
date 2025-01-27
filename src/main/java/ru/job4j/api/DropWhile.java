package ru.job4j.api;

import java.util.List;

public class DropWhile {
    public static void main(String[] args) {
        List.of(1, 2, 3, 4).stream()
                .dropWhile(v -> v < 3)
                .map(v -> "Result" + v)
                .forEach(System.out::println);
    }
}
