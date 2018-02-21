/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Entities.participerevent;
import Services.ServiceEvent;
import Services.ServiceParticiperEvent;
import Services.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class EventMembreController implements Initializable {

    File selectedfile;
    String path_img;
    Upload u = new Upload();
    public ArrayList<Evenement> ran;
    private Image image;
    private ObservableList<Evenement> listeE;
    @FXML
    private TableView<Evenement> tableView;
    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private TextField email_client;
    @FXML
    private Button retour;
    @FXML
    private Button sedeconnecter;
    @FXML
    private DatePicker DatePicker;

    @FXML
    private TableColumn<?, ?> IdEventC;
    @FXML
    private TableColumn<?, ?> titreC;
    @FXML
    private TableColumn<?, ?> descriptionC;
    @FXML
    private TableColumn<?, ?> lieuC;
    @FXML
    private TableColumn<?, ?> imageC;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private TableColumn<?, ?> prixC;
    @FXML
    private TableColumn<?, ?> nbplaceC;
    @FXML
    private TextField idEvent;
    @FXML
    private ImageView img_view;
    @FXML
    private TextField titre;
    @FXML
    private TextField lieu_txt;
    @FXML
    private TextField date_txt;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbplace;
    @FXML
    private TextArea description_txt;
    @FXML
    private Button Participer;
    @FXML
    private TextField type;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        DatePicker.setDayCellFactory((DatePicker dp) -> {
            return new DateCell() {
                @Override

                public void updateItem(LocalDate item, boolean empty) {
                    LocalDate d1 = null;
                    Date dte = java.sql.Date.valueOf(item);
                    Evenement ev = new Evenement();
                    List<Date> dates = new ArrayList<>();
                    ServiceEvent sv = new ServiceEvent();
                    super.updateItem(item, empty);
                    try {

                        dates = sv.AfficherDate();
                        for (Date d : dates) {

                            if (dte.equals(d)) {

                                setTooltip(new Tooltip("Demain !"));
                                setStyle("-fx-background-color: cyan;");

                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
        });
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuEvent.fxml"));
        Parent root = loader.load();
        MenuEventController frc = loader.getController();
        retour.getScene().setRoot(root);
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
    }
  public void afficherEvenement(Date d) {

        Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
               ran = (ArrayList<Evenement>) new ServiceEvent().chercher(d);
                return ran;
            }
        };
        task.setOnSucceeded(e -> {

            IdEventC.setCellValueFactory(new PropertyValueFactory<>("IdEvent"));
            titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC.setCellValueFactory(new PropertyValueFactory<>("date"));
            prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nbplaceC.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));

            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherEvenement(d);
        });
        Thread th = new Thread(task);
        th.start();
    }
    @FXML
    private void DatePicker(ActionEvent event) throws SQLException {
        List<Evenement> le = new ArrayList<>();
        ServiceEvent sv = new ServiceEvent();
        Date d = java.sql.Date.valueOf(DatePicker.getValue());
       afficherEvenement(d);
    
    }

    @FXML
    private void CLicked(MouseEvent event) {
        Evenement ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        //id_text.setText(ran.getType());
        String IdEvent = Integer.toString(ev.getIdEvent());
        String idpers = Integer.toString(ev.getIdPersonne());
        String nbp = Integer.toString(ev.getNbPlace());
        String prx = Float.toString(ev.getPrix());
        String date = ev.getDate().toString();
        description_txt.setText(ev.getDescription());
        lieu_txt.setText(ev.getLieu());
        id_client.setText(idpers);
        date_txt.setText(date);
        prix.setText(prx);
        nbplace.setText(nbp);
        titre.setText(ev.getTitre());
       type.setText(ev.getType());
        idEvent.setText(IdEvent);
        Image a1 = new Image("http://localhost/Image/" + ev.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/" + ev.getImage());

        img_view.setImage(a1);
    }

    @FXML
    private void sedeconnecter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController frc = loader.getController();
        sedeconnecter.getScene().setRoot(root);
    }
    @FXML
    private void Participer(ActionEvent event) throws SQLException {
        List<Evenement> le = new ArrayList<>();
        ServiceEvent sv = new ServiceEvent();
        String a = email_client.getText().toString();
        String From = a;
        String i = nbplace.getText();
        String e = idEvent.getText();
        int r = Integer.parseInt(e);
        String t = id_client.getText();
        int y = Integer.parseInt(t);
        String m = lieu_txt.getText();
        String j = prix.getText();
        float f = Float.parseFloat(j);
        
        Date d1 = java.sql.Date.valueOf(DatePicker.getValue());
        ServiceParticiperEvent sp =new  ServiceParticiperEvent();
        participerevent p =new participerevent(r,y);
        sp.AjouterParticipation(p);
        int c1 = Integer.parseInt(i);
        c1--;
        System.out.println(c1);
        Evenement e1 = new Evenement(y, description_txt.getText(), c1, titre.getText(),f , lieu_txt.getText(),type.getText(),d1,1);
       /*  e1.setImage(selectedfile.getName());
        if (e1.getImage().equals(selectedfile.getName())) {*/
            sv.ModifierEvent(e1, r);
           
       //            afficherEvenement();  }
        
        le = sv.chercherlieu(m);
        String s = "";
        for (int k = 0; k < le.size(); k++) {
            s = s + le.get(k).getType();
        }
         Mailer.sendMail(From, "Vous avez participé à cet événement", s);

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
