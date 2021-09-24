package ru.job4j.function.stream;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ArrayStream {
    public static void main(String[] args) {
        List<Integer> list = List.of(
                -2, 4, 6, -4, 10
        );
        List<Integer> negNums = list.stream().filter(
                num -> num >= 0
        ).collect(Collectors.toList());
        negNums.forEach(System.out::println);
    }
}
