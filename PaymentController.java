/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Payment;
import Entities.User;
import Services.ServiceUser;
import Services.servicePayment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class PaymentController implements Initializable {

    @FXML
    private TextField email;
    @FXML
    private PasswordField pwd;
    @FXML
    private Button login;
	@FXML
	private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void payment(ActionEvent event) throws SQLException, IOException {
               List<Payment> list = new ArrayList<>();
        servicePayment sp = new servicePayment();
        list = servicePayment.ChercherPayment();
        //System.out.println("frr");
        String e = email.getText();
        String e1 = pwd.getText();
                
        for(int i = 0; i <  list.size(); i++){
            
            if (list.get(i).getEmail().equals(e) &&list.get(i).getPwd().equals(e1)) 
            {
                
                Parent pm =FXMLLoader.load(getClass().getResource("payment2.fxml"));
        Scene sm = new Scene(pm, 1280, 720);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();    
        
            
            }
     
        }
        
    }

	@FXML
	private void retour(ActionEvent event) throws IOException {
		Parent pm =FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
        Scene sm = new Scene(pm, 1280, 720);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();
		
	}
        
    }
    

