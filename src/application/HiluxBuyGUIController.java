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

public class HiluxBuyGUIController {
	@FXML
	private Button MenuButton;
	@FXML
	private Button SigninButton;
	@FXML
	private Label HiText;
	@FXML
	private Button HomeButton;

	private String loggedInUsername;

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
        SigninButton.setVisible(true);
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
}
