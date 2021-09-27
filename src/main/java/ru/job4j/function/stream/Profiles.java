package ru.job4j.function.stream;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream().map(
                Profile::getAddress
        ).collect(Collectors.toList())
                .stream()
                .distinct()
                .sorted(Comparator.comparing(Address::getCity))
                .collect(Collectors.toList());
    }
}
