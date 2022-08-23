package ru.job4j.tracker;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockUserActionTest {

    private String ln = System.lineSeparator();

    @Test
    public void whenReplaceActionExecuteThenNewItemName() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("Replaced item"));
        String replacedName = "New item name";
        ReplaceAction replaceAction = new ReplaceAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        when(input.askStr(any(String.class))).thenReturn(replacedName);
        replaceAction.execute(input, tracker);
        assertThat(out.toString())
                .isEqualTo("=== Edit item ====" + ln + "Заявка изменена успешно." + ln);
        assertThat(tracker.findAll().get(0).getName()).isEqualTo(replacedName);
    }

    @Test
        public void whenDeleteActionThenEmptyStore() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item"));
        DeleteAction deleteAction = new DeleteAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        assertThat(out.toString())
                .isEqualTo("=== Delete item ====" + ln + "Заявка удалена успешно." + ln);
        assertThat(tracker.findAll().size()).isEqualTo(0);
    }

    @Test
    public void whenFindByIdAction() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("item1"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("item3"));
        FindByIDAction findByIDAction = new FindByIDAction(out);
        Input input = mock(Input.class);
        when(input.askInt(any(String.class))).thenReturn(2);
        when(input.askStr(any(String.class))).thenReturn("item2");
        findByIDAction.execute(input, tracker);
        assertThat(out.toString())
                .isEqualTo("=== Find item by id ====" + ln + tracker.findAll().get(1) + ln);
    }

    @Test
    public void whenFindByNameWithFewSameNamesInStore() {
        Output out = new StubOutput();
        Store tracker = new MemTracker();
        tracker.add(new Item("same"));
        tracker.add(new Item("item2"));
        tracker.add(new Item("same"));
        List<Item> items = tracker.findAll();
        FindByNameAction findByNameAction = new FindByNameAction(out);
        Input input = mock(Input.class);
        when(input.askStr(any(String.class))).thenReturn("same");
        findByNameAction.execute(input, tracker);
        assertThat(out.toString())
                .isEqualTo("=== Find items by name ===="
                        + ln
                        + items.get(0)
                        + ln
                        + items.get(2)
                        + ln
                );
    }
}