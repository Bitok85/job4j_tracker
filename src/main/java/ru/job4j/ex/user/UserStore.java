package ru.job4j.ex.user;

public class UserStore {
    public static User findUser(User[] users, String login) throws UserNotFoundException {
        User rsl = null;
        for (User user: users) {
            if (user.getUsername().equals(login)) {
                rsl = user;
                break;
            }
        }
        if (rsl == null) {
            throw new UserNotFoundException("User not found");
        }
        return  rsl;
    }

    public static boolean validate (User user) throws  UserInvalidException {
        boolean rsl = false;
        if (user.isValid() && user.getUsername().length() >= 3) {
            rsl = true;
        }
        if (!rsl) {
            throw  new UserInvalidException("User isn't valid");
        }
        return rsl;
    }

    public static void main(String[] args) {
        User[] users = {
                new User("Matroskin", true),
                new User("Pechkin", false),
                new User("Ma", true)
        };
        try {
            User user = findUser(users, "Ma");
                if (validate(user)) {
                    System.out.println("This user has access");
                }
        } catch (UserInvalidException ei) {
            ei.printStackTrace();
        } catch (UserNotFoundException en) {
            en.printStackTrace();
        }
    }
}


