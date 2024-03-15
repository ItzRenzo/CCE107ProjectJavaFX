package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class GarageGUIController {
    @FXML
    private Button MenuButton;
    @FXML
    private Button SigninButton;
    @FXML
    private Button ShopButton;
    @FXML
    private Button LocationButton;
    @FXML
    private Button ContactButton;
    @FXML
    private Button PayButton;
    @FXML
    private Label HiText;
    @FXML
    private Button HomeButton;
    @FXML
    private TextArea DescriptionTXT;
    @FXML
    private TextArea InfoTXT;
    
    private String formattedMonthlyPayment = "No Installment";

    private String loggedInUsername;

    private static final String JDBC_URL = "jdbc:mysql://185.207.164.129:3306/s6260_CCEProjectData";
    private static final String DB_USERNAME = "u6260_j1v7KCJUuY";
    private static final String DB_PASSWORD = "Yj.!4yj8@HNCfdyplr@XFZG3";
    
    public void setCarId(int carId) {
        loadCarInfo(carId);
    }
    
    public void setFormattedMonthlyPayment(String formattedMonthlyPayment) {
        this.formattedMonthlyPayment = formattedMonthlyPayment;
    }

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        loadAccountInfo(loggedInUsername); // Call loadAccountInfo here
        if (loggedInUsername != null) {
            HiText.setVisible(true);
            HiText.setText("Hi, " + loggedInUsername);
            SigninButton.setVisible(false);
        } else {
            HiText.setVisible(false);
            SigninButton.setVisible(true);
        }
    }
    
	@FXML
	public void HomeButtonClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        MainGUIController mainGUIController = loader.getController();
	        mainGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
    @FXML
    public void ShopButtonClick(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CarGUI1.fxml"));
        Parent root = loader.load();
        CarGUI1Controller carGUI1Controller = loader.getController();
        carGUI1Controller.setLoggedInUsername(loggedInUsername);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        stage.setTitle("Alfie Car Dealership");
    }
	
    @FXML
    public void SigninButtonClick(ActionEvent event) throws IOException {
        Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        Scene loginScene = new Scene(loginRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);
        
        // Get the dimensions of the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();

        // Calculate the position of the window to center it on the screen
        double windowWidth = loginScene.getWindow().getWidth();
        double windowHeight = loginScene.getWindow().getHeight();
        double windowX = (screenBounds.getWidth() - windowWidth) / 2;
        double windowY = (screenBounds.getHeight() - windowHeight) / 2;

        // Set the position of the window
        stage.setX(windowX);
        stage.setY(windowY);
        
        stage.show();
    }
	
    @FXML
    public void MenuButtonClick(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuGUI.fxml"));
            Parent root = loader.load();
            MenuGUIController menuGUIController = loader.getController();
            menuGUIController.setLoggedInUsername(loggedInUsername);

            Stage stage = new Stage();
            stage.initStyle(StageStyle.UNDECORATED); // Set the stage style to UNDECORATED
            stage.setScene(new Scene(root));

         // Set the position of the new window to the top right corner
            Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
            double screenWidth = screenBounds.getWidth();
            double menuWidth = 311.0; // Set the width of the MenuGUI here
            double menuX = screenWidth - menuWidth;
            double menuY = 0.0;
            

            stage.setX(menuX);
            stage.setY(menuY);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
     private void loadAccountInfo(String loggedInUsername) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
            String sql = "SELECT * FROM users WHERE username = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loggedInUsername);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String userId = resultSet.getString("id");
                String retrievedUsername = resultSet.getString("username");
                String email = resultSet.getString("email");

                String userInfo = "Username: " + retrievedUsername + "\n"
                                + "Email: " + email + "\n"
                                + "ID: " + userId;

                InfoTXT.setText(userInfo);
            } else {
                InfoTXT.setText("User not found");
            }

            connection.close();
            System.out.println("SQL query executed successfully.");
        } catch (SQLException e) {
            System.out.println("Error executing SQL query: " + e.getMessage());
        }
    }

     public void loadCarInfo(int carId) {
    	    try {
    	        Connection connection = DriverManager.getConnection(JDBC_URL, DB_USERNAME, DB_PASSWORD);
    	        String sql = "SELECT carname, cardesc, carprice, cartype FROM cars WHERE carid = ?";
    	        PreparedStatement statement = connection.prepareStatement(sql);
    	        statement.setInt(1, carId);
    	        ResultSet resultSet = statement.executeQuery();

    	        if (resultSet.next()) {
    	            String carName = resultSet.getString("carname");
    	            String carDesc = resultSet.getString("cardesc");
    	            String carPrice = resultSet.getString("carprice");
    	            String carType = resultSet.getString("cartype");

    	            String carInfo = "Car Name: " + carName + "\n\n"
    	                    + "Car Type: " + carType + "\n\n"
    	                    + "Car Description:\n" + carDesc + "\n\n"
    	                    + "Car Price: ₱" + carPrice + "\n\n"
    	                    + "Car Installment: " + (formattedMonthlyPayment != null ? formattedMonthlyPayment : "No Installment");
    	            DescriptionTXT.setText(carInfo);
    	        } else {
    	            DescriptionTXT.setText("Car not found");
    	        }

    	        connection.close();
    	        System.out.println("SQL query executed successfully.");
    	    } catch (SQLException e) {
    	        System.out.println("Error executing SQL query: " + e.getMessage());
    	    }
    	}

     @FXML
     public void PayButtonClick(ActionEvent event) {
         int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to proceed?", "Confirm",
             JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
         if (response == JOptionPane.YES_OPTION) {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("TransitGUI.fxml"));
             Parent root;
             try {
                 root = loader.load();
                 TransitGUIController transitGUIController = loader.getController();
                 transitGUIController.setLoggedInUsername(loggedInUsername);

                 // Pass userInfo and carInfo
                 String userInfo = InfoTXT.getText();
                 String carInfo = DescriptionTXT.getText();
                 transitGUIController.setUserInfo(userInfo);
                 transitGUIController.setCarInfo(carInfo);

                 Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                 stage.setScene(new Scene(root));
                 stage.show();
             } catch (IOException e) {
                 e.printStackTrace();
             }
         }
     }
     
     @FXML
     public void LocationButtonClick(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("LocationGUI.fxml"));
         Parent root;
 		try {
 			root = loader.load();
 	        LocationGUIController locationGUIController = loader.getController();
 	        locationGUIController.setLoggedInUsername(loggedInUsername);
 	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 	        stage.setScene(new Scene(root));
 	        stage.show();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
     }
     
     @FXML
     public void ContactButtonClick(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ContactGUI.fxml"));
         Parent root;
 		try {
 			root = loader.load();
 	        ContactGUIController contactGUIController = loader.getController();
 	        contactGUIController.setLoggedInUsername(loggedInUsername);
 	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
 	        stage.setScene(new Scene(root));
 	        stage.show();
 		} catch (IOException e) {
 			e.printStackTrace();
 		}
     }
}

