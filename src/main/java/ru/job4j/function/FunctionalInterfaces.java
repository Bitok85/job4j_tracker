package ru.job4j.function;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class FunctionalInterfaces {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        BiConsumer<Integer, String> biCon = (s, s1) -> map.put(s, s1);
        List<String> numbers = List.of("one", "two", "three", "four", "five", "six", "seven");
        int j = 1;
        for (String num : numbers) {
            biCon.accept(j, num);
            j++;
        }
        System.out.println(map);
        BiPredicate<Integer, String> biPred = (i, s) -> (i % 2 == 0 || s.length() == 4);
        for (Integer i : map.keySet()) {
            if (biPred.test(i, map.get(i))) {
                System.out.println("key: " + i + " value: " + map.get(i));
            }
        }
        Supplier<List<String>> sup = () -> new ArrayList<>(map.values());
        List<String> strings = sup.get();
        Function<String, String> func = (s) -> s.toUpperCase();
        Consumer<String> con = (s) -> System.out.println(func.apply(s));
        for (String s : strings) {
            con.accept(s);
        }
    }
}
