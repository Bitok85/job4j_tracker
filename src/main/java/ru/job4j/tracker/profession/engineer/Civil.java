package ru.job4j.tracker.profession.engineer;

public class Civil extends Engineer {
    private boolean building;
    private boolean roadConstruction;

    public Civil(String name, String surname, String education, String birthday) {
        super(name, surname, education, birthday);
    }

    public boolean getBuilding() {
        return building;
    }

    public boolean getRoadConstruction() {
        return roadConstruction;
    }
}
