package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.io.IOException;
import java.text.DecimalFormat;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CivicBuyGUIController {
	@FXML
	private Button MenuButton;
	@FXML
	private Button SigninButton;
	@FXML
	private Label HiText;
	@FXML
	private Button HomeButton;
	@FXML
	private Label TotalPrice;
	@FXML
	private ComboBox<String> Month;
	
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
	    Month.getItems().removeAll(Month.getItems());
	    Month.getItems().addAll("No Installment", "12 Months", "18 Months", "24 Months", "36 Months", "48 Months", "60 Months");
	    Month.getSelectionModel().select("No Installment");
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
	@FXML
	public void MonthCalculate(ActionEvent event) {
	    String selectedMonth = Month.getSelectionModel().getSelectedItem();
	    if (selectedMonth != null && !selectedMonth.isEmpty()) {
	        if (selectedMonth.equals("No Installment")) {
	            // Reset the TotalPrice to the original price
	            TotalPrice.setText("₱1,135,000.00");
	            TotalPrice.setFont(Font.font("System", 36));
	        } else {
	            TotalPrice.setFont(Font.font("System", 26));
	            String[] monthParts = selectedMonth.split(" ");
	            int months = Integer.parseInt(monthParts[0]);
	            double totalPrice = 1135000;
	            double monthlyPayment = totalPrice / months;

	            // Format the monthly payment to two decimal places
	            DecimalFormat df = new DecimalFormat("#.00");
	            String formattedMonthlyPayment = "₱ " + df.format(monthlyPayment) + " / month";

	            // Display the formatted monthly payment
	            TotalPrice.setText(formattedMonthlyPayment);
	        }
	    }
	}
}
