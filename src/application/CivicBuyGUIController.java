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
	private Button ShopButton;
	@FXML
	private Label HiText;
	@FXML
	private Button HomeButton;
	@FXML
	private Button BuyNowButton;
	@FXML
	private Label TotalPrice;
	@FXML
	private ComboBox<String> Month;
	
	private String loggedInUsername;
	
    private String formattedMonthlyPayment;

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
	            double totalPrice = 1600000;
	            DecimalFormat df = new DecimalFormat("#,###.00");
	            formattedMonthlyPayment = "₱" + df.format(totalPrice);
	            TotalPrice.setText(formattedMonthlyPayment);
	            TotalPrice.setFont(Font.font("System", 36));
	        } else {
	            TotalPrice.setFont(Font.font("System", 26));
	            String[] monthParts = selectedMonth.split(" ");
	            int months = Integer.parseInt(monthParts[0]);
	            double totalPrice = 1600000;
	            double monthlyPayment = totalPrice / months;

	            // Format the monthly payment to two decimal places
	            DecimalFormat df = new DecimalFormat("#.00");
	            formattedMonthlyPayment = "₱" + df.format(monthlyPayment) + " / month";
	            System.out.println("Formatted Monthly Payment: " + formattedMonthlyPayment);

	            // Display the formatted monthly payment
	            TotalPrice.setText(formattedMonthlyPayment);
	        }
	    }
	}
    @FXML
    public void BuyNowButtonClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("GarageGUI.fxml"));
        Parent root;
        try {
            root = loader.load();
            GarageGUIController garageGUIController = loader.getController();
            garageGUIController.setLoggedInUsername(loggedInUsername);
            garageGUIController.setFormattedMonthlyPayment(formattedMonthlyPayment); // Pass the formattedMonthlyPayment value
            garageGUIController.loadCarInfo(4); // Replace 1 with the actual car ID
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
