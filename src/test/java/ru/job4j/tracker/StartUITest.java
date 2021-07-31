package ru.job4j.tracker;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;

public class StartUITest {

    @Test
    public void whenAddItem() {
        String[] answers = {"Test1"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        StartUI.createItem(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Test1");
        assertThat(created.getName(), is(expected.getName()));
    }
}
