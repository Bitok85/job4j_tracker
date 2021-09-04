package ru.job4j.tracker.profession.doctor;

public class MedPractice extends Doctor {
    private boolean leadMed;
    private boolean needAssistant;

    public MedPractice(String name, String surname,
                       String education, String birthday, int experience, String specialization) {
        super(name, surname, education, birthday, experience, specialization);
    }

    private void operationSuccess() {
    }
}
