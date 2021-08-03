package ru.job4j.tracker;

import static org.junit.Assert.*;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.Matchers.nullValue;

public class StartUITest {

    @Test
    public void whenAddItem() {
        String[] answers = {"Test1"};
        Input input = new StubInput(answers);
        Tracker tracker = new Tracker();
        CreateAction createAction = new CreateAction();
        createAction.execute(input, tracker);
        Item created = tracker.findAll()[0];
        Item expected = new Item("Test1");
        assertThat(created.getName(), is(expected.getName()));
    }

    @Test
    public void whenReplaceItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("New item");
        tracker.add(item);
        String[] answers = {
                String.valueOf((item.getId())),
                "replaced item"
        };
        ReplaceAction replaceAction = new ReplaceAction();
        replaceAction.execute(new StubInput(answers), tracker);
        Item replaced = tracker.findById((item.getId()));
        assertThat(replaced.getName(), is ("replaced item"));
    }

    @Test
    public  void whenDeleteItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test");
        tracker.add(item);
        String[] answers = {
                String.valueOf(item.getId())
        };
        DeleteAction deleteAction = new DeleteAction();
        deleteAction.execute(new StubInput(answers), tracker);
        Item deleted = tracker.findById(item.getId());
        assertNull(deleted);
    }

    @Test
    public void whenCreateItemUI() {
        Input in = new StubInput(
                new String[] {"0", "test", "1"}
        );
        Tracker tracker = new Tracker();
        UserAction[] actions = {
                new CreateAction(),
                new ExitAction()
        };
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findAll()[0].getName(), is ("test"));
    }

    @Test
    public void whenReplaceItemUI() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("test"));
        String replacedName = "test2";
        Input in = new StubInput(
                new String[] {"0", "1", replacedName}
        );
        UserAction[] actions = {
                new ReplaceAction(),
                new ExitAction()
        };
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()).getName(), is (replacedName));
    }

    @Test
    public void whenDeleteItemUI() {
        Tracker tracker = new Tracker();
        Item item = tracker.add(new Item("Deleted item"));
        Input in = new StubInput(
                new String[] {"0", "1"}
        );
        UserAction[] actions = {
                new DeleteAction(),
                new ExitAction()
        };
        new StartUI().init(in, tracker, actions);
        assertThat(tracker.findById(item.getId()), is (nullValue()));
    }
}

