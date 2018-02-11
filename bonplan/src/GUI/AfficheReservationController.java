/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reservation;
import Services.ServiceReservation;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class AfficheReservationController implements Initializable {

    public ArrayList<Reservation> ran;
    @FXML
    private TableView<Reservation> tableView;

    @FXML
    private TableColumn<?, ?> dateEntree;
    @FXML
    private TableColumn<?, ?> dateSortie;
    @FXML
    private TableColumn<?, ?> nbrPersonnes;
    @FXML
    private TableColumn<?, ?> type;
    @FXML
    private TextField idReservation;
    @FXML
    private Button supprimer;
    @FXML
    private TableColumn<?, ?> nom;
    @FXML
    private Button modifier;
    @FXML
    private TextField dateE;
    @FXML
    private TextField dateS;
    @FXML
    private TextField nbrP;
    private TextField t;
    @FXML
    private DatePicker dateEn;
    @FXML
    private DatePicker dateSo;
    @FXML
    private TextField typee;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherReservation();
    }

    public void afficherReservation() {

        Task<ArrayList<Reservation>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
                ran = (ArrayList<Reservation>) new ServiceReservation().AfficherReservation();
                return ran;
            }
        };
        task.setOnSucceeded((WorkerStateEvent e) -> {

            // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
            dateEntree.setCellValueFactory(new PropertyValueFactory<>("dateEntree"));
            dateSortie.setCellValueFactory(new PropertyValueFactory<>("dateEntree"));
            nbrPersonnes.setCellValueFactory(new PropertyValueFactory<>("nbrPersonnes"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
            //  nom.setCellValueFactory(new PropertyValueFactory<>("nom"));

            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherReservation();
        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void clicked(MouseEvent event) {
        Reservation ran = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

        String a = Integer.toString(ran.getIdReservation());
        idReservation.setText(a);
        String b = Integer.toString(ran.getIdReservation());
        nbrP.setText(b);
        typee.setText(ran.getType());

        String c = ran.getDateEntree().toString();
        dateE.setText(c);
        String d = ran.getDateEntree().toString();
        dateS.setText(d);

    }

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {

        String e = idReservation.getText();
        int r = Integer.parseInt(e);
        ServiceReservation s = new ServiceReservation();
        s.SupprimerReservation(r);
        afficherReservation();

    }

    @FXML
    private void Modifier(ActionEvent event) throws SQLException {
        ServiceReservation sr = new ServiceReservation();

        String e = idReservation.getText();
        int r = Integer.parseInt(e);
        String i = nbrP.getText();
        int b = Integer.parseInt(i);
        Date d1 = java.sql.Date.valueOf(dateEn.getValue());
        Date d3 = java.sql.Date.valueOf(dateSo.getValue());
        Reservation p = new Reservation(typee.getText(), d1, d3, b);
        //  System.out.println("hhhhhhhh");
        sr.ModifierReservation(r, p);
        System.out.println("hhhhhhhh");
        //afficherReservation();
    }

}
