package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public Error() {
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public void printData() {
        System.out.println("Ошибка? " + active);
        System.out.println("Номер ошибки: " + status);
        System.out.println("Сообщение: " + message);
    }

    public static void main(String[] args) {
        Error dumbError = new Error();
        Error error = new Error(false, 0, "Okay");
        dumbError.printData();
        System.out.println();
        error.printData();
    }
}
