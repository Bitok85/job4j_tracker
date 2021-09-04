package ru.job4j.pojo;

public class Library extends Book {

    public Library(String name, int pages) {
        super(name, pages);
    }

    public static void main(String[] args) {
        Book javaPhil = new Book("Java philosophy", 564);
        Book algoGrok = new Book("Groking algorithms", 180);
        Book cleanCode = new Book("Clean Code", 254);
        Book biteOfPy = new Book("Bite of Python", 894);
        Book[] literature = new Book[4];
        literature[0] = javaPhil;
        literature[1] = algoGrok;
        literature[2] = cleanCode;
        literature[3] = biteOfPy;
        for (Book lit : literature) {
            System.out.println(lit.getName() + " " + lit.getPages());
        }
        System.out.println();
        Book temp = literature[0];
        literature[0] = literature[3];
        literature[3] = temp;
        for (Book lit : literature) {
            if ("Clean Code".equals(lit.getName())) {
                System.out.println(lit.getName() + " " + lit.getPages());
            }
        }

    }
}
