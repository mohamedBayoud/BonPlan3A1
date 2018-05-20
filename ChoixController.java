/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Covoiturage;
import Entities.User;
import Services.ServiceTransport;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class ChoixController implements Initializable {

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
    @FXML
    private TextField role1;
    @FXML
    private TextField id_client1;
    
    @FXML
    private TextField email1;
    @FXML
    private Button retour;
    @FXML
    private Button covoiturage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
		train.setVisible(false);
        // TODO
    }    
    @FXML
    private void covoiturage(ActionEvent event) throws SQLException, IOException 
    {
             FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
            Parent root =  loader.load();
            AffichageController frc = loader.getController();
            covoiturage.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
            List<Covoiturage> list = new ArrayList<>();
            ServiceTransport s = new ServiceTransport();
            list=s.AfficherCovoiturage();
             int x=0;
                for (int j =0;j<list.size();j++){
                if (list.get(j).getValidation()==0)
                    x++;
                }
              if (this.role.getText().equals("membre") || this.role.getText().equals("bonplaneur")){
                  frc.getModifier().setVisible(false);  
                  frc.getHeureArrivee().setVisible(false);
                  frc.getHeureDepart().setVisible(false);
                  frc.getPrix().setVisible(false);
                  frc.getNbrPlaceDispo().setVisible(false);
                  frc.getSupprimer().setVisible(false);
                  frc.getVilleArrivee1().setVisible(false);
                  frc.getVilleDepart1().setVisible(false);
                  frc.setEmail(email.getText());
                  frc.getDatePicker().setVisible(false);
                  frc.getdateDepart1().setVisible(false);
         }
             else if (this.role.getText().equals("admin"))
             {
                  frc.getChercher().setVisible(true);
                  frc.getChdepart().setVisible(true);
                  //frc.getSignaler().setVisible(false);
                  Image o = new Image("/Images/Tick.png");
                  Notification.notificationValidation(event, o,x);
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
              if (this.role.getText().equals("membre") || this.role.getText().equals("bonplaneur")){
                  frc.getModifier().setVisible(false);
                 // frc.getEco1().setVisible(false);
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
                  frc.getAjoutCov().setVisible(false);
         }
              else if (this.role.getText().equals("admin")){
                  
                  frc.getParticiper().setVisible(false);
                  frc.getChercher().setVisible(true);
                  frc.getChdepart().setVisible(true);
                 frc.getSupprimer().setVisible(true);
                 frc.setEmail(email.getText());
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

    public TextField getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email.setText(email);
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
}
