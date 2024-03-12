package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.print.PrinterJob;
import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class TransitGUIController {
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
	private Button PrintButton;
	@FXML
	private Label HiText;
	@FXML
	private Button HomeButton;
	@FXML
	private TextArea InfoTXT;
	@FXML
	private TextArea TransitTXT;
	
    private String userInfo;
    private String carInfo;
	
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

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
        updateTransitTXT();
    }

    public void setCarInfo(String carInfo) {
        this.carInfo = carInfo;
        updateTransitTXT();
    }
    
    public void updateTransitTXT() {
    	InfoTXT.setText(userInfo);
    	String transitInfo = "----------------------------------------------------" + "\n" +
                "           ALFIE CAR DEALERSHIP RECEIPT           " + "\n" +
                "----------------------------------------------------" + "\n" +
                "Bill to: " + "\n\n" +
                userInfo + "\n\n" +
                "Car Info: " + "\n\n" +
                carInfo;
        TransitTXT.setText(transitInfo);
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
    
    @FXML
    public void PrintButtonClick(ActionEvent event) {
        String transitInfo = "\n" + "----------------------------------------------------" 
        		+ "\n" +
                "           ALFIE CAR DEALERSHIP RECEIPT           " + "\n" +
                "----------------------------------------------------" + "\n" +
                "Bill to: " + "\n\n" +
                userInfo + "\n\n" +
                "Car Info: " + "\n\n" +
                carInfo;

        Text printText = new Text(transitInfo);
        printText.setFont(new Font(8)); // Set the desired font size here
        PrinterJob printerJob = PrinterJob.createPrinterJob();
        if (printerJob != null && printerJob.showPrintDialog(TransitTXT.getScene().getWindow())) {
            printerJob.getJobSettings().setPageLayout(printerJob.getPrinter().getDefaultPageLayout());
            printText.getTransforms().add(new Scale(0.8, 0.8)); // Adjust the scaling as needed
            boolean success = printerJob.printPage(printText);
            if (success) {
                printerJob.endJob();
            }
            printText.getTransforms().remove(printText.getTransforms().size() - 1);
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
