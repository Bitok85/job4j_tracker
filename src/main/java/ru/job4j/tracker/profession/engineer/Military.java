package ru.job4j.tracker.profession.engineer;

public class Military extends Engineer {
    private boolean offencive;
    private boolean defencive;

    public Military(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public boolean getOffencive() {
        return offencive;
    }

    public boolean getDefencive() {
        return defencive;
    }
}