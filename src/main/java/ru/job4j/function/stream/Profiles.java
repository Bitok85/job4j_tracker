package ru.job4j.function.stream;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Profiles {
    public List<Address> collect(List<Profile> profiles) {
        return profiles.stream()
                .sorted(Comparator.comparing(profile -> profile.getAddress().getCity()))
                .distinct()
                .map(
                        Profile::getAddress
                ).collect(Collectors.toList());
    }
}
