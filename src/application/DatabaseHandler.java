package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHandler {

    @SuppressWarnings("unused")
	private String loggedInUsername;
    
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }
	
    private static final String DB_URL = "jdbc:mysql://185.207.164.129:3306/s6260_CCEProjectData";
    private static final String USER = "u6260_j1v7KCJUuY";
    private static final String PASSWORD = "Yj.!4yj8@HNCfdyplr@XFZG3";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASSWORD);
    }

    public String[] getUserByUsername(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT id, username, email, password FROM users WHERE username = ?")) {
            statement.setString(1, username);

            System.out.println("Executing SQL query...");

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String id = String.valueOf(resultSet.getInt("id"));
                    String retrievedUsername = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    String password = resultSet.getString("password");

                    return new String[]{id, retrievedUsername, email, password};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // User not found
    }
    
    public String[] getCar(String username) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement("SELECT carid, carname, cardesc, carprice, cartype FROM cars WHERE username = ?")) {
            statement.setString(1, username);

            System.out.println("Executing SQL query...");

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String carId = String.valueOf(resultSet.getInt("carid"));
                    String carName = resultSet.getString("carname");
                    String carDesc = resultSet.getString("cardesc");
                    String carPrice = resultSet.getString("carprice");
                    String carType = resultSet.getString("cartype");

                    return new String[]{carId, carName, carDesc, carPrice, carType};
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Car not found
    }
}