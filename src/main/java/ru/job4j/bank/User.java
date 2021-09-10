package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает модель данных User, которая включает поля номера паспорта и имени пользователя,
 * их геттеры и сеттеры и переопределённые методы сравнения.
 * @author Alexander Kagin
 * @Version 1.0
 */

public class User {
    private String passport;
    private String username;

    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Переопределение методов equals() и hashCode().
     * Сравнение моделей класса User происходит только по номеру паспорта.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}

