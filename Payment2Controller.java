/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Payment;
import Entities.Promotion;
import Entities.Reservation;
import Entities.bonplan;
import Entities.details;
import Services.ServiceReservation;
import Services.servicePayment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class Payment2Controller implements Initializable {
    public ArrayList<Payment> ran;
    public ArrayList<Reservation> l;

    @FXML
    private TableView<Payment> table;
    @FXML
    private TableColumn<?, ?> montant;
    
  
    @FXML
    private Button payer;
    @FXML
    private TableView<Reservation> t2;
    @FXML
    private TableColumn<?, ?> nbrC;
    @FXML
    private TableColumn<?, ?> nbrN;
    @FXML
    private TextField c;
    @FXML
    private TextField n;
    @FXML
    private TextField m;
    @FXML
    private TextField pa;
	@FXML
	private Button deco;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherMontant();
        afficherR();
        
       
        
    }    
    public void afficherMontant() {

        Task<ArrayList<Payment>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Payment>) new servicePayment().afficherR();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {

            // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
            
            montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
          
              

            table.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherMontant();
        });
        Thread th = new Thread(task);
        th.start();
    }
      public void afficherR() {

        Task<ArrayList<Reservation>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                l=(ArrayList<Reservation>) new servicePayment().afficher();
                return l;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {

            nbrN.setCellValueFactory(new PropertyValueFactory<>("nbrNuit"));
            nbrC.setCellValueFactory(new PropertyValueFactory<>("nbrChambre"));
          
            t2.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherR();
        });
        Thread th = new Thread(task);
        th.start();
    }
     

    @FXML
    private void payer(ActionEvent event) throws SQLException {
          float x=0;
         Reservation r = t2.getItems().get(t2.getSelectionModel().getSelectedIndex());
         Payment p = table.getItems().get(table.getSelectionModel().getSelectedIndex());

          String b = Integer.toString(r.getNbrNuit());
        String a = Integer.toString(r.getNbrChambre());
         String z = Integer.toString((int) p.getMontant());
       n.setText(b);
       c.setText(a);
       m.setText(z);
       
       String nbrn = n.getText();
        int s = Integer.parseInt(nbrn);
        String nbrc = c.getText();
        int y = Integer.parseInt(nbrc);
        String l= m.getText();
        int v = Integer.parseInt(l);
        
        x=x+(s*y*300);
        System.out.println(x);
        
        String n=Integer.toString((int) x);
        pa.setText(n+"DT");
        
        if(x<v){
            servicePayment sp = new servicePayment();
                    Payment e = new Payment(v-x);

         sp.ModifierMontant(e);
         afficherMontant();
        }
        else{
            System.out.println("pas bon");
           
        
        Notifications.create().title("notification").text("votre solde est insufisant").showWarning();
        }
        
        
        
        
        
        
     
        
    }

	@FXML
	private void deco(ActionEvent event) throws IOException {
		Parent page = FXMLLoader.load(getClass().getResource("payment.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
	}
    
}
