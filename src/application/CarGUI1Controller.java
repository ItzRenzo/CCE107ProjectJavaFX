package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class CarGUI1Controller {
	@FXML
	private Button HomeButton;
	@FXML
	private Button MenuButton;
	@FXML
	private Button ShopButton;
	@FXML
	private Button LocationButton;
	@FXML
	private Button ContactButton;
	@FXML
	private Label HiText;
	@FXML
	private Button ViosShopButton;
	@FXML
	private Button AltisShopButton;
	@FXML
	private Button AccordShopButton;
	@FXML
	private Button CivicShopButton;
	@FXML
	private Button RangerShopButton;
	@FXML
	private Button HiluxShopButton;
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
    @FXML
    private Button SigninButton;
	
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
    public void LocationButtonClick(ActionEvent event) {
    	
    }
    
    @FXML
    public void ContactButtonClick(ActionEvent event) {
    	
    }
    
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
	@FXML
	public void ViosShopButtonClick(ActionEvent event) {
		// TODO Autogenerated
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ViosBuyGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        ViosBuyGUIController viosbuyGUIController = loader.getController();
	        viosbuyGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void AltisShopButtonClick(ActionEvent event) {
		// TODO Autogenerated
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AltisBuyGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        AltisBuyGUIController altisbuyGUIController = loader.getController();
	        altisbuyGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void AccordShopButtonClick(ActionEvent event) {
		// TODO Autogenerated
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccordBuyGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        AccordBuyGUIController accordbuyGUIController = loader.getController();
	        accordbuyGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void CivicShopButtonClick(ActionEvent event) {
		// TODO Autogenerated
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CivicBuyGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        CivicBuyGUIController civicbuyGUIController = loader.getController();
	        civicbuyGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void RangerShopButtonClick(ActionEvent event) {
		// TODO Autogenerated
        FXMLLoader loader = new FXMLLoader(getClass().getResource("RangerBuyGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        RangerBuyGUIController rangerbuyGUIController = loader.getController();
	        rangerbuyGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@FXML
	public void HiluxShopButtonClick(ActionEvent event) {
		// TODO Autogenerated
        FXMLLoader loader = new FXMLLoader(getClass().getResource("HiluxBuyGUI.fxml"));
        Parent root;
		try {
			root = loader.load();
	        HiluxBuyGUIController hiluxbuyGUIController = loader.getController();
	        hiluxbuyGUIController.setLoggedInUsername(loggedInUsername);
	        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	        stage.setScene(new Scene(root));
	        stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
