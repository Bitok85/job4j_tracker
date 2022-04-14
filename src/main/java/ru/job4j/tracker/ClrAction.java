package ru.job4j.tracker;

import java.util.List;

public class ClrAction implements UserAction {
    private Output out;

    public ClrAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Удалить все пустые заявки";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        List<Item> emptyItems = tracker.findAll();
        int count = 0;
        for (Item item : emptyItems) {
            tracker.delete(item.getId());
            count++;
        }
        System.out.println(count);
        return true;
    }
}
