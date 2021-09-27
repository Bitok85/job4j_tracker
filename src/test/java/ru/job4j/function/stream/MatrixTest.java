package ru.job4j.function.stream;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import java.util.List;

import static org.junit.Assert.*;

public class MatrixTest {

    @Test
    public void matrixToList() {
        Matrix matrix = new Matrix();
        int[][] arr = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        List<Integer> rsl = matrix.matrixToList(arr);
        List<Integer> expected = List.of(
                1, 2, 3, 4, 5, 6, 7, 8, 9
        );
        assertThat(rsl, is(expected));
    }
}