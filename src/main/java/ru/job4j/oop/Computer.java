package ru.job4j.oop;

public class Computer {
    private boolean multiMonitor;
    private int ssd;
    private String cpu;

    public Computer(boolean multiMonitor, int ssd, String cpu) {
        this.cpu = cpu;
        this.multiMonitor = multiMonitor;
        this.ssd = ssd;
    }

    public void printInfo() {
        System.out.println("Много мониторов: " + multiMonitor);
        System.out.println("SSD " + ssd + " GB");
        System.out.println("Модель CPU " + cpu);
    }

    public static void main(String[] args) {
        Computer computer = new Computer(true, 500, "Intel Core I7-10700K");
        Computer comp = new Computer(false, 250, "AMD 5500");
        computer.printInfo();
        comp.printInfo();
    }
}
