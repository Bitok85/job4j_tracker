package ru.job4j.function.stream;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Matrix {
    public List<Integer> matrixToList(Integer[][] arr) {
        return Stream.of(arr)
                .flatMap(Stream::of)
                .collect(Collectors.toList());
    }
}
