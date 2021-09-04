package ru.job4j.poly;

public class Train implements Vehicle {
    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " Moving by rails");
    }

    @Override
    public void using() {
        System.out.println(getClass().getSimpleName() + " using electricity");
    }
}
