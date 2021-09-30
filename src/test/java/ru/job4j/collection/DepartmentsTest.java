package ru.job4j.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DepartmentsTest {

    @Test
    public void whenMissed() {
        List<String> input = List.of("k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> rsl = Departments.fillGap(input);
        assertThat(rsl, is(expect));
    }

    @Test
    public void whenNotMissed() {
        List<String> input = List.of("k1", "k1/sk1");
        List<String> expect = List.of("k1", "k1/sk1");
        List<String> rsl = Departments.fillGap(input);
        assertThat(rsl, is(expect));
    }

    @Test
    public void whenMissedSecond() {
        List<String> input = List.of("k1", "k1/sk1/ssk1");
        List<String> expect = List.of("k1", "k1/sk1", "k1/sk1/ssk1");
        List<String> rsl = Departments.fillGap(input);
        assertThat(rsl, is(expect));
    }

    @Test
    public void whenMisedFew() {
        List<String> input = List.of("k1/sk1", "k2/sk1/ssk2");
        List<String> expect = List.of("k1", "k1/sk1", "k2", "k2/sk1", "k2/sk1/ssk2");
        List<String> rsl = Departments.fillGap(input);
        assertThat(rsl, is(expect));
    }
}