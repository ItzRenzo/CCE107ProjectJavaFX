package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class AccountInfoGUIController {
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
	private Label HiText;
	@FXML
	private Button HomeButton;
	@FXML
	private Label UserText;
	@FXML
	private Label EmailText;
	@FXML
	private Label PasswordText;
	@FXML
	private Label AccountIDText;
	
	private String loggedInUsername;
	
    private String retrievedUsername;
    private String email;
    private String password;
    private String id;

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

    Node button;

    public void loadAccountInfo(String loggedInUsername) {
        // Get the user data from the database
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String[] userData = databaseHandler.getUserByUsername(loggedInUsername);

        if (userData != null) {
            id = userData[0];
            retrievedUsername = userData[1];
            email = userData[2];
            password = userData[3];

            UserText.setText("Username: " + retrievedUsername);
            EmailText.setText("Email: " + email);
            AccountIDText.setText("ID: " + id);
            PasswordText.setText("Password: " + password);
        } else {
            // Handle case when user is not found
            UserText.setText("User not found");
            EmailText.setText("");
            AccountIDText.setText("");
            PasswordText.setText("");
        }
        System.out.println("SQL query executed successfully.");
    }
    public void initialize() {
        loadAccountInfo(loggedInUsername);
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
    
	// Event Listener on Button[#MenuButton].onAction
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
	// Event Listener on Button[#HomeButton].onAction
	public void HomeButtonClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        MainGUIController mainGUIController = loader.getController();
	        mainGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
            stage.setTitle("Alfie Car Dealership");
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
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
