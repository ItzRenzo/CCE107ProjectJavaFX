package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.scene.control.PasswordField;

import javafx.scene.control.CheckBox;

public class RegisterGUIController {
	@FXML
	private TextField UserTF;
	@FXML
	private PasswordField PasswordTF;
	@FXML
	private CheckBox showpass;
	@FXML
	private Button AlrAcc;
	@FXML
	private Button RegisterButton;
	@FXML
	private TextField EmailTF;

	// Event Listener on CheckBox[#showpass].onAction
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
	// Event Listener on Button[#AlrAcc].onAction
	@FXML
	public void AlrAccClick(ActionEvent event) throws IOException {
	    Parent loginRoot = FXMLLoader.load(getClass().getResource("LoginGUI.fxml"));
	    Scene loginScene = new Scene(loginRoot);
	    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	    stage.setScene(loginScene);
	    stage.show();
	}
	// Event Listener on Button[#RegisterButton].onAction
	// Event Listener on Button[#RegisterButton].onAction
	@FXML
	public void RegisterButtonClick(ActionEvent event) {
	    String name = UserTF.getText();
	    String email = EmailTF.getText();
	    String password = new String(PasswordTF.getText());

	    // Establish database connection
	    try (Connection connection = DatabaseHandler.getConnection();
	         PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
	        statement.setString(1, name);

	        try (ResultSet resultSet = statement.executeQuery()) {
	            if (resultSet.next()) {
	                JOptionPane.showMessageDialog(null, "Username already exists");
	            } else {
	                // Insert the registration information into the database
	                String insertQuery = "INSERT INTO users (username, email, password) VALUES (?, ?, ?)";
	                PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
	                insertStatement.setString(1, name);
	                insertStatement.setString(2, email);
	                insertStatement.setString(3, password);

	                int rowsAffected = insertStatement.executeUpdate();

	                if (rowsAffected == 1) {
	                    JOptionPane.showMessageDialog(null, "Registration Successful");

	                    FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginGUI.fxml"));
	                    Parent mainRoot = loader.load();

	                    Scene mainScene = new Scene(mainRoot);
	                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
	                    stage.setScene(mainScene);
	                    stage.show();
	                } else {
	                    JOptionPane.showMessageDialog(null, "Registration Failed");
	                }

	                insertStatement.close();
	            }

	            resultSet.close();
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}
}
