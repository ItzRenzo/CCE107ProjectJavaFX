package application;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;

public class CarGUI1Controller {
	@FXML
	private Button SUVButton;
	@FXML
	private Button SedanButton;
	@FXML
	private Button PickupButton;
	@FXML
	private AnchorPane SUVGUI;
	@FXML
	private AnchorPane SedanGUI;
	@FXML
	private AnchorPane PickUpGUI;

	// Event Listener on Button[#SUVButton].onAction
	@FXML
	public void SUVButtonClick(ActionEvent event) {
	    SUVGUI.setVisible(true);
	    PickUpGUI.setVisible(false);
	    SedanGUI.setVisible(false);
		// TODO Autogenerated
	}
	// Event Listener on Button[#SedanButton].onAction
	@FXML
	public void SedanButtonClick(ActionEvent event) {
	    SUVGUI.setVisible(false);
	    PickUpGUI.setVisible(false);
	    SedanGUI.setVisible(true);
	}
	// Event Listener on Button[#PickupButton].onAction
	@FXML
	public void PickupButtonClick(ActionEvent event) {
	    SUVGUI.setVisible(false);
	    PickUpGUI.setVisible(true);
	    SedanGUI.setVisible(false);
		// TODO Autogenerated
	}
}
