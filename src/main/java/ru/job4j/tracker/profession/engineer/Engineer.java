package ru.job4j.tracker.profession.engineer;

import ru.job4j.tracker.profession.Profession;

public class Engineer extends Profession {
    private int age;
    private String experience;

    public Engineer(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public int getAge() {
        return age;
    }

    public String getExperience() {
        return experience;
    }
}
