/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Covoiturage;
import Entities.Participation;
import Entities.Train;
import Entities.Transport;
import Entities.User;
import Services.ServiceParticipation;
import Services.ServiceTransport;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author esprit
 */
public class AffichageTrainController implements Initializable {

    @FXML
    private TableView<Train> tableView;
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
    private TableColumn<?, ?> eco;
    @FXML
    private TableColumn<?, ?> prix;
    @FXML
    private TextField VilleDepart1;
    @FXML
    private TextField HeureArrivee;
    @FXML
    private TextField HeureDepart;
    @FXML
    private TextField NbrPlaceDispo;
    @FXML
    private TextField VilleArrivee1;
    @FXML
    private TextField idTransport;
    @FXML
    private TextField luxe;
    private TextField eco1;
    
        public ArrayList<Train> ran;
    @FXML
    private Button retour;
    @FXML
    private Button modifier;
    @FXML
    private Button supprimer;
    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private Button logout;
    @FXML
    private Button participer;
    @FXML
    private TextField idTransport1;
    @FXML
    private TextField email;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TextField dateDepart1;
    @FXML
    private Label plein;
    @FXML
    private TextField chdepart;
    @FXML
    private Button chercher;
    @FXML
    private Button AjoutCov;
    @FXML
    private Label villedep;
    @FXML
    private Label datedep;
    @FXML
    private Label prixdep;
    @FXML
    private Label heurear;
    @FXML
    private Label heuredep;
    @FXML
    private Label nbpd;
    @FXML
    private Label villear;
    @FXML
    private Button annuler;
	@FXML
	private Label valide2;
	@FXML
	private Label valide3;
	@FXML
	private Label valide4;
	@FXML
	private Label valide5;
	@FXML
	private Label valide6;
	@FXML
	private Label valide1;


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherTransport();
    }    

    public void afficherTransport() {

        Task<ArrayList<Train>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Train>) new ServiceTransport().AfficherTrain();
                return ran;
            }
        };
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent e) {                                                                                                                                                                    
                // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
                VilleDepart.setCellValueFactory(new PropertyValueFactory<>("villeDepart"));
                VilleArrivee.setCellValueFactory(new PropertyValueFactory<>("villeArrive"));
                nbrPlaceDispo.setCellValueFactory(new PropertyValueFactory<>("nbrPlaceDispo"));
                heureDepart.setCellValueFactory(new PropertyValueFactory<>("heureDepart"));
                heureArrivee.setCellValueFactory(new PropertyValueFactory<>("heureArrive"));
                prix.setCellValueFactory(new PropertyValueFactory<>("classluxe"));
                eco.setCellValueFactory(new PropertyValueFactory<>("classEco"));
                tableView.setItems(FXCollections.observableArrayList(task.getValue()));
            }
        });
        task.setOnFailed(e -> {
            afficherTransport();
        });
        Thread th = new Thread(task);
        th.start();
        //retour.setVisible(false);
    }
   
    @FXML
    private void supprimer(ActionEvent event) throws SQLException 
    {
        String e = idTransport1.getText();
        int r = Integer.parseInt(e);
        ServiceParticipation s = new ServiceParticipation();
        s.SupprimerParticipation(r);
        ServiceTransport s1 = new ServiceTransport();
        s1.SupprimerTransport1(r);
	Image o = new Image("/Images/Tick.png");
	Notification.notificationDeSuppressionTransport(event, o);
        afficherTransport();
    }
    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
		 //boolean isnotAlphDesc = TextFieldValidation.textalphabet(VilleDepart, valide1, "Pas de numeros ");
		 //boolean isnotAlphDesc2 = TextFieldValidation.textalphabet(VilleArrivee, valide2, "Pas de numeros ");
		 boolean isnotAlphDesc3 = TextFieldValidation.texNum(NbrPlaceDispo, valide3, "numéros obligatoires ");
		 boolean isnotAlphDesc4 = TextFieldValidation.texNum(HeureDepart, valide4, "numéros obligatoires ");
		 boolean isnotAlphDesc5 = TextFieldValidation.texNum(HeureArrivee, valide5, "numéros obligatoires ");
		 //boolean isnotAlphDesc6 = TextFieldValidation.texNum(PrixPlace , valide6, "numéros obligatoires ");       
        Transport ran =  tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        ServiceTransport sr = new ServiceTransport();
        Date date = java.sql.Date.valueOf(DatePicker.getValue());
        String i= id_client.getText();
        int nbr = Integer.parseInt(i);
        String g = idTransport1.getText();
        int g1 = Integer.parseInt(g);
        ServiceParticipation s = new ServiceParticipation();
        Participation P = new Participation(g1,nbr,"train");
        s.AjouterParticipation(P);
        String a = VilleDepart1.getText();
        String b =VilleArrivee1.getText();
        String c = NbrPlaceDispo.getText();
        int c1 = Integer.parseInt(c);
        String d = HeureDepart.getText();
        int d1 = Integer.parseInt(d);
        String e = HeureArrivee.getText();
        int e1 = Integer.parseInt(e);
        String f = luxe.getText();
        float f1 =  Float.parseFloat(f);
        /*String h = eco1.getText();
        float h1 = Float.parseFloat(h);*/
        Date dt = java.sql.Date.valueOf(LocalDate.now());
        if(date.after(dt)){
        Transport v  = new Train(g1,a,b,c1,d1,e1,date,1,ran.getSignalement(),nbr,f1);
        /*(int id_transport, String villeDepart, 
            String villeArrive, int nbrPlaceDispo, int heureDepart,
            int heureArrive,Date dateDepart,int validation,int signalement,int id_admin,float prixPersonne) */
        sr.ModifierTransport(v, g1);
        Image o = new Image("/Images/Tick.png");
	Notification.notificationDeModif(event, o);
        System.out.println("hhhhhhhh");
        //afficherReservation();
        System.out.println(VilleDepart.getText());
        afficherTransport();}
        else {
            Image l = new Image("/Images/Cross.png") ;
            Notification.notificationErreur1(event ,l);
                }
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
    private void clickedd(MouseEvent event) throws SQLException {
        
        if(this.role.getText().equals("admin")){    
            System.out.println(this.role.getText());
            visibility();}
        ServiceTransport sr = new ServiceTransport();
        ServiceParticipation sp = new ServiceParticipation();
        Train ran =  tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
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
       /* String f = Float.toString(ran.getclassEco());
        eco1.setText(f); */
        String h = Float.toString(ran.getPrixPersonne());
        luxe.setText(h);
        String g = Integer.toString(ran.getId_transport());
        idTransport.setText(g);
        String g1 = Integer.toString(ran.getId_transport());
        System.out.println(g1);
        idTransport1.setText(g1);
        Date date1 = ran.getDateDepart();
        dateDepart1.setText(date1.toString());
        //participer.setVisible(true);
        List<Participation> listp = new ArrayList<>();
        List<User> list = new ArrayList<>();
        List<Covoiturage> list1 = new ArrayList<>();
        list =sr.AfficherUser(email.getText());
        int idPersonne=list.get(0).getIdPersonne();
        listp=sp.AfficherParticipation(idPersonne, ran.getId_transport());
        if (!listp.isEmpty()){participer.setDisable(true);annuler.setVisible(true);}
        else {participer.setDisable(false);annuler.setVisible(false);}
        if(this.role.getText().equals("membre") || this.role.getText().equals("bonplaneur")){
        if (ran.getNbrPlaceDispo()==0)
         {participer.setVisible(false);
          plein.setVisible(true);
         }
         else {participer.setVisible(true);
          plein.setVisible(false);}}
    }
    @FXML
     private void retour(ActionEvent event) throws SQLException, IOException 
    {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Choix.fxml"));
            Parent root =  loader.load();
            ChoixController frc = loader.getController();
            retour.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setIDClient(nbr);
            frc.setEmail(email.getText());
    }  
     

    public TextField getVilleDepart1() {
        return VilleDepart1;
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

    public TextField getVilleArrivee1() {
        return VilleArrivee1;
    }

    public TextField getIdTransport() {
        return idTransport;
    }

    public TextField getLuxe() {
        return luxe;
    }

    public TextField getEco1() {
        return eco1;
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
    @FXML
    private void participer(ActionEvent event) throws SQLException {
         Train ran =  tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
         ServiceTransport sr = new ServiceTransport();
         String i= id_client.getText();
         int nbr = Integer.parseInt(i);
         String g = idTransport1.getText();
         int g1 = Integer.parseInt(g);
         ServiceParticipation s = new ServiceParticipation();
         Participation P = new Participation(g1,nbr,"train");
         s.AjouterParticipation(P);    
         String a = VilleDepart1.getText();
         String b =VilleArrivee1.getText();
         String receveur=email.getText();
         String c = NbrPlaceDispo.getText();
         int c1 = Integer.parseInt(c);
         c1--;
         String d = HeureDepart.getText();
         int d1 = Integer.parseInt(d);
        String e = HeureArrivee.getText();
        int e1 = Integer.parseInt(e);
       String f = luxe.getText();
        float f1 =  Float.parseFloat(f);
        /*String h = eco1.getText();
        float h1 = Float.parseFloat(h);*/
        Date date = ran.getDateDepart();
        
        Transport v  = new Train(g1,a,b,c1,d1,e1,date,1,ran.getSignalement(),nbr,f1);
        sr.ModifierTransport(v, g1); 
        afficherTransport();
         Mailer.sendMail(receveur, "BonPlan ","le depart est prevu pour"+v.getHeureDepart());
    }

    public DatePicker getDatePicker() {
        return DatePicker;
    }

    public TextField getIdTransport1() {
        return idTransport1;
    }
    public TextField getEmail() {
        return email;
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
    public TextField getChdepart() {
        return chdepart;
    }
    public Button getChercher() {
        return chercher;
    }
    
    @FXML
    private void recherche(ActionEvent event) {
        Task<ArrayList<Train>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Train>) new ServiceTransport().ChercherTrain(chdepart.getText());
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
                prix.setCellValueFactory(new PropertyValueFactory<>("classluxe"));
                eco.setCellValueFactory(new PropertyValueFactory<>("classEco"));
                tableView.setItems(FXCollections.observableArrayList(task.getValue()));
            }
        });
        task.setOnFailed(e -> {
          //  afficherTransport();
        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void AjoutCov(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterTrain.fxml"));
            Parent root =  loader.load();
            AjouterTrainController frc = loader.getController();
            retour.getScene().setRoot(root);
            frc.setRole(role.getText());
            String i= id_client.getText();
            int nbr = Integer.parseInt(i);
            frc.setEmail(email.getText());
            frc.setIDClient(nbr);
    }

    public Button getAjoutCov() {
        return AjoutCov;
    }
     public void  visibility()
    {
        this.modifier.setVisible(true);
                  this.HeureArrivee.setVisible(true);
                  this.HeureDepart.setVisible(true);
                 // frc.getIdTransport().setVisible(false);
                  this.luxe.setVisible(true);
                  this.NbrPlaceDispo.setVisible(true);
                  this.supprimer.setVisible(true);
                  this.VilleArrivee1.setVisible(true);
                  this.VilleDepart1.setVisible(true);
                  //this.setEmail(email.getText());
                  //frc.getIdTransport().setVisible(false);
                  this.DatePicker.setVisible(true);
                  this.AjoutCov.setVisible(true);
                  this.dateDepart1.setVisible(true);
                  this.DatePicker.setVisible(true);
                  this.villedep.setVisible(true);
                  this.datedep.setVisible(true);
                  this.prixdep.setVisible(true);
                  this.heurear.setVisible(true);
                  this.heuredep.setVisible(true);
                  this.nbpd.setVisible(true);
                  this.villear.setVisible(true);
   
    }
    public void invisibility()
    {
                  this.modifier.setVisible(false);
                  this.HeureArrivee.setVisible(false);
                  this.HeureDepart.setVisible(false);
                 // frc.getIdTransport().setVisible(false);
                  this.prix.setVisible(false);
                  this.NbrPlaceDispo.setVisible(false);
                  this.supprimer.setVisible(false);
                  this.VilleArrivee1.setVisible(false);
                  this.VilleDepart1.setVisible(false);
                  //this.setEmail(email.getText());
                  //frc.getIdTransport().setVisible(false);
                  this.DatePicker.setVisible(false);
                  this.AjoutCov.setVisible(false);
                  this.dateDepart1.setVisible(false);
                  this.DatePicker.setVisible(false);
                  //complet.setVisible(false);
                  plein.setVisible(false);
                  participer.setVisible(false);
                  this.luxe.setVisible(false);
                  this.villedep.setVisible(false);
                  this.datedep.setVisible(false);
                  this.prixdep.setVisible(false);
                  this.heurear.setVisible(false);
                  this.heuredep.setVisible(false);
                  this.nbpd.setVisible(false);
                  this.villear.setVisible(false);
    }

    @FXML
    private void annuler(ActionEvent event) throws SQLException {
        Train ran =  tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
         ServiceTransport sr = new ServiceTransport();
         ServiceParticipation sp = new ServiceParticipation();
         String i= id_client.getText();
         int nbr = Integer.parseInt(i);
         String g = idTransport1.getText();
         int g1 = Integer.parseInt(g);
         String a = VilleDepart1.getText();
         String b =VilleArrivee1.getText();
         String receveur=email.getText();
         String c = NbrPlaceDispo.getText();
         int c1 = Integer.parseInt(c);
         c1++;
         String d = HeureDepart.getText();
         int d1 = Integer.parseInt(d);
        String e = HeureArrivee.getText();
        int e1 = Integer.parseInt(e);
       String f = luxe.getText();
        float f1 =  Float.parseFloat(f);
        /*String h = eco1.getText();
        float h1 = Float.parseFloat(h);*/
        Date date = ran.getDateDepart();
        
        Transport v  = new Train(g1,a,b,c1,d1,e1,date,1,ran.getSignalement(),nbr,f1);
        sr.ModifierTransport(v, g1); 
        afficherTransport();
        sp.SupprimerParticipation(ran.getId_transport());
    }
}
