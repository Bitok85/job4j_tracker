package ru.job4j.tracker;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.nullValue;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Collections;

public class StartUITest {

    @Test
    public void whenAddItem() {
        String[] answers = {"Test1"};
        Output output = new ConsoleOutput();
        Input input = new StubInput(answers);
        MemTracker tracker = new MemTracker();
        CreateAction createAction = new CreateAction(output);
        createAction.execute(input, tracker);
        Item created = tracker.findAll().get(0);
        Item expected = new Item("Test1");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenReplaceItem() {
        MemTracker tracker = new MemTracker();
        Item item = new Item("New item");
        Output output = new ConsoleOutput();
        tracker.add(item);
        String[] answers = {
                String.valueOf((item.getId())),
                "replaced item"
        };
        ReplaceAction replaceAction = new ReplaceAction(output);
        replaceAction.execute(new StubInput(answers), tracker);
        Item replaced = tracker.findById((item.getId()));
        assertThat(replaced.getName(), is("replaced item"));
    }

    @Test
    public  void whenDeleteItem() {
        MemTracker tracker = new MemTracker();
        Output output = new ConsoleOutput();
        Item item = new Item("test");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId())
        };
        DeleteAction deleteAction = new DeleteAction(output);
        deleteAction.execute(new StubInput(answers), tracker);
        Item deleted = tracker.findById(item.getId());
        assertNull(deleted);
    }

    @Test
    public void whenCreateItemUI() {
        Output output = new ConsoleOutput();
        Input in = new StubInput(
                new String[] {"0", "test", "1"}
        );
        MemTracker tracker = new MemTracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new CreateAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll().get(0).getName(), is("test"));
    }

    @Test
    public void whenReplaceItemUI() {
        Output output = new ConsoleOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test"));
        String replacedName = "test2";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ReplaceAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is(replacedName));
    }

    @Test
    public void whenDeleteItemUI() {
        Output output = new ConsoleOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new DeleteAction(output));
        actions.add(new ExitAction());
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is(nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        MemTracker tracker = new MemTracker();
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator()
                        + "0. Exit program" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllUI() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ShowAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu." + ls
                + "0. Show all items" + ls
                + "1. Exit program" + ls
                + "Show all items" + ls
                + item + ls
                + "Menu." + ls
                + "0. Show all items" + ls
                + "1. Exit program" + ls));
    }

    @Test
    public void whenFindByIDUI() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByIDAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu." + ls
                + "0. Find item by ID " + ls
                + "1. Exit program" + ls
                + "=== Find item by id ====" + ls
                + item + ls
                + "Menu." + ls
                + "0. Find item by ID " + ls
                + "1. Exit program" + ls));
    }

    @Test
    public void whenFindByNameUI() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new FindByNameAction(out));
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is("Menu." + ls
                + "0. Find item by name" + ls
                + "1. Exit program" + ls
                + "=== Find items by name ====" + ls
                + item + ls
                + "Menu." + ls
                + "0. Find item by name" + ls
                + "1. Exit program" + ls));
    }

    @Test
    public void whenInvalidExit() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        MemTracker tracker = new MemTracker();
        Input in = new StubInput(
                new String[] {"8", "0"}
        );
        ArrayList<UserAction> actions = new ArrayList<>();
        actions.add(new ExitAction());
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + ls
                        + "0. Exit program" + ls
                        + "Wrong input, you can select: 0 .. 0" + ls
                        + "Menu." + ls
                        + "0. Exit program" + ls
        ));
    }

    @Test
    public void whenInvalidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"one", "1"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(1));
    }

    @Test
    public void whenValidInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(0));
    }

    @Test
    public void whenInvalidNegativeInput() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"-3", "3"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(-3));
    }

    @Test
    public void whenFewInputs() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"3", "6"}
        );
        ValidateInput input = new ValidateInput(out, in);
        int selected = input.askInt("Enter menu:");
        assertThat(selected, is(3));
        selected = input.askInt("Enter menu:");
        assertThat(selected, is(6));
    }

    @Test
    public void whenSortById() {
        List<Item> items = Arrays.asList(
                new Item(3, "abc"),
                new Item(5, "def"),
                new Item(1, "ghk")
        );
        List<Item> expectedAscending = Arrays.asList(
                new Item(1, "ghk"),
                new Item(3, "abc"),
                new Item(5, "def")
        );
        List<Item> expectedDescending = Arrays.asList(
                new Item(5, "def"),
                new Item(3, "abc"),
                new Item(1, "ghk")
        );
        Collections.sort(items);
        assertThat(items, is(expectedAscending));
        Collections.reverse(items);
        assertThat(items, is(expectedDescending));
    }

    @Test
    public void whenSortItemsByName() {
        List<Item> items = Arrays.asList(
                new Item(3, "def"),
                new Item(5, "abc"),
                new Item(1, "ghk")
        );
        List<Item> expectedAscending = Arrays.asList(
                new Item(5, "abc"),
                new Item(3, "def"),
                new Item(1, "ghk")
        );
        Collections.sort(items, new ItemNameComparator());
        assertThat(items, is(expectedAscending));
    }
}
