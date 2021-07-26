package ru.job4j.pojo;

import java.util.Date;

public class College extends Student{

    public static void main(String[] args) {
        Student student1 = new Student();
        student1.setBio("Михаил Ломоносов");
        student1.setUniversity("Cлавяно-греко-латинская академия");
        student1.setGroup(1);
        student1.setAdmission(new Date());
        System.out.println(student1.getBio());
        System.out.println(student1.getUniversity());
        System.out.println(student1.getGroup());
        System.out.println(student1.getAdmission());
    }
}

