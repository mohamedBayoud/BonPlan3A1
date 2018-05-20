/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class MenuChoix1Controller implements Initializable {

    @FXML
    private Button modifierR;
    @FXML
    private Button ajouterR;
	@FXML
	private Button retour;
	@FXML
	private Button logout;
	@FXML
	private TextField email;
	@FXML
	private TextField role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierR(ActionEvent event) throws IOException {
         
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ModifierBP2.fxml"));
        Parent root = loader.load();
        ModifierBP2Controller frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
		modifierR.getScene().setRoot(root);
    }

    @FXML
    private void ajouterR(ActionEvent event) throws IOException {
        
        
		FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterBP2.fxml"));
        Parent root = loader.load();
        AjouterBP2Controller frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
		ajouterR.getScene().setRoot(root);
    }
	@FXML
	private void retour(ActionEvent event) throws IOException {
        
         FXMLLoader loader = new FXMLLoader(getClass().getResource("BonPlanMenu.fxml"));
        Parent root = loader.load();
        BonPlanMenuController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
        retour.getScene().setRoot(root);
    }

	@FXML
	private void logout(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root =  loader.load();
            LoginController frc = loader.getController();
            logout.getScene().setRoot(root);
	}
public TextField getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email.setText(email);
	}
	 public TextField getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role.setText(role);
	}

	void setIDClient(int r) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
    
}
