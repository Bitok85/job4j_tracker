package ru.job4j.map;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class College2 {
    private final Map<Student, Set<Subject>> students;

    public College2(Map<Student, Set<Subject>> students) {
        this.students = students;
    }

    public Optional<Student> findByAccount(String account) {
        return students.keySet()
                .stream()
                .filter(student -> student.getAccount().equals(account))
                .findFirst();
    }

    public Optional<Subject> findBySubjectName(String account, String name) {
        Optional<Student> student = findByAccount(account);
        return student.flatMap(value -> students.get(value)
                .stream()
                .filter(s -> s.getName().equals(name))
                .findFirst());
    }
}