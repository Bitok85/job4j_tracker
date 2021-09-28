package ru.job4j.oop;

public class DummyDic {
    public String engToRus(String eng) {
        return "Неизвестное слово " + eng;
    }

    public static void main(String[] args) {
        DummyDic dict = new DummyDic();
        System.out.println(dict.engToRus("bruh"));
    }
}
