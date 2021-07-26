package ru.job4j.tracker.profession.doctor;

public class MedTeacher extends Doctor {
    private boolean students;
    private boolean interns;

    public MedTeacher(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public void teachSuccess() {
    }
}
