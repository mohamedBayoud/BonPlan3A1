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
 * @author Asus Pc
 */
public class MenuController implements Initializable {

    @FXML
    private Button BonPlan;
    @FXML
    private Button Evenement;
    @FXML
    private Button Promotion;
    @FXML
    private Button Transport;
    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private TextField email_client;
	@FXML
	private TextField email_client1;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void BonPlan(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("BonPlanMenu.fxml"));
        Parent root = loader.load();
        BonPlanMenuController frc = loader.getController();
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
        BonPlan.getScene().setRoot(root);
    }

    @FXML
    private void Evenement(ActionEvent event) throws IOException {
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEvent.fxml"));
        Parent root = loader.load();
        MenuEventController frc = loader.getController();
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
        Evenement.getScene().setRoot(root);
        frc.setRole(role.getText());
           if (!this.role.getText().equals("bonplaneur") ) {
            
            frc.getAjouterEvent().setVisible(false);
        }
    
    }

    @FXML
    private void Promotion(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PromotionMenu.fxml"));
        Parent root = loader.load();
        PromotionMenuController frc = loader.getController();
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
        Promotion.getScene().setRoot(root);
		if (role.getText().equals("membre"))
		frc.getAjouter().setVisible(false);
    }

    @FXML
    private void Transport(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Choix.fxml"));
        Parent root = loader.load();
        ChoixController frc = loader.getController();
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
        Transport.getScene().setRoot(root);
    }

    public void setEmail(String email) {
        this.email_client.setText(email);
    }

    public void setRole(String role) {

        this.role.setText(role);
    }

    void setIDClient(int id) {
        String s = "";
        s = s + id;
        this.id_client.setText(s);
    }

	public void setEmail_client(String email_client) {
		this.email_client.setText(email_client); 
	}
	

}
