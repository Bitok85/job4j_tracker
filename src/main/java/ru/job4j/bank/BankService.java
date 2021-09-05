package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankService {
    private Map<User, List<Account>> users = new HashMap<>();

    public void addUser(User user) {
        List<Account> accounts = new ArrayList<>();
        if (!users.containsKey(user.getPassport())) {
            users.put(user, accounts);
        }
    }

    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        boolean check = true;
        List<Account> accounts = users.get(user);
        for (Account elem : accounts) {
            if (account.equals(elem)) {
                check = false;
                break;
            }
        }
        if (user != null && check) {
            users.get(user).add(account);
        }
    }

    public User findByPassport(String passport) {
        User rsl = null;
        for (User user : users.keySet()) {
            if (user.getPassport().equals(passport)) {
                rsl = user;
                break;
            }
        }
        return rsl;
    }

    public Account findByRequisite(String passport, String requisite) {
        Account rsl = null;
        User user = findByPassport(passport);
        List<Account> accounts = users.get(user);
        if (user == null) {
            return null;
        } else {
            for (Account account : accounts) {
                if (account.getRequisite().equals(requisite)) {
                    rsl = account;
                    break;
                }
            }
        }
        return rsl;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean rsl = true;
        Account srcAcc, destAcc;
        srcAcc = findByRequisite(srcPassport, srcRequisite);
        destAcc = findByRequisite(destPassport, destRequisite);
        if (srcAcc.getBalance() < amount) {
            rsl = false;
        } else {
            srcAcc.setBalance(srcAcc.getBalance() - amount);
            destAcc.setBalance(destAcc.getBalance() + amount);
        }
        return rsl;
    }
}
