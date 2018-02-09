/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Voiture;
import Service.ServiceVoiture;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Amina
 */
public class AjoutVoitureController implements Initializable {

    @FXML
    private Label MarqueLab;
    @FXML
    private Label CouleurLab;
    @FXML
    private TextField MarqueField;
    @FXML
    private TextField couleurFileld;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjoutVoiture(ActionEvent event) throws IOException {
        ServiceVoiture sv = new ServiceVoiture();
        Voiture v = new Voiture(MarqueField.getText(),couleurFileld.getText());
        try {
            sv.AjouterVoiture(v);
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Succes.fxml"));
            Parent root =  loader.load();
   //???
            SuccesController frc = loader.getController();
            frc.setSuccesText("Voiture: "+MarqueField.getText()+"  "+couleurFileld.getText()+" Ajoutée");
                 
                Stage stage = new Stage();
                stage.setScene(new Scene(root));  
                stage.show();
                
           
        } catch (SQLException ex) {
            Logger.getLogger(AjoutVoitureController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
