package ru.job4j.inheritance;

public class HTMLReport extends TextReport {

    @Override
    public String generate(String name, String body) {
        return "<h1>" + name + "</h1>" +
                "<br/>" +
                "<span>" + body + "</span>";
    }

    public static void main(String[] args) {
        HTMLReport report = new HTMLReport();
        String text = report.generate("Карл", "Маркс");
        System.out.println(text);
    }
}
