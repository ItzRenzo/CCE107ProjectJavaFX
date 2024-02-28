package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseHandler {

    private static final String DB_URL = "jdbc:mysql://185.207.164.129:3306/s6260_CCEProjectData";
    private static final String USER = "u6260_j1v7KCJUuY";
    private static final String PASSWORD = "Yj.!4yj8@HNCfdyplr@XFZG3";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }
}
