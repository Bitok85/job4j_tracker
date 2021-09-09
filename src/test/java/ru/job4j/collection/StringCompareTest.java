package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class StringCompareTest {

    @Test
    public void whenStringsAreEqualThenZero() {
        StringCompare compare = new StringCompare();
        int rsl = compare.compare(
                "abc",
                "abc"
        );
        assertThat(rsl, is(0));
    }

    @Test
    public void whenLeftLessThenRightThenNegative() {
        StringCompare compare = new StringCompare();
        int rsl = compare.compare(
                "abc",
                "abcd"
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenRightLessThenLeftThenPositive() {
        StringCompare compare = new StringCompare();
        int rsl = compare.compare(
                "abcd",
                "abc"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenLeftGreaterThenRightThenPositive() {
        StringCompare compare = new StringCompare();
        int rsl = compare.compare(
                "bcd",
                "abcd"
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftGreaterThanRightShouldBePositive() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Petrov",
                "Patrov"
        );
        assertThat(rst, greaterThan(0));
    }

    @Test
    public void secondCharOfLeftLessThanRightShouldBeNegative() {
        StringCompare compare = new StringCompare();
        int rst = compare.compare(
                "Patrova",
                "Petrov"
        );
        assertThat(rst, lessThan(0));
    }
}
