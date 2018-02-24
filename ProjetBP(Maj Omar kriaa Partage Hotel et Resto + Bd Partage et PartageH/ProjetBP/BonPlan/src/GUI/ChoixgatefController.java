/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
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
 * @author esprit
 */
public class ChoixgatefController implements Initializable {

    @FXML
    private Button covoiturage;
    @FXML
    private Button train;
    private TextField id;
    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private Button logout;
    @FXML
    private TextField email;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void covoiturage(ActionEvent event) throws SQLException, IOException 
    {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichagegatef.fxml"));
            Parent root =  loader.load();
            AffichagegatefController frc = loader.getController();
            train.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
           
          
              if (this.role.getText().equals("membre")){
                  frc.getModifier().setVisible(false);  
                  frc.getHeureArrivee().setVisible(false);
                  frc.getHeureDepart().setVisible(false);
                  //frc.getIdTransport().setVisible(false);
                  frc.getPrix().setVisible(false);
                  frc.getNbrPlaceDispo().setVisible(false);
                  frc.getSupprimer().setVisible(false);
                  frc.getVilleArrivee1().setVisible(false);
                  frc.getVilleDepart1().setVisible(false);
                  frc.setEmail(email.getText());
                  frc.getDatePicker().setVisible(false);
         }
             else if (this.role.getText().equals("admin")){
                  
                  frc.getAjoutCov().setVisible(false);
                  frc.getParticiper().setVisible(false);
                  
         }
              

    }
    @FXML
    private void train(ActionEvent event) throws SQLException, IOException 
    {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageTrain.fxml"));
            Parent root =  loader.load();
            AffichageTrainController frc = loader.getController();
            train.getScene().setRoot(root);
            frc.setRole(role.getText());
            
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
          
              if (this.role.getText().equals("membre")){
                  frc.getModifier().setVisible(false);
                  frc.getEco1().setVisible(false);
                  frc.getHeureArrivee().setVisible(false);
                  frc.getHeureDepart().setVisible(false);
                 // frc.getIdTransport().setVisible(false);
                  frc.getLuxe().setVisible(false);
                  frc.getNbrPlaceDispo().setVisible(false);
                  frc.getSupprimer().setVisible(false);
                  frc.getVilleArrivee1().setVisible(false);
                  frc.getVilleDepart1().setVisible(false);
                  frc.setEmail(email.getText());
                  frc.getIdTransport().setVisible(false);
                  frc.getDatePicker().setVisible(false);
         }
              else if (this.role.getText().equals("admin")){
                  
                  frc.getParticiper().setVisible(false);
                  
         }

    }  

    public void setRole(String role) {
        
        this.role.setText(role);
    }
    public void setIDClient(int id) {
        String s="";
        s=s+id;
        this.id_client.setText(s);
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
}
