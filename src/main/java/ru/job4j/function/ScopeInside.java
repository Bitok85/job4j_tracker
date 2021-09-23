package ru.job4j.function;

import java.util.function.Supplier;

public class ScopeInside {
    public static void main(String[] args) {
        int[] number = {1, 2, 3};
        int total = 0;
        for (int i = 0; i < number.length; i++) {
            int num = number[i];
            int tmpTotal = total;
            total = add(
                    () -> tmpTotal + num
            );
        }
        System.out.println(total);
    }

    public static Integer add(Supplier<Integer> calc) {
        return calc.get();
    }
}
