package ru.job4j.collection;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class JobSortTest {
    @Test
    public void whenAscendByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Write tests", 2),
                new Job("Add classes", 3)
        );
        List<Job> expected = Arrays.asList(
                new Job("Add classes", 3),
                new Job("Write tests", 2)
        );
        jobs.sort(new JobAscByName());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenAscendByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Add classes", 3),
                new Job("Write tests", 2)
        );
        List<Job> expected = List.of(
                new Job("Write tests", 2),
                new Job("Add classes", 3)
        );
        jobs.sort(new JobAscByPriority());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenDescendByName() {
        List<Job> jobs = Arrays.asList(
                new Job("Add classes", 3),
                new Job("Write tests", 2)
        );
        List<Job> expected = List.of(
                new Job("Write tests", 2),
                new Job("Add classes", 3)
        );
        jobs.sort(new JobDescByName());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenDescendByPriority() {
        List<Job> jobs = Arrays.asList(
                new Job("Add classes", 3),
                new Job("Write tests", 2)
        );
        List<Job> expected = List.of(
                new Job("Add classes", 3),
                new Job("Write tests", 2)
        );
        jobs.sort(new JobDescByPriority());
        assertThat(jobs, is(expected));
    }

    @Test
    public void whenDescComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority =
                new JobDescByName().thenComparing(new JobDescByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenDescComparatorByPriorityAndName() {
        Comparator<Job> cmpPriorityName =
                new JobDescByPriority().thenComparing(new JobDescByName());
        int rsl = cmpPriorityName.compare(
                new Job("Fix bug", 1),
                new Job("Impl test", 1)
        );
        assertThat(rsl, greaterThan(0));
    }

    @Test
    public void whenAscComparatorByNameAndPriority() {
        Comparator<Job> cmpNamePriority = new JobAscByName().thenComparing(new JobAscByPriority());
        int rsl = cmpNamePriority.compare(
                new Job("Impl task", 0),
                new Job("Impl task", 1)
        );
        assertThat(rsl, lessThan(0));
    }

    @Test
    public void whenAscComparatorByPriorityAndName() {
        Comparator<Job> cmpPriorityName = new JobAscByPriority().thenComparing(new JobAscByName());
        int rsl = cmpPriorityName.compare(
                new Job("Impl task", 0),
                new Job("Fix bug", 1)
        );
        assertThat(rsl, lessThan(0));
    }
}
