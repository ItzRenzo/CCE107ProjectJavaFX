package application;

import javafx.fxml.FXML;
import java.sql.*;
import javax.swing.JOptionPane;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.CheckBox;

public class LoginGUIController {
    @FXML
    private TextField UserTF;
    @FXML
    private PasswordField PasswordTF;
    @FXML
    private CheckBox showpass;
    @FXML
    private Button NoAcc;
    @FXML
    private Button LoginButton;
    
    private String loggedInUsername;
    
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
    }
    
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    @FXML
    public void ShowPassCheck(ActionEvent event) {
        if (showpass.isSelected()) {
            PasswordTF.setPromptText(PasswordTF.getText());
            PasswordTF.setText("");
            PasswordTF.setDisable(true);
        } else {
            PasswordTF.setPromptText("");
            PasswordTF.setDisable(false);
        }
    }

    @FXML
    public void NoAccClick(ActionEvent event) throws IOException {
        Parent registerRoot = FXMLLoader.load(getClass().getResource("RegisterGUI.fxml"));
        Scene registerScene = new Scene(registerRoot);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(registerScene);
        stage.show();
    }


    @FXML
    public void LoginButtonClick(ActionEvent event) {
        String userText = UserTF.getText();
        String pwdText = PasswordTF.getText();

        if (userText.isEmpty() || (pwdText.isEmpty() && !showpass.isSelected())) {
            JOptionPane.showMessageDialog(null, "Username or Password is empty");
        } else {
            try (Connection connection = DatabaseHandler.getConnection();
                 PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? AND password = ?")) {
                statement.setString(1, userText);
                
                if (showpass.isSelected()) {
                    statement.setString(2, PasswordTF.getPromptText());
                } else {
                    statement.setString(2, pwdText);
                }

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        loggedInUsername = resultSet.getString("username");
                        JOptionPane.showMessageDialog(null, "Login Successful" + " \nWelcome: " + loggedInUsername);
                        
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainGUI.fxml"));
                        Parent mainRoot = loader.load();
                        MainGUIController mainGUIController = loader.getController();
                        mainGUIController.setLoggedInUsername(loggedInUsername);
                        mainGUIController.saveMainGUIPosition(); // Save the position and size of MainGUI
                        
                        Scene mainScene = new Scene(mainRoot);
                        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                        stage.setScene(mainScene);
                        stage.show();
                        // Call the showMainGUI() method after the MainGUI window is loaded and shown
                        mainGUIController.showMainGUI();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Failed. Please try again!");
                        UserTF.setText("");
                        PasswordTF.setText("");
                        UserTF.requestFocus();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
