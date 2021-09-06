package ru.job4j.bank;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.is;

public class BankServiceTest {

    @Test
    public void addUser() {
        User user = new User("paws and tail", "Kot Matroskin");
        BankService bank = new BankService();
        bank.addUser(user);
        assertThat(bank.findByPassport("paws and tail"), is(user));
    }

    @Test
    public void whenInvalidPassport() {
        User user = new User("paws and tail", "Kot Matroskin");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("123", 50D));
        assertNull(bank.findByRequisite("moustache and tail", "123"));
    }

    @Test
    public void addAccount() {
        User user = new User("paws and tail", "Kot Matroskin");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("123", 200D));
        assertThat(bank.findByRequisite(user.getPassport(), "123").getBalance(), is(200D));
    }

    @Test
    public void whenTransferSameClient() {
        User user = new User("paws and tail", "Kot Matroskin");
        BankService bank = new BankService();
        bank.addUser(user);
        bank.addAccount(user.getPassport(), new Account("123", 200D));
        bank.addAccount(user.getPassport(), new Account("456", 100D));
        bank.transferMoney(user.getPassport(), "123", user.getPassport(), "456", 50D);
        assertThat(bank.findByRequisite(user.getPassport(), "456").getBalance(), is(150D));
    }

    @Test
    public void whenTransferDifferentClients() {
        User cat = new User("paws and tail", "Kot Matroskin");
        User dog = new User("paws and fangs", "Sharik the Dog");
        BankService bank = new BankService();
        bank.addUser(cat);
        bank.addUser(dog);
        bank.addAccount(cat.getPassport(), new Account("123", 200D));
        bank.addAccount(dog.getPassport(), new Account("456", 100D));
        bank.transferMoney(cat.getPassport(), "123", dog.getPassport(), "456", 50D);
        assertThat(bank.findByRequisite(dog.getPassport(), "456").getBalance(), is(150D));
        assertThat(bank.findByRequisite(cat.getPassport(), "123").getBalance(), is(150D));
    }
}