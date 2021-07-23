package ru.job4j.oop;

public class Error {
    private boolean active;
    private int status;
    private String message;

    public void printData() {
        System.out.println("Ошибка? " + active);
        System.out.println("Номер ошибки " + status);
        System.out.println("Сообщение " + message);
    }

    public Error(boolean active, int status, String message) {
        this.active = active;
        this.status = status;
        this.message = message;
    }

    public static void main(String[] args) {
        Error error = new Error(false, 0, "Okay");
        //Error dumbError = new Error();
        error.printData();
        //dumbError.printData();
        //public Error()     *** куда и как я должен добавить тут конструктор Error() по умолчанию, чтобы иметь возможность вывести dumbError?
    }
}
