package ru.job4j.function.stream;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class ProfilesTest {

    @Test
    public void collectTest() {
        Profiles pr = new Profiles();
        Address address1 = new Address("Tver", "Lenina str", 5, 10);
        Address address2 = new Address("Moscow", "Stroiteley str", 5, 2);
        Profile profile1 = new Profile(address1);
        Profile profile2 = new Profile(address2);
        List<Profile> profiles = List.of(
                profile1,
                profile2
        );
        List<Address> rsl = pr.collect(profiles);
        String expected1 = "Tver";
        int expected2 = 5;
        assertThat(rsl.get(0).getCity(), is(expected1));
        assertThat(rsl.get(1).getHome(), is(expected2));
    }
}
