package ru.job4j.search;
import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class PhoneDictionary {
    private ArrayList<Person> persons = new ArrayList<>();

    public void add(Person person) {
        this.persons.add(person);
    }

    public ArrayList<Person> funcFind(String key) {
        ArrayList<Person> result = new ArrayList<>();
        Predicate<String> pred = (s) -> s.contains(key);
        Predicate<Person> combine = (s) -> {
            return pred.test(s.getName())
                    || pred.test(s.getPhone())
                    || pred.test(s.getAddress())
                    || pred.test(s.getSurname());
        };
        for (Person person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    public ArrayList<Person> find(String key) {
        ArrayList<Person> result = new ArrayList<>();
        for (Person person : persons) {
            if (
                    person.getName().contains(key)
                            || person.getPhone().contains(key)
                            || person.getAddress().contains(key)
                            || person.getSurname().contains(key)
            ) {
                result.add(person);
            }
        }
        return result;
    }
}
