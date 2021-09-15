package ru.job4j.collection;

import java.util.*;

public class Departments {
    /**
     * Метод для добавления недостающих вышестоящих в иерархии подразделений департаментов.
     * @param deps список департаментов (напр. "k1/sk1")
     * @return список департаментов с добавленным вышестоящим подразделением ("k1", "k1/sk1")
     */
    public static List<String> fillGap(List<String> deps) {
        Set<String> tmp = new LinkedHashSet<>();
        for (String value : deps) {
            String start = value.split("/")[0];
            tmp.add(start);
            for (String el : value.split("/")) {
                if (!Objects.equals(start, el)) {
                    start += "/" + el;
                    tmp.add(start);
                }
            }
        }
        return new ArrayList<>(tmp);
    }

    public static void sortAsc(List<String> orgs) {
        Collections.sort(orgs);
    }

    public static void sorDesc(List<String> orgs) {
    }
}
