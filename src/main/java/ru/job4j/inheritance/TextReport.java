package ru.job4j.inheritance;

public class TextReport {
    public String generate(String name, String body) {
        return name + System.lineSeparator() + body;
    }

    public static void main(String[] args) {
        TextReport report = new TextReport();
        String text = report.generate("Reports name", "Reports body");
        System.out.println(text);
    }
}
