package ru.job4j.tracker.profession;

public class Doctor extends Profession{
    private String experience;
    private int age;

    public int getAge() {
        return age;
    }

    public String getExperience() {
        return experience;
    }

    public class MedTeacher extends Doctor {
        private boolean students;
        private boolean interns;

        public void teachSuccess() {
        }
    }

    public class MedPractice extends Doctor {
        private boolean leadMed;
        private boolean needAssistant;

        private void operationSuccess(){}
    }
}
