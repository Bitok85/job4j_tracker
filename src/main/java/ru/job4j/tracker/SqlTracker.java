package ru.job4j.tracker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.io.InputStream;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());
    private Connection cn;
    private final String path = "db/scripts/update_001.sql";

    public SqlTracker() {
            init();
            createTab();
            LOG.info("Connection initialized, DB created/already exists");
    }

    public SqlTracker(Connection init) {
        init();
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

    private void createTab() {
        try (Statement statement = cn.createStatement()) {
            String sql = new String(Files.readAllBytes(Paths.get(path)));
            statement.execute(sql);
        } catch (Exception e) {
            LOG.error("Sql/IO exception", e);
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
            result = ps.executeUpdate() > 0;
            item.setId(id);
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
            result = ps.executeUpdate() > 0;
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
                    items.add(getDbItem(resultSet));
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
                    items.add(getDbItem(resultSet));
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
    public Item findById(int id) {
        Item result = null;
        try (PreparedStatement ps = cn.prepareStatement("select * from items where id = ?")) {
            ps.setInt(1, id);
            try (ResultSet resultSet = ps.executeQuery()) {
                if (resultSet.next()) {
                    result = getDbItem(resultSet);
                }
            }
        } catch (SQLException e) {
            LOG.error("SqlException", e);
        }
        return result;
    }

    private Item getDbItem(ResultSet resultSet) {
        Item item = new Item();
        try {
            item.setId(resultSet.getInt("id"));
            item.setName(resultSet.getString("name"));
            item.setDateTime(resultSet.getTimestamp("created").toLocalDateTime());
            } catch (SQLException e) {
                LOG.error("SqlException", e);
                }
        return item;
    }
}

