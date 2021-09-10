package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель данных Account,
 * которая включает поля с номерами реквизитов и суммы на счёте, а также
 * их геттеры и сеттеры и переопределённые методы сравнения.
 * @author Alexander Kagin
 * @Version 1.0
 */
public class Account {
    private String requisite;
    private double balance;

    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    public String getRequisite() {
        return requisite;
    }

    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Переопределение методов equals() и hashCode().
     * Сравнение моделей класса Account происходит только по номерам реквизитов.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
