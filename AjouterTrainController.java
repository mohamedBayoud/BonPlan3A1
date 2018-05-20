/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Covoiturage;
import Entities.Train;
import Entities.Transport;
import Services.ServiceTransport;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AjouterTrainController implements Initializable {

    @FXML
    private Button BUTTON;
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
    private SplitMenuButton Train;
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
    @FXML
    private TextField email;
	@FXML
	private Label valide2;
	@FXML
	private Label valide3;
	@FXML
	private Label valide4;
	@FXML
	private Label valide5;
	@FXML
	private Label valide1;
	@FXML
	private Label valide6;

   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    

    @FXML
    private void DatePicker(ActionEvent event) {
    }

    @FXML
    private void ajouterTransport(ActionEvent event) throws IOException, SQLException {
         boolean isnotAlphDesc = TextFieldValidation.textalphabet(VilleDepart, valide1, "Pas de numeros ");
		 boolean isnotAlphDesc2 = TextFieldValidation.textalphabet(VilleArrivee, valide2, "Pas de numeros ");
		 boolean isnotAlphDesc3 = TextFieldValidation.texNum(NbrPlaceDispo, valide3, "numéros obligatoires ");
		 boolean isnotAlphDesc4 = TextFieldValidation.texNum(HeureDepart, valide4, "numéros obligatoires ");
		 boolean isnotAlphDesc5 = TextFieldValidation.texNum(HeureArrivee, valide5, "numéros obligatoires ");
		 boolean isnotAlphDesc6 = TextFieldValidation.texNum(PrixPlace, valide6, "numéros obligatoires ");


  
		
		if ((NbrPlaceDispo.getText().isEmpty())&&(HeureDepart.getText().isEmpty())&&(HeureArrivee.getText().isEmpty())&&(PrixPlace.getText().isEmpty())&&(VilleDepart.getText().isEmpty())&&
                (VilleArrivee.getText().isEmpty()) )
		{
			Image o = new Image("/Images/Cross.png");
			Notification.notificationVide(event, o);
		}
		else if ((NbrPlaceDispo.getText().isEmpty())||(HeureDepart.getText().isEmpty())||(HeureArrivee.getText().isEmpty())||(PrixPlace.getText().isEmpty())||(VilleDepart.getText().isEmpty())||
                        (VilleArrivee.getText().isEmpty()) )
		{
			Image o = new Image("/Images/Cross.png");
			Notification.notificationErreur(event, o);
		}
		else {
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
        Date dt = java.sql.Date.valueOf(LocalDate.now());
        if(d.after(dt)){
        Transport v=new Train(idc+nbrPl*Hdep+1,VilleDepart.getText(), VilleArrivee.getText(),nbrPl,Hdep,Harr,d,1,0,idc,PPlace);
        ServiceTransport sv = new ServiceTransport();
        sv.AjouterTransport(v); 
        Image o = new Image("/Images/Tick.png");    
        Notification.notificationDajout(event ,o);
        }
        else {
            Image l = new Image("/Images/Cross.png") ;
            Notification.notificationErreur1(event ,l);
                }
		}
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AffichageTrain.fxml"));
            Parent root =  loader.load();
            AffichageTrainController frc = loader.getController();
            retour.getScene().setRoot(root);
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
                  //frc.setEmail(email.getText());
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

    @FXML
    private void logout(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("login.fxml"));
            Parent root =  loader.load();
            LoginController frc = loader.getController();
            logout.getScene().setRoot(root);
    }

    public TextField getRole() {
        return role;
    }

    public TextField getId_client() {
        return id_client;
    }

    public void setRole(String role) {
        this.role.setText(role) ;
    }
    public void setIDClient(int id) {
        String s="";
        s=s+id;
        this.id_client.setText(s);
    }
     public void setEmail(String email) {
        this.email.setText(email) ;
    }
}
