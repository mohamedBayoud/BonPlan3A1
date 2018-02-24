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
public class MenuEventController implements Initializable {

    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private TextField email_client;
    @FXML
    private Button AjouterEvent;
    @FXML
    private Button ConsulterEvent;
    @FXML
    private Button retour;
    @FXML
    private Button sedeconnecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
   
    
   
    @FXML
    private void AjouterEvent(ActionEvent event) throws IOException {
          if (this.role.getText().equals("bonplaneur")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventplaneurAjou.fxml"));
            Parent root = loader.load();
           EventplaneurAjouController frc = loader.getController();
            AjouterEvent.getScene().setRoot(root);
             frc.setRole(role.getText());
            String i = id_client.getText();
            frc.setEmail(email_client.getText());
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
        }
    }

    @FXML
    private void ConsulterEvent(ActionEvent event) throws IOException {
         if (this.role.getText().equals("membre")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventMembre.fxml"));
            Parent root = loader.load();
            EventMembreController frc = loader.getController();
            ConsulterEvent.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i = id_client.getText();
            frc.setEmail(email_client.getText());
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);

        } else if (this.role.getText().equals("bonplaneur")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventplaneurAffich.fxml"));
            Parent root = loader.load();
           EventplaneurAffichController frc = loader.getController();
            ConsulterEvent.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i = id_client.getText();
            frc.setEmail(email_client.getText());
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
        } else if (this.role.getText().equals("admin")) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("EventAdmin.fxml"));
            Parent root = loader.load();
            EventAdminController frc = loader.getController();
            ConsulterEvent.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i = id_client.getText();
            AjouterEvent.setVisible(false);
            int nbr = Integer.parseInt(i);
             frc.setIDClient(nbr);

        }
        
        
    }

    public Button getAjouterEvent() {
        return AjouterEvent;
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController frc = loader.getController();
        retour.getScene().setRoot(root);
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
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

    public void setEmail(String email) {
        this.email_client.setText(email);
    }

    public void setIDClient(int id) {
        String s = "";
        s = s + id;
        this.id_client.setText(s);
    }
    

}
