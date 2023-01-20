package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String hostName = "localhost";
    private static final String port = "3306";
    private static final String userName = "root";
    private static final String password ="password";
    private static final String dataBaseName = "usersdb";
    private static SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + dataBaseName, userName, password);
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            Configuration configuration = new Configuration();
            properties.setProperty(AvailableSettings.DRIVER, "com.mysql.cj.jdbc.Driver");
            properties.setProperty(AvailableSettings.URL, "jdbc:mysql://" + hostName + ":" + port + "/" + dataBaseName);
            properties.setProperty(AvailableSettings.USER, userName);
            properties.setProperty(AvailableSettings.PASS, password);
            properties.setProperty(AvailableSettings.DIALECT, "org.hibernate.dialect.MySQLDialect");
            properties.setProperty(AvailableSettings.SHOW_SQL, "true");
            configuration.addProperties(properties);
            configuration.addAnnotatedClass(User.class);
            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
            try {
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return sessionFactory;
    }
}
