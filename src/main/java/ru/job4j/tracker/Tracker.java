package ru.job4j.tracker;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }

    public Item[] findAll() {
        int count = 0;
        for (Item item : items) {
            if (item == null) {
                break;
            }
            count++;
        }
        Item[] itemsWoNull = new Item[count];
        for (int i = 0; i < itemsWoNull.length; i++) {
            itemsWoNull[i] = items[i];
        }
        return itemsWoNull;
    }

    public Item[] findByName(String key) {
        int count = 0;
        for (Item item: items) {
            if (item != null && key.equals(item.getName())) {
                count++;
            }
        }
        Item[] nameArray = new Item[count];
        count = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && key.equals(items[i].getName())) {
                nameArray[count] = items[i];
                count++;
            }
        }
        return nameArray;
    }
}
