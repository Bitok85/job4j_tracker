package ru.job4j.poly;

public interface Vehicle {

    void move();

    void using();

    public static void main(String[] args) {
        Vehicle car = new Car();
        Vehicle train = new Train();
        Vehicle plane = new Plane();
        Vehicle[] vehicles = new Vehicle[] {car, train, plane};
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            vehicle.using();
        }
    }
}
