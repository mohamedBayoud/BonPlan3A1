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

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void AjouterReservation(ActionEvent event) throws SQLException {
   
     //  String a = NbrPlaces.getText();
     String i = NbrPlaces.getText();
        int b = Integer.parseInt(i);
//        String j = prix.getText();
//        float d = Float.parseFloat(j);
//        String e = idEvent.getText();
//        int r = Integer.parseInt(e);
//        String t = idpersonne.getText();
//        int y = Integer.parseInt(t);
//           
       Date  d1 = java.sql.Date.valueOf(dateEntree.getValue());
      Date  d3 = java.sql.Date.valueOf(DateSortie.getValue());
//    e1 = new Evenement(r,y,description.getText(),b,titre.getText(),d,lieu.getText(),type.getText(),d1,d2,d3);


       
    Reservation r =new Reservation(Type.getText(),d1,d3,b);
    ServiceReservation sr = new ServiceReservation();
    sr.AjouterReservation(r);
    
     }
}
