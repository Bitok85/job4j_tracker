package ru.job4j.poly;

public class Plane implements Vehicle {

    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " Flying by air");
    }

    @Override
    public void using() {
        System.out.println(getClass().getSimpleName() + " using aviation fuel");
    }
}
