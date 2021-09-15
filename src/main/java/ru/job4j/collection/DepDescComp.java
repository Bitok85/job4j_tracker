package ru.job4j.collection;

import java.util.Comparator;
import java.util.Objects;

/**
 * Компаратор сравнивающий первые элементы строк (главные департаменты) по убыванию
 * и последующие элементы по возрастанию.
 */
public class DepDescComp implements Comparator<String> {
    @Override
    public int compare(String o1, String o2) {
        String[] first = o1.split("/");
        String[] second = o2.split("/");
        int rsl = second[0].compareTo(first[0]);
        if (rsl != 0) {
            return rsl;
        } else if (first.length != second.length) {
            rsl = Integer.compare(first.length, second.length);
        } else {
            for (int i = 1; i < first.length; i++) {
                rsl = first[i].compareTo(second[i]);
                if (rsl != 0) {
                    break;
                }
            }
        }
        return rsl;
    }
}
