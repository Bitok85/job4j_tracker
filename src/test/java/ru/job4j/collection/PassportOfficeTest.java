package ru.job4j.collection;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class PassportOfficeTest {

    @Test
    public void add() {
        Citizen citizen = new Citizen("paws and tail", "Kot Matroskin");
        PassportOffice office = new PassportOffice();
        office.add(citizen);
        assertThat(office.get(citizen.getPassport()), is(citizen));
    }

    @Test
    public void addNotDublicate() {
        PassportOffice office = new PassportOffice();
        Citizen citizen1 = new Citizen("paws and tail", "Kot Matroskin");
        Citizen citizen2 = new Citizen("paws and tail", "Sharik the Dog");
        office.add(citizen1);
        office.add(citizen2);
        assertFalse(office.add(citizen2));
    }
}
