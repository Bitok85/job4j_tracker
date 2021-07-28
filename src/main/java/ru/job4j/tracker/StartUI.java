package ru.job4j.tracker;

import ru.job4j.tracker.Item;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StartUI {

    public static void main(String[] args) {
        Item testDateTime = new Item();
        LocalDateTime dirtyDateTime = testDateTime.getDateTime();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String cleanDateTime = dirtyDateTime.format(formatter);
        System.out.println(cleanDateTime);
        Item testToStr = new Item(12, "Gav");
        System.out.println(testToStr);
    }
}
