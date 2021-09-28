package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс BankService содержит методы для работы с моделями данных User и Account.
 * Созданные модели данных хранятся в коллекции Map, где ключом является модель User,
 * а значением список его Account моделей.
 * @author Alexander Kagdin
 * @version 1.0
 */
public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    /**
     * @param user, метод добавляет нового пользователя,
     *             проверяя что такого пользователя в коллекции нет
     *             и закрепляя за ним пустой список аккаунтов
     */
    public void addUser(User user) {
        List<Account> accounts = new ArrayList<>();
        users.putIfAbsent(user, accounts);
    }

    /**
     *
     * @param passport по данным паспорта ищет пользователя
     * @param account Проверяет отсутствие дубликатов аккаунтов у найденного пользователя.
     *                Если дубликатов нет, добавляет аккаунт в список аккаунтов пользователя.
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            if (!accounts.contains(account)) {
                accounts.add(account);
            }
        }
    }

    /**
     *
     * @param passport поиск в коллекции пользователя по номеру паспорта
     * @return Если в коллекции пользователь найден, возвращает пользователя.
     * Иначе возвращает null.
     */

    public User findByPassport(String passport) {
        return users.keySet().stream()
                .filter(s -> s.getPassport().equals(passport))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод ищет аккаунт пользователя по реквизитам
     * @param passport сначала проверяется наличие пользователя в коллекции по номеру паспорта
     * @param requisite если пользователь найден, происходит поиск аккаунта с введёнными реквизитами
     * @return аккаунт пользователя. Если пользователь или аккаунт не найдены, null.
     */

    public Account findByRequisite(String passport, String requisite) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> accounts = users.get(user);
            return accounts.stream()
                    .filter(s -> s.getRequisite().equals(requisite))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    /**
     * Метод осуществляет перевод денег с одного аккаунта на другой.
     * Предварительно проверяется возможность перевода:
     * существование пользователей
     * существование счетов
     * достаточная сумма для перевода
     * @param srcPassport паспорт пользователя со счёта которого будут переведены деньги
     * @param srcRequisite реквизиты этого счёта
     * @param destPassport паспорт пользователя, на чей счёт будут переведены деньги
     * @param destRequisite реквизиты этого счёта
     * @param amount переводимая сумма
     * @return булево значение: true перевод осуществлён, иначе false
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = false;
        Account srcAcc = findByRequisite(srcPassport, srcRequisite);
        Account destAcc = findByRequisite(destPassport, destRequisite);
        if (srcAcc != null && destAcc != null) {
            if (srcAcc.getBalance() >= amount) {
                srcAcc.setBalance(srcAcc.getBalance() - amount);
                destAcc.setBalance((destAcc.getBalance() + amount));
                rsl = true;
            }
        }
        return rsl;
    }
}
