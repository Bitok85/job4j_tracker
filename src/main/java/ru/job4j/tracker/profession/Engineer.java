package ru.job4j.tracker.profession;

public class Engineer extends Profession{
    private int age;
    private String experience;

    public int getAge() {
        return age;
    }

    public String getExperience() {
        return experience;
    }

    public class Military extends Engineer {
        private boolean offencive;
        private boolean defencive;

        public boolean getOffencive() {
            return offencive;
        }

        public boolean getDefencive() {
            return defencive;
        }
    }

    public class Civil extends Engineer {
        private boolean building;
        private boolean roadConstruction;

        public boolean getBuilding() {
            return building;
        }

        public boolean getRoadConstruction() {
            return roadConstruction;
        }
    }

}
