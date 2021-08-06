package ru.job4j.tracker;

import static org.junit.Assert.*;
import org.junit.Test;

import javax.swing.*;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.nullValue;
import java.lang.*;

public class StartUITest {

    @Test
    public void whenAddItem() {
        String[] answers = {"Test1"};
        Output output = new ConsoleOutput();
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        CreateAction createAction = new CreateAction(output);
        createAction.execute(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Test1");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
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
        assertThat(replaced.getName(), is ("replaced item"));
    }

    @Test
    public  void whenDeleteItem() {
        Tracker tracker = new Tracker();
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
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(output),
                new ExitAction()
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is ("test"));
    }

    @Test
    public void whenReplaceItemUI() {
        Output output = new ConsoleOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        String replacedName = "test2";
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), replacedName, "1"}
        );
        UserAction[] actions = {
                new ReplaceAction(output),
                new ExitAction()
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is (replacedName));
    }

    @Test
    public void whenDeleteItemUI() {
        Output output = new ConsoleOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new DeleteAction(output),
                new ExitAction()
        };
        new StartUI(output).init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is (nullValue()));
    }

    @Test
    public void whenExit() {
        Output out = new StubOutput();
        Input in = new StubInput(
                new String[] {"0"}
        );
        Tracker tracker = new Tracker();
        UserAction [] actions = {
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + System.lineSeparator() +
                        "0. Exit program" + System.lineSeparator()
        ));
    }

    @Test
    public void whenShowAllUI() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        UserAction[] actions = {
                new ShowAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is ("Menu." + ls +
                "0. Show all items" + ls +
                "1. Exit program" + ls +
                "Show all items" + ls +
                item + ls +
                "Menu." + ls +
                "0. Show all items" + ls +
                "1. Exit program" + ls));
    }

    @Test
    public void whenFindByIDUI() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(
                new String[] {"0", String.valueOf(item.getId()), "1"}
        );
        UserAction[] actions = {
                new FindByIDAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is ("Menu." + ls +
                "0. Find item by ID " + ls +
                "1. Exit program" + ls +
                "=== Find item by id ====" + ls +
                item + ls +
                "Menu." + ls +
                "0. Find item by ID " + ls +
                "1. Exit program" + ls));
    }

    @Test
    public void whenFindByNameUI() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        Input in = new StubInput(
                new String[] {"0", item.getName(), "1"}
        );
        UserAction[] actions = {
                new FindByNameAction(out),
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is ("Menu." + ls +
                "0. Find item by name" + ls +
                "1. Exit program" + ls +
                "=== Find items by name ====" + ls +
                item + ls +
                "Menu." + ls +
                "0. Find item by name" + ls +
                "1. Exit program" + ls));
    }

    @Test
    public void whenInvalidExit() {
        String ls = System.lineSeparator();
        Output out = new StubOutput();
        Tracker tracker = new Tracker();
        Input in = new StubInput(
                new String[] {"8", "0"}
        );
        UserAction[] actions = new UserAction[] {
                new ExitAction()
        };
        new StartUI(out).init(in, tracker, actions);
        assertThat(out.toString(), is(
                "Menu." + ls +
                        "0. Exit program" + ls
                        + "Wrong input, you can select: 0 .. 0" + ls
                        + "Menu." + ls
                        + "0. Exit program" + ls
        ));
    }
}

