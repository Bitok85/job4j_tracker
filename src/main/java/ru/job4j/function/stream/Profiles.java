package ru.job4j.function.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        List<Address> rsl = new ArrayList<>();
        Comparator<Address> comp = (left, right) -> left.getCity().compareTo(right.getCity());
        rsl = profiles.stream().map(
                Profile::getAddress
        ).collect(Collectors.toList());
        rsl.sort(comp);
        return rsl.stream().
                distinct()
                .collect(Collectors.toList());
    }
}
