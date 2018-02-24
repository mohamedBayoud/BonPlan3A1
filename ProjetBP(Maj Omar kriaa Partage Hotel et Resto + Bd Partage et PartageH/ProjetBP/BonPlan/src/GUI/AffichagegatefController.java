/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Covoiturage;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import Entities.Transport;
import Services.ServiceTransport;
import java.io.IOException;
import java.sql.Date;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import Entities.Covoiturage;
import Entities.Participation;
import Services.ServiceParticipation;
import java.time.LocalDate;
import java.time.ZoneId;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AffichagegatefController implements Initializable {
    @FXML
    private TableView<Covoiturage> tableView;
    @FXML
    private TableColumn<?, ?> VilleDepart;
    @FXML
    private TableColumn<?, ?> VilleArrivee;
    @FXML
    private TableColumn<?, ?> nbrPlaceDispo;
    @FXML
    private TableColumn<?, ?> heureDepart;
    @FXML
    private TableColumn<?, ?> heureArrivee;
    @FXML
    private TableColumn<?, ?> prix;
  
    @FXML
    private TextField Prix;
    @FXML
    private TextField HeureArrivee;
    @FXML
    private TextField HeureDepart;
    @FXML
    private TextField NbrPlaceDispo;
    
    
    public ArrayList<Covoiturage> ran;
    @FXML
    private TextField VilleDepart1;
    @FXML
    private TextField VilleArrivee1;
    @FXML
    private Button retour;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField role;
    @FXML
    private Button AjoutCov;
    @FXML
    private TextField id_client;
    @FXML
    private Button logout;
    @FXML
    private Button participer;
    private TextField idTransport;
    @FXML
    private TextField idTransport1;
    @FXML
    private TextField email;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TextField dateDepart1;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       afficherTransport();
        // TODO
    }
    public void afficherTransport() {

        Task<ArrayList<Covoiturage>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Covoiturage>) new ServiceTransport().AfficherCovoiturage();
                return ran;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent e) {                                                                                                                                                                    
                VilleDepart.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
                VilleArrivee.setCellValueFactory(new PropertyValueFactory<>("villeArrive"));
                nbrPlaceDispo.setCellValueFactory(new PropertyValueFactory<>("nbrPlaceDispo"));
                heureDepart.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
                heureArrivee.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
                prix.setCellValueFactory(new PropertyValueFactory<>("prixPersonne"));
                tableView.setItems(FXCollections.observableArrayList(task.getValue()));
            }
        });
        task.setOnFailed(e -> {
            afficherTransport();
        });
        Thread th = new Thread(task);
        th.start();
    }
   
    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        String e = idTransport1.getText();
        int r = Integer.parseInt(e);
        ServiceParticipation s = new ServiceParticipation();
        s.SupprimerParticipation(r);
        ServiceTransport s1 = new ServiceTransport();
        s1.SupprimerTransport1(r);
        afficherTransport();
    }
    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        Date date = java.sql.Date.valueOf(DatePicker.getValue());
        ServiceTransport sr = new ServiceTransport();
        String a = VilleDepart1.getText();
        String b =VilleArrivee1.getText();      
        String c = NbrPlaceDispo.getText();
        int c1 = Integer.parseInt(c);
        String d = HeureDepart.getText();
        int d1 = Integer.parseInt(c);
        String e = HeureArrivee.getText();
        int e1 = Integer.parseInt(c);
        String f = Prix.getText();
        float f1 =  Float.parseFloat(f);
        String g = idTransport1.getText();
        int g1 = Integer.parseInt(g);
        Transport v  = new Covoiturage(g1,a,b,c1,d1,e1,date,0,f1);
        sr.ModifierTransport(v, g1);
        System.out.println("hhhhhhhh");
        System.out.println(VilleDepart.getText());
        afficherTransport();
    }
    private void ajout(ActionEvent event) throws SQLException, IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTransport.fxml"));
            Parent root =  loader.load();
            AjouterTransportController frc = loader.getController();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));  
            stage.show();
    }
    @FXML
    private void clickedd(MouseEvent event) {
           Transport ran =  tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

        String a = ran.getVilleDepart();
        VilleDepart1.setText(a);
        String b = ran.getVilleArrive();
        VilleArrivee1.setText(b);
        String c = Integer.toString(ran.getNbrPlaceDispo());
        NbrPlaceDispo.setText(c);
        String d = Integer.toString(ran.getHeureDepart());
        HeureDepart.setText(d);
        String e = Integer.toString(ran.getHeureArrive());
        HeureArrivee.setText(e);
        String f = Float.toString(ran.getprixPersonne());
        Prix.setText(f);
        String g = Integer.toString(ran.getId_transport());
        System.out.println(g);
        idTransport1.setText(g);
        Date date1 = ran.getDateDepart();
        //String da=Date
//       LocalDate date = date1.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
     //   DatePicker.setValue(date); 
         dateDepart1.setText(date1.toString());
    }
    public Button getAjoutCov() {
        return AjoutCov;
    }
    public String getEmail(){
        return email.getText();
    }
    @FXML
    private void retour(ActionEvent event) throws SQLException, IOException 
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Choix.fxml"));
            Parent root =  loader.load();
            ChoixgatefController frc = loader.getController();
            retour.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
            frc.setEmail(email.getText());
            
    } 
    @FXML
     private void AjoutCov(ActionEvent event) throws SQLException, IOException 
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTransport.fxml"));
            Parent root =  loader.load();
            AjouterTransportController frc = loader.getController();
            retour.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);

    }  
    public TextField getPrix() {
        return Prix;
    }

    public TextField getHeureArrivee() {
        return HeureArrivee;
    }

    public TextField getHeureDepart() {
        return HeureDepart;
    }

    public TextField getNbrPlaceDispo() {
        return NbrPlaceDispo;
    }

    public TextField getIdTransport() {
        return idTransport;
    }

    public TextField getVilleDepart1() {
        return VilleDepart1;
    }

    public TextField getVilleArrivee1() {
        return VilleArrivee1;
    }

    public Button getRetour() {
        return retour;
    }

    public Button getModifier() {
        return modifier;
    }

    public Button getSupprimer() {
        return supprimer;
    }
    public void setRole(String role) {
        
        this.role.setText(role);
     }   

    void setIDClient(int id) 
    {       
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
    private void participer(ActionEvent event) throws IOException, SQLException{
           String receveur=email.getText();
         String i= id_client.getText();
         int nbr = Integer.parseInt(i);
         String j= idTransport1.getText();
         int nbr1 = Integer.parseInt(j);
         ServiceParticipation s = new ServiceParticipation();
         Participation P = new Participation(nbr1,nbr,"covoiturage");
         s.AjouterParticipation(P);
         ServiceTransport sr = new ServiceTransport();
        String a = VilleDepart1.getText();
        String b =VilleArrivee1.getText();
        String c = NbrPlaceDispo.getText();
        int c1 = Integer.parseInt(c);
        c1--;
        String d = HeureDepart.getText();
        int d1 = Integer.parseInt(c);
        String e = HeureArrivee.getText();
        int e1 = Integer.parseInt(c);
        String f = Prix.getText();
        float f1 =  Float.parseFloat(f); 
        Date date = java.sql.Date.valueOf(DatePicker.getValue());
        Transport v  = new Covoiturage(nbr1,a,b,c1,d1,e1,date,0,f1);
        sr.ModifierTransport(v, nbr1);
        afficherTransport();
         Mailer.sendMail(receveur, "BonPlan ","le depart est prevu pour"+v.getHeureDepart());
    }

    public DatePicker getDatePicker() {
        return DatePicker;
    }
    public void setEmail(String email) {
        this.email.setText(email);
    }
    public Button getParticiper() {
        return participer;
    }
    @FXML
    private void DatePicker(ActionEvent event) {
    }
    
}
