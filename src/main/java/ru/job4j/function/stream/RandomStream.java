package ru.job4j.function.stream;

import java.util.List;
import java.util.Random;
import java.util.stream.Stream;
import java.util.stream.Collectors;

public class RandomStream {
    public static List<Integer> randomFill(int num, int min, int max) {
        Random random = new Random();
        Stream<Integer> stream = random.ints(num, min, max).boxed();
        List<Integer> rsl = stream.filter(
            i -> i > 0
        ).collect(Collectors.toList());
        return rsl;
    }
}
