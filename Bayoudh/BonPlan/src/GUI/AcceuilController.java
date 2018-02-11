/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;


import java.sql.*;
import javafx.scene.*;
import Entites.Evenement;
import Services.ServiceEvent;
import Services.Upload;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class AcceuilController implements Initializable {

    Upload u = new Upload();
    @FXML
    private TextField idpersonne;
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML
    private TextField idEvent;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField type;
    @FXML
    private Button updateEvent;
    @FXML
    private Button imagebrowse;
    public static File file;
    @FXML
    private ListView imagetelecharger;
    @FXML
    private Button ajoutEvent;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void AjoutEvent(ActionEvent event) throws IOException, SQLException {
        ServiceEvent sv = new ServiceEvent();

        u.upload(selectedfile);
        String i = nbplace.getText();
        int b = Integer.parseInt(i);
        String j = prix.getText();
        float d = Float.parseFloat(j);
        String e = idEvent.getText();
        int r = Integer.parseInt(e);
        String t = idpersonne.getText();
        int y = Integer.parseInt(t);
        
       Date  d1 = java.sql.Date.valueOf(date.getValue());
       Date  d3 = java.sql.Date.valueOf(date_fin.getValue());
       Date  d2 = java.sql.Date.valueOf(date_debut.getValue());
        
        Evenement e1;
        //e1 = new Evenement(y, r, t, b, t, d, e, t);
       e1 = new Evenement(r,y,description.getText(),b,titre.getText(),d,lieu.getText(),type.getText(),d1,d2,d3);
        e1.setImage(selectedfile.getName());
        sv.AjouterEvent(e1);
      
        
    }

    @FXML
    private void updateEvent(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
            Parent root = loader.load();

            AffichageController frc = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    File selectedfile;
    String path_img;

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
}
