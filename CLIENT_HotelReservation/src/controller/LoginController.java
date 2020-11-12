package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.AuthManager;

public class LoginController implements Initializable {

	private int idClient;
	
	@FXML
	private TextField userNameTxt;
	@FXML
    private PasswordField passwordTxt;
	@FXML
    private Label errorLoginTxt;
	
	private boolean isIdentifiersValid() {
		String username = userNameTxt.getText();
		String password = passwordTxt.getText();
		String result = AuthManager.authentification(username, password);
		if(result == null)
			return false;
		else {
			this.idClient = Integer.parseInt(result);
			return true;
		}

	}
	
	private boolean isIdentifiersEmpty() {
		return userNameTxt.getText().isEmpty() || passwordTxt.getText().isEmpty();
	}
	
	@FXML
	void login(ActionEvent event) {

		if(isIdentifiersEmpty()) {
			errorLoginTxt.setText("Veuillez renseigner vos identifiants");
			errorLoginTxt.setVisible(true);
		}
		else if(isIdentifiersValid()) {
			setHotelScene(event);
		}else {
			errorLoginTxt.setText("Identifiants inexistants");
			errorLoginTxt.setVisible(true);
		}
		
		
		
	}
	
	private void setHotelScene(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/HotelReservation.fxml"));
			loader.setController(new HotelReservationController(idClient));

			Parent hotelBookingParent = loader.load();
			Scene hotelBookingViewScene = new Scene(hotelBookingParent);

			Node source = (Node) event.getSource();
			Stage window = (Stage) source.getScene().getWindow();
		    
			window.setScene(hotelBookingViewScene);
			window.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		errorLoginTxt.setVisible(false);
	}

}
