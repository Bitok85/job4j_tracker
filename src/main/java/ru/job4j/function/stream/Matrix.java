package ru.job4j.function.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {
    public List<Integer> matrixToList(int[][] arr) {
        return Stream.of(arr)
                .flatMap(row -> Arrays.stream(row).boxed())
                .collect(Collectors.toList());
    }
}
