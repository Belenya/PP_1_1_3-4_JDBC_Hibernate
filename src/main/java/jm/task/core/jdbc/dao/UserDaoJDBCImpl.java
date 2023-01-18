package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import javax.swing.plaf.nimbus.State;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "age TINYINT)");
        }   catch (SQLException ignore) {
        }
    }

    public void dropUsersTable() {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("DROP TABLE IF EXISTS users");
        }   catch (SQLException ignore) {
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute( "INSERT INTO  users VALUES (NULL, '" + name + "', '" + lastName + "', '" + age + "')");
        } catch (SQLException ignore) {
        }
    }

    public void removeUserById(long id) {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute( "DELETE FROM users WHERE id = " + id);
        } catch (SQLException ignore) {
        }
    }

    public List<User> getAllUsers() {
        ResultSet rs = null;
        List<User> resultList = new ArrayList<>();
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            rs = statement.executeQuery("SELECT * FROM users");
            while (rs.next()) {
                resultList.add(new User(rs.getString("name"), rs.getString("lastname"), rs.getByte("age")));
            }
        } catch (SQLException ignore) {
        }
        return resultList;
    }

    public void cleanUsersTable() {
        try (Connection connection = getConnection()) {
            connection.createStatement().execute( "DELETE FROM users");
        } catch (SQLException ignore) {
        }

    }
}
