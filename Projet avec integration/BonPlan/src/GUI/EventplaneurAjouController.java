/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Evenement;
import Services.ServiceEvent;
import Services.Upload;
import java.io.File;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class EventplaneurAjouController implements Initializable {

    Upload u = new Upload();
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
    private DatePicker date;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private ListView imagetelecharger;
    @FXML
    private ComboBox<String> ComboType;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbplace;
    @FXML
    private Button imagebrowse;
    @FXML
    private Button ajoutEvent;
    File selectedfile;
    String path_img;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ComboType.getItems().addAll("rendonne", "soiree", "sortie");
        // TODO
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

    @FXML
    private void sedeconnecter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController frc = loader.getController();
        sedeconnecter.getScene().setRoot(root);
    }

    @FXML
    private void browse(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println("aaaaaaaaaa");
            imagetelecharger.getItems().add(selectedfile.getName());
            path_img = selectedfile.getAbsolutePath();
            System.out.println("sssssssssssssssss");
        } else {
            System.out.println("FICHIER erroné");
        }
    }

    @FXML
    private void AjoutEvent(ActionEvent event) throws SQLException, IOException {
        ServiceEvent sv = new ServiceEvent();

         if((lieu.getText().trim().isEmpty())||(description.getText().trim().isEmpty())||(nbplace.getText().trim().isEmpty())||(titre.getText().trim().isEmpty())||(prix.getText().trim().isEmpty())||(selectedfile.getName().trim().isEmpty())||(date.getValue()==null)||(ComboType.getValue()==null)){
           Image l = new Image("/Images/Cross.png");
             Notification.notificationVide(event, l);
     
          
        } else {
            u.upload(selectedfile);
            String i = nbplace.getText();
            int b = Integer.parseInt(i);
            String j = prix.getText();
            float f = Float.parseFloat(j);
            String t = id_client.getText();
            int y = Integer.parseInt(t);
            Date d1 = java.sql.Date.valueOf(date.getValue());
            Date dte = java.sql.Date.valueOf(LocalDate.now());
            String c = (String) ComboType.getValue();
            Evenement e;
            e = new Evenement(y, description.getText(), b, titre.getText(), f, lieu.getText(), c, d1, 0);

            e.setImage(selectedfile.getName());

            ServiceEvent s = new ServiceEvent();
            if (d1.after(dte)) {
                sv.AjouterEvent(e);
                Image l = new Image("/Images/Tick.png");
                Notification.notificationDeConfirmation(event, l);

            } else {
                Image l = new Image("/Images/Cross.png");
                Notification.notificationErreur(event, l);
            }
        }

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
