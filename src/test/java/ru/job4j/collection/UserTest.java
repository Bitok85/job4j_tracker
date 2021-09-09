package ru.job4j.collection;

import org.junit.Test;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class UserTest {

    @Test
    public void whenAsc() {
        Set<User> users = new TreeSet<>();
        users.add(new User("Petr", 32));
        users.add(new User("Ivan", 31));
        Iterator<User> iterator = users.iterator();
        assertThat(iterator.next(), is(new User("Ivan", 31)));
        assertThat(iterator.next(), is(new User("Petr", 32)));
    }

    @Test
    public void whenComparePetrVsIvan() {
        int rsl = new User("Petr", 32)
                .compareTo(
                        new User("Ivan", 31)
                );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenCompareSameNames() {
        int rsl = new User("Petr", 30)
                .compareTo(
                        new User("Petr", 32)
                );
        assertThat(rsl, lessThan(0));
    }
}
