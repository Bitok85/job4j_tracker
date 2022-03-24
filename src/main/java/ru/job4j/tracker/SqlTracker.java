package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());
    private Connection cn;
    private final String path = "db/update_001.sql";

    public SqlTracker() throws SQLException, IOException {
      init();
      createTab();
      LOG.info("Connection initialized, DB created/already exists");
    }

    public void init() {
        try (InputStream in = SqlTracker.class.
                getClassLoader().
                getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            LOG.error("Connect initialization error", e);
        }
    }

    private void createTab() throws SQLException, IOException {
        try (Statement statement = cn.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get(path)));
            statement.execute(sql);
        } catch (Exception e) {
            LOG.error("table creation error", e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null)  {
            cn.close();
        }
    }

    /**
     *
     * @param item с конструктором item(int id, String name).
     */
    @Override
    public Item add(Item item) {
        Timestamp timestamp = Timestamp.valueOf(item.getDateTime());
        try (PreparedStatement ps = cn.prepareStatement(
                "insert into items (name, created) values (?, ?)",
                Statement.RETURN_GENERATED_KEYS
        )) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, timestamp);
            ps.execute();
            LOG.info("Item '{}' added successfully", item.getName());
            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    item.setId(generatedKeys.getInt(1));
                }
            }
        } catch (Exception e) {
            LOG.error("SQL error while item addition", e);
        }
        return item;
    }

    /**
     *
     * @param item с конструктором item(int id, String name).
     */
    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        Timestamp timestamp = Timestamp.valueOf(item.getDateTime());
        try (PreparedStatement ps = cn.prepareStatement(
                "update items set name = ?, created = ? where id = ?")) {
            ps.setString(1, item.getName());
            ps.setTimestamp(2, timestamp);
            ps.setInt(3, id);
            ps.execute();
            result = true;
            LOG.info("Item '{}' replaced successfully", item.getName());
        } catch (Exception e) {
            LOG.error("Replace error", e);
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (PreparedStatement ps = cn.prepareStatement("delete from items where id = ?")) {
            ps.setInt(1, id);
            ps.execute();
            result = true;
            LOG.info("ID '{}' deleted successfully", id);
        } catch (SQLException e) {
            LOG.error("Delete item error", e);
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement("select * from items")) {
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                            ));
                }
            }
            LOG.info("Items list created");
        } catch (SQLException e) {
            LOG.error("Error", e);
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (PreparedStatement ps = cn.prepareStatement(
                "select * from items where name = ?"
        )) {
            ps.setString(1, key);
            try (ResultSet resultSet = ps.executeQuery()) {
                while (resultSet.next()) {
                    items.add(new Item(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getTimestamp("created").toLocalDateTime()
                    ));
                }
            }
            LOG.info("Items '{}' list created", key);
        } catch (SQLException e) {
            LOG.error("Error", e);
        }
        if (items.size() == 0) {
            LOG.info("Key '{}' doesn't exist in table 'items'", key);
        }
        return items;
    }

    @Override
    public Item findBy(int id) {
        Item result = new Item();
        try (PreparedStatement ps = cn.prepareStatement("select * from items where id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    result.setId(resultSet.getInt("id"));
                    result.setName(resultSet.getString("name"));
                    result.setMemDateTime(resultSet.getTimestamp("created").toLocalDateTime());
                } else {
                    LOG.error("ID '{}' doesn't exist in table items", id);
                }
            }
        } catch (SQLException e) {
            LOG.error("Error", e);
        }
        return result;
    }
}
