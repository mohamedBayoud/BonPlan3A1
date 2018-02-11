/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Services.ServiceLogin;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class LoginController implements Initializable {

    @FXML
    private TextField Username;
    @FXML
    private PasswordField Passowrd;
    @FXML
    private Button connect;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void Login(ActionEvent event) {
        ServiceLogin sv = new ServiceLogin();

     

            String login = Username.getText();
            String mdp = Passowrd.getText();
            if (sv.login(login, mdp)) {
                 try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
                Parent root = loader.load();
                //  AcceuilController frc = loader.getController();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                  } catch (IOException ex) {
            Logger.getLogger(AcceuilController.class.getName()).log(Level.SEVERE, null, ex);
        }
            } else {
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ERROR Dialog");
        alert.setHeaderText(null);
        alert.setContentText("Verifier vos donnees!");
        alert.show();
            }

      
    }
}
