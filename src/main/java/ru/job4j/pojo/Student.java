package ru.job4j.pojo;

import java.util.Date;

public class Student {
    private String bio;
    private String university;
    private int group;
    private Date admission;

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setAdmission(Date admission) {
        this.admission = admission;
    }

    public String getBio() {
        return bio;
    }

    public String getUniversity() {
        return university;
    }

    public int getGroup() {
        return group;
    }

    public Date getAdmission() {
        return admission;
    }
}
