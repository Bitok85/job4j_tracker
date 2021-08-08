package ru.job4j.ooa;

public class Airbus extends Aircraft {
    private static final int COUNT_ENGINE = 2;
    private static final int COUNT_ENGINE380 = 4;

    private String name;

    public Airbus(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void printModel() {
        System.out.println("Модель самолёта " + name);
    }

    public void printCountEngine(Airbus airbus) {
        System.out.println((airbus.getName().equals("A380")) ?
                "Количество двигателей равно: " + COUNT_ENGINE380 :
                "Количество двигателей равно: " + COUNT_ENGINE);
    }

    @Override
    public String toString() {
        return "Airbus{"
                + "name='" + name + '\''
                + '}';
    }
}
