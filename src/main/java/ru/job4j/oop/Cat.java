package ru.job4j.oop;

public class Cat {
    private String name;
    private String food;
    public void show() {
        System.out.print(this.name + " ");
        System.out.println(this.food);
    }
    public void nick(String catName) {
        this.name = catName;
    }

    public void eat(String meat) {
        this.food = meat;
    }

    public static void main(String[] args) {
        System.out.println("There are gav's food.");
        Cat gav = new Cat();
        gav.nick("Gav");
        gav.eat("kotleta");
        gav.show();
        System.out.println("There are black's food.");
        Cat black = new Cat();
        black.nick("Black");
        black.eat("fish");
        black.show();
    }
}
