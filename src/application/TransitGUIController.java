package application;

import javafx.fxml.FXML;
import javafx.print.PrinterJob;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.transform.Scale;
import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import java.io.IOException;
import javax.mail.MessagingException;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.swing.JOptionPane;

import javafx.event.ActionEvent;

import javafx.scene.control.Label;

import javafx.scene.control.TextArea;
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
        // Print using Java Print
        String[] options = {"Email", "Java Print"};
        int choice = JOptionPane.showOptionDialog(
                null,
                "How would you like to proceed?",
                "Print Options",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );
        
        if (choice == 0) {
            // Email the receipt
            sendEmailWithReceipt(transitInfo);
        } else if (choice == 1) {
            // Print the receipt using Java Print
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

        // Send email with the receipt
        sendEmailWithReceipt(transitInfo);
    }
    

    private void sendEmailWithReceipt(String receiptContent) {
        // Set the SMTP server properties for Gmail
        Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        // Email credentials
        String senderEmail = "e.bersamin.546349@umindanao.edu.ph";
        String password = "";

        // Retrieve the recipient's email address from the database
        String recipientEmail = getUserEmailFromDatabase(loggedInUsername);

        // Create a session with authentication
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(senderEmail, password);
            }
        });

        try {
            // Create a new email message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(senderEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("ALFIE CAR DEALERSHIP RECEIPT");
            message.setText(receiptContent);

            // Send the email
            Transport.send(message);
            
            JOptionPane.showMessageDialog(null, "Email sent successfully");
            System.out.println("Email sent successfully.");
        } catch (AuthenticationFailedException e) {
            System.out.println("Authentication failed. Please check your email credentials.");
        } catch (MessagingException e) {
            System.out.println("Error sending email: " + e.getMessage());
        }
    }

    private String getUserEmailFromDatabase(String loggedInUsername) {
        // Get the user data from the database
        DatabaseHandler databaseHandler = new DatabaseHandler();
        String[] userData = databaseHandler.getUserByUsername(loggedInUsername);

        if (userData != null) {
            return userData[2]; // Return the email address from the user data
        } else {
            return ""; // Return an empty string if user data is not found
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
