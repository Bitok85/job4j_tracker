package ru.job4j.tracker;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.Test;


import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class TrackerHbmTest {

    private final HbmTracker tracker = new HbmTracker();
    private SessionFactory sf = tracker.getSessionFactory();

    @After
    public void wipeTable() {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DELETE FROM items").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1");
        tracker.add(item);
        Item rsl = tracker.findById(item.getId());
        assertThat(rsl.getName()).isEqualTo(item.getName());
    }

    @Test
    public void whenSaveItemAndThenReplaceThenIdMustBeTheSame() {
        Item item1 = tracker.add(new Item("test1"));
        Item item2 = new Item("test2");
        int expectedId = item1.getId();
        tracker.replace(expectedId, item2);
        assertThat(tracker.findById(expectedId).getName()).isEqualTo(item2.getName());
    }

    @Test
    public void whenSaveThenDeleteThenNullById() {
        Item item = tracker.add(new Item("test"));
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }

    @Test
    public void whenSaveListAndFindAllThenGetSameList() {
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item3"));
        assertThat(tracker.findAll()).isEqualTo(List.of(item1, item2, item3));
    }

    @Test
    public void whenSaveListAndFindByNameThenListNames() {
        Item item1 = tracker.add(new Item("item1"));
        Item item2 = tracker.add(new Item("item2"));
        Item item3 = tracker.add(new Item("item3"));
        Item item4 = tracker.add(new Item("item2"));
        assertThat(tracker.findByName("item2")).isEqualTo(List.of(item2, item4));
    }
}
