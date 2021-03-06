/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;
import Entities.Covoiturage;
import Entities.Transport;
import Services.ServiceTransport;
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
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Spinner;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AjouterTransportController implements Initializable {

    @FXML
    private TextField VilleDepart;
    @FXML
    private TextField PrixPlace;
    @FXML
    private TextField HeureArrivee;
    @FXML
    private TextField HeureDepart;
    @FXML
    private TextField NbrPlaceDispo;
    @FXML
    private TextField VilleArrivee;
    
    @FXML
    private SplitMenuButton Covoiturage;
    @FXML
    private Button BUTTON;
    @FXML
    private Button retour;
    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private Button logout;
    @FXML
    private DatePicker DatePicker;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    @FXML
    private void ajouterTransport(javafx.event.ActionEvent event) throws SQLException, IOException 
    { 
        Date d = java.sql.Date.valueOf(DatePicker.getValue());
        String i=id_client.getText();
        int idc=Integer.parseInt(i);
         i= NbrPlaceDispo.getText();
        int nbrPl = Integer.parseInt(i);
        i=HeureDepart.getText();
        int Hdep=Integer.parseInt(i);
        i=HeureArrivee.getText();
        int Harr=Integer.parseInt(i);
        i = PrixPlace.getText();
        float PPlace = Float.parseFloat(i);
        Transport v=new Covoiturage(idc+nbrPl*Hdep,VilleDepart.getText(), VilleArrivee.getText(),nbrPl,Hdep,Harr,d,idc,PPlace);
        ServiceTransport sv = new ServiceTransport();
        sv.AjouterTransport(v); 
        System.out.println(d);
        
    }
     @FXML
     private void retour(ActionEvent event) throws SQLException, IOException 
    {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
            Parent root =  loader.load();
            AffichagegatefController frc = loader.getController();
            retour.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
             if (this.role.getText().equals("membre")){
                  frc.getModifier().setVisible(false);  
                  frc.getHeureArrivee().setVisible(false);
                  frc.getHeureDepart().setVisible(false);
                  frc.getIdTransport().setVisible(false);
                  frc.getPrix().setVisible(false);
                  frc.getNbrPlaceDispo().setVisible(false);
                  frc.getSupprimer().setVisible(false);
                  frc.getVilleArrivee1().setVisible(false);
                  frc.getVilleDepart1().setVisible(false);
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root =  loader.load();
            LoginController frc = loader.getController();
            logout.getScene().setRoot(root);
    }

    @FXML
    private void DatePicker(ActionEvent event) {
    }
     
    
}
