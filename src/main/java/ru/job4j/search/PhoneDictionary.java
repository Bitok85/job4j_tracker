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
        var result = new ArrayList<Person>();
        Predicate<Person> name = (s) -> s.getName().contains(key);
        Predicate<Person> phone = (s) -> s.getPhone().contains(key);
        Predicate<Person> address = (s) -> s.getAddress().contains(key);
        Predicate<Person> surName = (s) -> s.getSurname().contains(key);
        Predicate<Person> combine = name.or(phone).or(address).or(surName);
        for (var person : persons) {
            if (combine.test(person)) {
                result.add(person);
            }
        }
        return result;
    }

    public ArrayList<Person> find(String key) {
        var result = new ArrayList<Person>();
        for (var person : persons) {
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
