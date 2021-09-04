package ru.job4j.tracker.profession.engineer;

public class Military extends Engineer {
    private boolean offencive;
    private boolean defencive;

    public Military(String name, String surname,
                    String education, String birthday, int age, String experience) {
        super(name, surname, education, birthday, age, experience);
    }

    public boolean getOffencive() {
        return offencive;
    }

    public boolean getDefencive() {
        return defencive;
    }
}