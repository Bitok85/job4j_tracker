package ru.job4j.poly;

public class Bus implements Transport {
    @Override
    public void drive() {
        System.out.println("Регулярный рейс");
    }

    @Override
    public void passengers(int pass) {
        System.out.println("Перевозит до " + pass + "пассажиров.");
    }

    @Override
    public int fill(int fuel) {
        int gasTankPrice = fuel * 50;
        return gasTankPrice;
    }
}
