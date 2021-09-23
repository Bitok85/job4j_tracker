package ru.job4j.search;
import javax.print.attribute.standard.PresentationDirection;
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
        Predicate<Person> name = (s) -> s.getName().contains(key);
        Predicate<Person> phone = (s) -> s.getPhone().contains(key);
        Predicate<Person> address = (s) -> s.getAddress().contains(key);
        Predicate<Person> surName = (s) -> s.getSurname().contains(key);
        Predicate<Person> combine = (s) -> {
            return name.test(s).or(phone.test(s)).or(address.test(s)).or(surName.test(s));
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
