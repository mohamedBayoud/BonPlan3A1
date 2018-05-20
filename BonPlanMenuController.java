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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class BonPlanMenuController implements Initializable {

    @FXML 
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private TextField email_client;
    @FXML
    private Button retour;
    @FXML
    private Button sedeconnecter;
    @FXML
    private Button ChoixH;
    @FXML
    private Button ChoixR;
	@FXML
	private TextField email;
	@FXML
	private TextField email_client1;
	@FXML
	private TextField id_client1;
	@FXML
	private TextField role1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController frc = loader.getController();
        retour.getScene().setRoot(root);
        frc.setRole(role.getText());
        frc.setEmail(email.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
    }

    @FXML
    private void sedeconnecter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController frc = loader.getController();
        sedeconnecter.getScene().setRoot(root);
    }

    public void setRole(String role) {
        this.role.setText(role);
    }

    

    public void setIDClient(int id) {
        String s = "";
        s = s + id;
        this.id_client.setText(s);
    }

    @FXML
    private void ChoixH(ActionEvent event) throws IOException {

        if (this.role.getText().equals("bonplaneur")) {

            
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuChoix.fxml"));
        Parent root = loader.load();
        MenuChoixController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
		//frc.setEmail(email.getText());
        ChoixH.getScene().setRoot(root);
        } 
		else if (this.role.getText().equals("membre")){
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterH.fxml"));
        Parent root = loader.load();
        ConsulterHController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setEmail_client(email.getText());
		frc.setRole(role.getText());
		String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setId_client(id_client.getText());
        ChoixH.getScene().setRoot(root);
        }
		else
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminHotel.fxml"));
        Parent root = loader.load();
        AdminHotelController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
                ChoixH.getScene().setRoot(root);
		}
    }

    @FXML
    private void ChoixR(ActionEvent event) throws IOException {
        if (this.role.getText().equals("bonplaneur")) {
	    FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuChoix1.fxml"));
        Parent root = loader.load();
        MenuChoix1Controller frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
		 frc.setRole(role1.getText());
           frc.setEmail(email_client1.getText());
	       String e = id_client.getText();
           int r = Integer.parseInt(e);
           frc.setIDClient(r);           
        ChoixR.getScene().setRoot(root);
		
        } else if (this.role.getText().equals("membre")){
            
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterR.fxml"));
        Parent root = loader.load();
        ConsulterRController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
		 frc.setRole(role1.getText());
           frc.setEmail(email_client1.getText());
	       String e = id_client.getText();
           int r = Integer.parseInt(e);
           frc.setIDClient(r);  
        ChoixR.getScene().setRoot(root);
        }
		else
		{
		FXMLLoader loader = new FXMLLoader(getClass().getResource("RestoAdmin.fxml"));
        Parent root = loader.load();
        RestoAdminController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
        ChoixR.getScene().setRoot(root);
		}
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

	public TextField getEmail_client1() {
		return email_client1;
	}

	public void setEmail_client1(TextField email_client1) {
		this.email_client1 = email_client1;
	}

	public TextField getId_client1() {
		return id_client1;
	}

	public void setId_client1(TextField id_client1) {
		this.id_client1 = id_client1;
	}

	public TextField getRole1() {
		return role1;
	}

	public void setRole1(TextField role1) {
		this.role1 = role1;
	}
 
 

	
}
