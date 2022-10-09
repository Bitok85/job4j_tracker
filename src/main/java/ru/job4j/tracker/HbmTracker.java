package ru.job4j.tracker;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.ArrayList;
import java.util.List;

public class HbmTracker implements Store, AutoCloseable {
    private static final Logger LOG = LogManager.getLogger(HbmTracker.class.getName());
    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    @Override
    public Item add(Item item) {
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(item);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean rsl = false;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                    "UPDATE Item SET name = :fName WHERE id = :fId")
                    .setParameter("fName", item.getName())
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
            session.close();

        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return rsl;
    }

    @Override
    public boolean delete(int id) {
        boolean rsl = false;
        try (Session session = sf.getCurrentSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                    "DELETE Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .executeUpdate() > 0;
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return rsl;
    }

    @Override
    public List<Item> findAll() {
        List<Item> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery("FROM Item").list();
            session.getTransaction();
            session.close();
            return rsl;
        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return new ArrayList<>();
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> rsl = new ArrayList<>();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = session.createQuery(
                    "FROM Item WHERE name = :fName")
                    .setParameter("fName", key)
                    .list();
        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return new ArrayList<>();
    }

    @Override
    public Item findById(int id) {
        Item rsl = null;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            rsl = (Item) session.createQuery("FROM Item WHERE id = :fId")
                    .setParameter("fId", id)
                    .getSingleResult();
        } catch (HibernateException e) {
            LOG.error("HibernateException", e);
        }
        return rsl;
    }

    @Override
    public void close() throws Exception {
        StandardServiceRegistryBuilder.destroy(registry);
    }
}
