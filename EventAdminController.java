/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Services.ServiceEvent;
import Services.ServiceParticiperEvent;
import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class EventAdminController implements Initializable {
    @FXML
 private TableView<Evenement> tableView;
    private ObservableList<Evenement> listeE;
     public ArrayList<Evenement> ran;
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
    private TextField chercher;
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
    private TableColumn<?, ?> typeC;
    @FXML
    private Button valider;
    @FXML
    private Button supprimer;
    @FXML
    private TextField idEvent;

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
                    Entities.Evenement ev = new Entities.Evenement();
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
                        Logger.getLogger(Affichage1Controller.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
        });
       
    }    
 @FXML
    private void DatePicker(ActionEvent event) throws SQLException {
         List<Evenement> le = new ArrayList<>();
        ServiceEvent sv = new ServiceEvent();
        Date d = java.sql.Date.valueOf(DatePicker.getValue());
       afficherEvenement_nnValider(d);
        
    }

    @FXML
    private void CLicked(MouseEvent event) {
         Evenement ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
         String IdEvent = Integer.toString(ev.getIdEvent());
          idEvent.setText(IdEvent);
    }

    @FXML
    private void valider(ActionEvent event) throws SQLException {
         ServiceEvent sv = new ServiceEvent();
        String e = idEvent.getText();
        int r = Integer.parseInt(e);
        Evenement e1;
        e1 = new Evenement();
         sv.ActiverEvement(e1, r);
        afficherEvenementValider();
    }
    public void afficherEvenement_nnValider(Date d) {

        Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran =(ArrayList<Evenement>) new ServiceEvent().chercher1(d);
                return ran;
            }
        };
        task.setOnSucceeded(e -> {

            // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
            IdEventC.setCellValueFactory(new PropertyValueFactory<>("IdEvent"));
            titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC.setCellValueFactory(new PropertyValueFactory<>("date"));
            prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nbplaceC.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherEvenement_nnValider(d);
        });
        Thread th = new Thread(task);
        th.start();
    }
   public void afficherEvenementValider() {

        Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() {
                ran =(ArrayList<Evenement>) new ServiceEvent().AfficherEvenementNonActiver();
                return ran;
            }
        };
        task.setOnSucceeded(e -> {

            // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
            IdEventC.setCellValueFactory(new PropertyValueFactory<>("IdEvent"));
            titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC.setCellValueFactory(new PropertyValueFactory<>("date"));
            prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nbplaceC.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherEvenementValider();
        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
         Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette Evenement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String e = idEvent.getText();
            int r = Integer.parseInt(e);
            
            ServiceEvent s = new ServiceEvent();
            Services.ServiceParticiperEvent sp =new ServiceParticiperEvent();
            sp.SupprimerParticipation(r);
            s.supprimerEvent(r);
            
            afficherEvenementValider();
            
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();

           
        }
     /*    Evenement ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
         String IdEvent = Integer.toString(ev.getIdEvent());
          idEvent.setText(IdEvent);
          */
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
       frc.setRole(role.getText());
           if (!this.role.getText().equals("bonplaneur") ) {
            
            frc.getAjouterEvent().setVisible(false);
        }
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
