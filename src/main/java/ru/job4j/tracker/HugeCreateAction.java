package ru.job4j.tracker;

public class HugeCreateAction implements UserAction {

    private final Output out;

    public HugeCreateAction(Output out) {
        this.out = out;
    }

    @Override
    public String name() {
        return "Добавить 1млн пустых заявок";
    }

    @Override
    public boolean execute(Input input, Store tracker) {
        out.println("Adds 1млн empty items");
        for (int i = 0; i < 100000; i++) {
            tracker.add(new Item());
        }
        out.println("Добавлены 1млн пустых заявок");
        return true;
    }
}
