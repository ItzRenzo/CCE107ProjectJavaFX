package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;

public class MainGUIController {
	@FXML
	private Button ShopButton;
	@FXML
	private Button SigninButton;

	// Event Listener on Button[#ShopButton].onAction
	@FXML
	public void ShopButtonClick(ActionEvent event) throws IOException {
	    Parent shopRoot = FXMLLoader.load(getClass().getResource("CarGUI1.fxml"));
	    Scene shopScene = new Scene(shopRoot);
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(shopScene);
	    stage.show();
	}
	// Event Listener on Button[#SigninButton].onAction
	@FXML
	public void SigninButtonClick(ActionEvent event) throws IOException {
	    Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
	    Scene loginScene = new Scene(loginRoot);
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(loginScene);
	    stage.show();
	}
}
