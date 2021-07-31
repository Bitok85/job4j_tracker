package ru.job4j.poly;

public class Car implements Vehicle {

    @Override
    public void move() {
        System.out.println(getClass().getSimpleName() + " Driving on the road");
    }

    @Override
    public void using() {
        System.out.println(getClass().getSimpleName() + " using gasoline");
    }
}
