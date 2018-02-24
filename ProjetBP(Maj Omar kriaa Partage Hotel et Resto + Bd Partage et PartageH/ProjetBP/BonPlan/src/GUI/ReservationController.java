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
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import javafx.scene.control.ButtonType;
import java.util.Optional;

import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JTextField;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.scene.control.ComboBox;
//import java.util.*;
//import javax.mail.*;
//import javax.mail.internet.*;
//import javax.mail.internet.MimeMessage;
//import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import javax.swing.JOptionPane;
 


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
    private TextField Type;
    @FXML
    private Button modifier;
    @FXML
    private TextField nbrC;
    @FXML
    private TextField NbrNuit;
    @FXML
    private ComboBox<String> typeRes;

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
        
        
        typeRes.getItems().removeAll(typeRes.getItems());
    typeRes.getItems().addAll("demi pension", "pension complette");
    typeRes.getSelectionModel().select("pension Complette");
   
    } 
    
              
      

    @FXML
    private void AjouterReservation(ActionEvent event) throws SQLException, IOException {
   
    
   
    ServiceReservation sr = new ServiceReservation();
    if((NbrNuit.getText().trim().isEmpty())||(nbrC.getText().trim().isEmpty())||(dateEntree.getValue()==null)||((DateSortie.getValue()==null))){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confiramtion Ajout");
        alert.setHeaderText("un des champs est vide ");
        Optional<ButtonType> result = alert.showAndWait();
    }
    
    else 
    { String i = NbrNuit.getText();
        int b = Integer.parseInt(i);
        String z = nbrC.getText();
        int y = Integer.parseInt(z);

          
       Date  d1 = java.sql.Date.valueOf(dateEntree.getValue());
      Date  d3 = java.sql.Date.valueOf(DateSortie.getValue());
      Date  d4= java.sql.Date.valueOf(LocalDate.now());
      


      
    Reservation r =new Reservation(typeRes.getValue(),d1,d3,b,y);
        if(d3.after(d1)){
    
    sr.AjouterReservation(r);
    
    
//     try {
//			// Construct data
//			String apiKey = "apikey=" + "b0X8ItocUU0-3N1HjD3eHpzUQJmU6R4v7HzWrDa4EQ";
//			String message = "&message=" + "vous etes les bienvenus le"+d1;
//			String sender = "&sender=" + "sami";
//			String numbers = "&numbers=" + "(+216)28823951";
//			
//			// Send data
//			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.txtlocal.com/send/?").openConnection();
//			String data = apiKey + numbers + message + sender;
//			conn.setDoOutput(true);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
//			conn.getOutputStream().write(data.getBytes("UTF-8"));
//			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//			final StringBuffer stringBuffer = new StringBuffer();
//			String line;
//			while ((line = rd.readLine()) != null) {
//				//stringBuffer.append(line);
//                                JOptionPane.showMessageDialog(null, "message"+line);
//			}
//			rd.close();
//			
//			//return stringBuffer.toString();
//                        
//		} catch (Exception e) {
//			//System.out.println("Error SMS "+e);
//                        JOptionPane.showMessageDialog(null, e);
//			//return "Error "+e;
//		}
//     
    
    
   
 Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confiramtion Ajout");
        alert.setHeaderText("Vous etes les bienvenus le "+d1);
        Optional<ButtonType> result = alert.showAndWait();
    
    Parent pm =FXMLLoader.load(getClass().getResource("AfficherReservation.fxml"));
        Scene sm = new Scene(pm, 700, 400);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();
     }
     else 
     {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confiramtion Ajout");
        alert.setHeaderText("veillez verifier la date de sortie");
        Optional<ButtonType> result = alert.showAndWait();
        
        
        Parent pm =FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        Scene sm = new Scene(pm, 700, 400);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();
     }
      if(d1.before(d4))
     {
             Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
     alert.setTitle("Confiramtion Ajout");
        alert.setHeaderText("veillez saisir une date valable");
        Optional<ButtonType> result = alert.showAndWait();
        
        
        Parent pm =FXMLLoader.load(getClass().getResource("Reservation.fxml"));
        Scene sm = new Scene(pm, 700, 400);
        Stage s =(Stage)((Node) event.getSource()).getScene().getWindow();
        s.setScene(sm);
        s.show();
     }
      
   
           
      

     
    
    }
    }
}
   
    

