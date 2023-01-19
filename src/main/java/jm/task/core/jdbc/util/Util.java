package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static String hostName = "localhost";
    private static String port = "3306";
    private static String userName = "root";
    private static String password ="password";
    private static String dataBaseName = "usersdb";

    public static Connection getConnection() throws SQLException{
        return DriverManager.getConnection("jdbc:mysql://" + hostName + ":" + port + "/" + dataBaseName, userName, password);
    }
}
