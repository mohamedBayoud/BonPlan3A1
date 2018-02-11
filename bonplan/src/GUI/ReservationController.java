/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Reservation;
import Services.ServiceReservation;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JTextField;


/**
 * FXML Controller class
 *
 * @author ASUS
 */
public class ReservationController implements Initializable {

    @FXML
    private DatePicker dateEntree;
    @FXML
    private DatePicker DateSortie;
    @FXML
    private Button Reserver;
    @FXML
    private TextField NbrPlaces;
    @FXML
    private TextField Type;

    /**
     * Initializes the controller class.
     */
    @FXML
    private void pageModifier(ActionEvent event) throws IOException {
        Parent pm =FXMLLoader.load(getClass().getResource("AfficheReservation.fxml"));
        Scene sm = new Scene(pm, 700, 400);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();
//    
    
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterReservation(ActionEvent event) throws SQLException, IOException {
   
     String i = NbrPlaces.getText();
        int b = Integer.parseInt(i);

          
       Date  d1 = java.sql.Date.valueOf(dateEntree.getValue());
      Date  d3 = java.sql.Date.valueOf(DateSortie.getValue());


       
    Reservation r =new Reservation(Type.getText(),d1,d3,b);
    ServiceReservation sr = new ServiceReservation();
    sr.AjouterReservation(r);
    
    Parent pm =FXMLLoader.load(getClass().getResource("AfficheReservation.fxml"));
        Scene sm = new Scene(pm, 700, 400);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();
    
     }
   
    
}
