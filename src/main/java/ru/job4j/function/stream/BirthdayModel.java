package ru.job4j.function.stream;

public class BirthdayModel {
    private String name;
    private String surname;
    private int years;
    private double budget;
    private String place;
    private boolean animation;

    static class Builder {
        private String name;
        private String surname;
        private int years;
        private double budget;
        private String place;
        private boolean animation;

        Builder buildName(String name) {
            this.name = name;
            return this;
        }

        Builder buildSurname(String surname) {
            this.surname = surname;
            return this;
        }

        Builder buildYears(int years) {
            this.years = years;
            return this;
        }

        Builder buildBudget(double budget) {
            this.budget = budget;
            return this;
        }

        Builder buildPlace(String place) {
            this.place = place;
            return this;
        }

        Builder buildAnimation(boolean animation) {
            this.animation = animation;
            return this;
        }

        BirthdayModel build() {
            BirthdayModel birthdayModel = new BirthdayModel();
            birthdayModel.name = name;
            birthdayModel.surname = surname;
            birthdayModel.years = years;
            birthdayModel.budget = budget;
            birthdayModel.place = place;
            birthdayModel.animation = animation;
            return birthdayModel;
        }
    }

    public static void main(String[] args) {
        BirthdayModel birthdayModel = new Builder().buildName("Kot")
                .buildSurname("Matrokin")
                .buildYears(30)
                .buildBudget(200D)
                .buildPlace("Prostokvashino")
                .buildAnimation(true)
                .build();
        System.out.println(birthdayModel);
    }

    @Override
    public String toString() {
        return "BirthdayModel{"
                + "name='" + name + '\''
                + ", surname='" + surname + '\''
                + ", years=" + years
                + ", budget=" + budget
                + ", place='" + place + '\''
                + ", animation=" + animation
                + '}';
    }
}
