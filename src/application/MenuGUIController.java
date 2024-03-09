package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.Window;

public class MenuGUIController {
	@FXML
	private Button AccountButton;
	@FXML
	private Button SignOutButton;
	@FXML
	private Button CloseMenuButton;
	@FXML
	private Label HiText;
	
    private String loggedInUsername;

    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        HiText.setText("Hi, " + (loggedInUsername != null ? loggedInUsername : "No Account Found!"));
    }
    
    public void openMenuGUI() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuGUI.fxml"));
            Parent root = loader.load();
            Stage stage = new Stage();
            stage.setTitle("MenuGUI"); // Set the title of the MenuGUI window
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void AccountButtonClick(ActionEvent event) {
        // Close other stages
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Stage mainStage = getMainStage(currentStage);
        currentStage.close();

        if (mainStage != null) {
            mainStage.close();
        }

        // Open AccountInfoGUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AccountInfoGUI.fxml"));
        Parent root;
        try {
            root = loader.load();
            AccountInfoGUIController accountInfoGUIController = loader.getController();
            accountInfoGUIController.setLoggedInUsername(loggedInUsername);
            Stage stage = new Stage();
            stage.setScene(new Scene(root, 1366, 768));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
	// Event Listener on Button[#SignOutButton].onAction
    @FXML
    public void SignOutButtonClick(ActionEvent event) throws IOException {
        // Get all open stages
        List<Stage> stages = new ArrayList<>();
        for (Window window : Window.getWindows()) {
            if (window instanceof Stage && window.isShowing()) {
                stages.add((Stage) window);
            }
        }

        // Iterate over the stages and close the one with the title "Alfie Car Dealership"
        for (Stage stage : stages) {
            if (stage.getTitle().equals("Alfie Car Dealership")) {
                stage.close();
                break;
            }
        }

        // Load the LoginGUI
        Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
        Scene loginScene = new Scene(loginRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(loginScene);

        // Set the position of the LoginGUI window to center it on the screen
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        double windowWidth = stage.getWidth();
        double windowHeight = stage.getHeight();
        double windowX = (screenBounds.getWidth() - windowWidth) / 2;
        double windowY = (screenBounds.getHeight() - windowHeight) / 2;
        stage.setX(windowX);
        stage.setY(windowY);

        // Close the current stage and show the LoginGUI window
        stage.show();
    }
	
    private Stage getMainStage(Stage currentStage) {
        List<Window> windows = Stage.getWindows();
        for (Window window : windows) {
            if (window instanceof Stage && window != currentStage) {
                String title = ((Stage) window).getTitle();
                if (title != null && title.equals("Alfie Car Dealership")) {
                    return (Stage) window;
                }
            }
        }
        return null;
    }
    
	// Event Listener on Button[#CloseMenuButton].onAction
	@FXML
	public void CloseMenuButtonClick(ActionEvent event) {
        // Get the source button
        Button button = (Button) event.getSource();
        
        // Get the stage of the button
        Stage stage = (Stage) button.getScene().getWindow();
        
        // Close the stage (window)
        stage.close();
	}
}
