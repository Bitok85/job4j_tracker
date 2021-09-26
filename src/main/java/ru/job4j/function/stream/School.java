package ru.job4j.function.stream;

import javax.swing.plaf.synth.SynthDesktopIconUI;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class School {
    public List<Student> collect(List<Student> students, Predicate<Student> predict) {
        return students.stream()
                .filter(predict)
                .collect(Collectors.toList());
    }

    public Map<String, Student> studentsToMap(List<Student> students) {
        Comparator<Student> comp = (left, right) -> left.getSurname().compareTo(right.getSurname());
        return students.stream().sorted(comp).collect(
                Collectors.toMap(
                        Student::getSurname,
                        student -> student,
                        (surname1, surname2) -> surname1
                )
        );
    }
}
