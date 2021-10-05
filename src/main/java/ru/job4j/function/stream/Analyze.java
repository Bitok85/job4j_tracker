package ru.job4j.function.stream;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Analyze {
    public static double averageScore(Stream<Pupil> stream) {
        OptionalDouble rsl = stream.flatMapToInt(
                    s -> s.getSubjects()
                        .stream()
                        .mapToInt(Subject::getScore)
                )
                .average();
        return rsl.isPresent() ? rsl.getAsDouble() : -1D;
    }

    public static List<Tuple> averageScoreBySubject(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(
                        pupil.getName(),
                        pupil.getSubjects()
                                .stream()
                                .mapToInt(Subject::getScore)
                                .average()
                                .getAsDouble()
                ))
                .collect(Collectors.toList());
    }

    public static List<Tuple> averageScoreByPupil(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(
                        Subject::getName,
                        LinkedHashMap::new,
                        Collectors.averagingDouble(Subject::getScore)
                ))
                .entrySet()
                .stream()
                .map(
                        value -> new Tuple(
                                value.getKey(),
                                value.getValue()
                        )
                    )
                .collect(Collectors.toList());
    }

    public static Tuple bestStudent(Stream<Pupil> stream) {
        return stream.map(pupil -> new Tuple(
                pupil.getName(),
                pupil.getSubjects()
                        .stream()
                        .mapToDouble(Subject::getScore)
                        .sum()
                )
        ).
                max(Comparator.comparing(Tuple::getScore))
                .get();
    }

    public static Tuple bestSubject(Stream<Pupil> stream) {
        return stream.flatMap(pupil -> pupil.getSubjects().stream())
                .collect(Collectors.groupingBy(
                        Subject::getName,
                        LinkedHashMap::new,
                        Collectors.summingDouble(Subject::getScore)
                ))
                .entrySet()
                .stream()
                .map(
                        value -> new Tuple(
                                value.getKey(),
                                value.getValue()
                        )
                )
                .max(Comparator.comparing(Tuple::getScore))
                .get();
    }
}
