/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class IsncriptionController implements Initializable {

    @FXML
    private Button retour;
    @FXML
    private Button inscrire;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker date;
    @FXML
    private TextField cin;
    @FXML
    private TextField num_tel;
    @FXML
    private TextField mail;
    @FXML
    private TextField login;
    @FXML
    private PasswordField mdp;
    @FXML
    private ComboBox<String> role;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          role.getItems().addAll("membre", "bonplanneur");
        // TODO
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController frc = loader.getController();
        retour.getScene().setRoot(root);

    }

    @FXML
    private void inscrire(ActionEvent event) throws SQLException {
        
         String c = (String) role.getValue();
		          Date  d1 = java.sql.Date.valueOf(date.getValue());

        ServiceUser Su=new ServiceUser();
		
		User U=new User(nom.getText(),prenom.getText(),cin.getText(),num_tel.getText(),d1,mail.getText(),c,login.getText(),mdp.getText());
		
			Su.AjouterUser(U);
		
    }

}
