package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.*;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS users (id BIGINT PRIMARY KEY AUTO_INCREMENT," +
                    "name VARCHAR(255)," +
                    "lastname VARCHAR(255)," +
                    "age TINYINT)").executeUpdate();
            transaction.commit();
        } catch (Exception ignore) {
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DROP TABLE users").executeUpdate();
            transaction.commit();
        } catch (Exception ignore) {
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.save(new User(name, lastName, age));
            transaction.commit();
        } catch (Exception ignore) {
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            var user = new User();
            user.setId(id);
            session.delete(user);
            transaction.commit();
        } catch (Exception ignore) {
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> resultList = null;
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            resultList = session.createSQLQuery("SELECT * FROM users").addEntity(User.class).list();
            transaction.commit();
        } catch (Exception ignore) {
        }
        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.createSQLQuery("DELETE FROM users").executeUpdate();
            transaction.commit();
        } catch (Exception ignore) {
        }
    }
}
