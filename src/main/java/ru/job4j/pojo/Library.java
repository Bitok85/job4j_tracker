package ru.job4j.pojo;

public class Library extends Book{

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
        for (int i = 0; i < literature.length; i++) {
            Book lit = literature[i];
            System.out.println(lit.getName() + " " + lit.getPages());
        }
        System.out.println();
        literature[0] = biteOfPy;
        literature[3] = javaPhil;
        for (int i = 0; i < literature.length; i++) {
            Book lit = literature[i];
            if (lit.getName().equals("Clean Code")) {
                System.out.println(lit.getName() + " " + lit.getPages());
            }
        }

    }
}
