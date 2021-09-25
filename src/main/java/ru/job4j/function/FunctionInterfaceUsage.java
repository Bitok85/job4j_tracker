package ru.job4j.function;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.*;

public class FunctionInterfaceUsage {
    public static void main(String[] args) {
        Supplier<String> sup = () -> "New String for Interface";
        System.out.println(sup.get());
        List<String> list = List.of("one", "two", "three", "one", "two", "three");
        Supplier<Set<String>> sup2 = () -> new HashSet<>(list);
        Set<String> strings = sup2.get();
        for (String s : strings) {
            System.out.println(s);
        }
        Consumer<String> consumer = (s) -> System.out.println(s);
        consumer.accept(sup.get());
        BiConsumer<String, String> biConsumer = (s1, s2) -> System.out.println(s1 + s2);
        biConsumer.accept(sup.get(), " and Second String");
        List<String> list2 = List.of("one", "two", "three", "one", "two", "three");
        Supplier<Set<String>> sup3 = () -> new HashSet<>(list2);
        BiConsumer<Integer, String> biConsumer1 = (s, s1) -> System.out.println(s + s1);
        Set<String> strings1 = sup3.get();
        int i = 1;
        for (String s : strings1) {
            biConsumer1.accept(i++, " is " + s);
        }

        Predicate<String> pred = s -> s.isEmpty();
        System.out.println("Строка пустая: " + pred.test(""));
        System.out.println("Строка пустая: " + pred.test("123"));

        BiPredicate<String, Integer> cond = (s, j) -> s.contains(j.toString());
        System.out.println("Строка содержит подстроку " + cond.test("abc1", 1));
        System.out.println("Строка содержит подстроку " + cond.test("abc1", 3));

        Function<String, Character> function = s -> s.charAt(2);
        System.out.println("Третий символ в строке: " + function.apply("first"));
        System.out.println("Третий символ в строке: " + function.apply("second"));

        BiFunction<String, Integer, String> biFunction = (s, j) -> s.concat(" ").concat(j.toString());
        System.out.println("Результат работы бифункции: " + biFunction.apply("test", 123));

        UnaryOperator<StringBuilder> builder = b -> b.reverse();
        System.out.println("Строка после реверса: " + builder.apply(new StringBuilder("123 456")));

        BinaryOperator<StringBuilder> biBuilder = (b1, b2) -> b1.append(" ").append(b2);
        System.out.println(
                "Строка после объединения: " + biBuilder.apply(
                        new StringBuilder("123"),
                        new StringBuilder("456")
                )
        );
    }
}
