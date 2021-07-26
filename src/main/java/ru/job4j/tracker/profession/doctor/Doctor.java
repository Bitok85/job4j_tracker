package ru.job4j.tracker.profession.doctor;

import ru.job4j.tracker.profession.Profession;

public class Doctor extends Profession {
    private int experience;
    private String specialization;

    public Doctor(String name, String surname, String education, String birthday, int experience, String specialization) {
        super(name, surname, education, birthday);
    }

    public String getAge() {
        return specialization;
    }

    public int getExperience() {
        return experience;
    }
}



