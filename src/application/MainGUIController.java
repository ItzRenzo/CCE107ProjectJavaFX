package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainGUIController {
    @FXML
    private BorderPane BorderPaneSize;
    @FXML
    private Button ShopButton;
    @FXML
    private Button SigninButton;
    @FXML
    private Button MenuButton;
	@FXML
	private Label HiText;
    
	private String loggedInUsername;
	
    private double mainGUIX;
    private double mainGUIY;
    private double mainGUIWidth;
    private double mainGUIHeight;

    public void saveMainGUIPosition() {
        // Check if the BorderPane has a scene associated with it
        Scene scene = BorderPaneSize.getScene();
        if (scene != null) {
            // Get the stage from the scene
            Stage stage = (Stage) scene.getWindow();
        mainGUIX = stage.getX();
        mainGUIY = stage.getY();
        mainGUIWidth = stage.getWidth();
        mainGUIHeight = stage.getHeight();
        }
    }

    public void restoreMainGUIPosition() {
        Stage stage = (Stage) BorderPaneSize.getScene().getWindow();
        stage.setX(mainGUIX);
        stage.setY(mainGUIY);
        stage.setWidth(mainGUIWidth);
        stage.setHeight(mainGUIHeight);
    }
    
    public void showMainGUI() {
        // Call the restoreMainGUIPosition() method after the MainGUI window is shown
        restoreMainGUIPosition();
    }

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
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

    @FXML
    public void initialize() {
        button = MenuButton;
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
}