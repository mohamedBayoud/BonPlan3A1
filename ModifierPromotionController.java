/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Promotion;
import Services.ServicePromotion;
import Services.Upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class ModifierPromotionController implements Initializable {

    Upload u = new Upload();
   
    @FXML
    private DatePicker date;
   /* @FXML
    private DatePicker date_debut;
    @FXML
    private DatePicker date_fin;
    @FXML*/
   
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField lieu;
    @FXML
    private Button imagebrowse;
    @FXML
    private TextField prix;
    
    
    @FXML
    private Button updatePromotion;
    @FXML
    private ListView<?> imagetelecharger;
    File selectedfile;
    String path_img;
    @FXML
    private Pane paneImage;
    @FXML
    private ImageView ImageView;
    @FXML
    private TextField idpr;

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
    private void browse(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println(selectedfile.getName());
            FileInputStream inp = new FileInputStream(selectedfile.getPath());
            System.out.println(selectedfile.getName());
            ImageView a = new ImageView(new Image(inp));
            a.setFitHeight(150);
            a.setFitWidth(100);

            paneImage.getChildren().add(a);

            path_img = selectedfile.getAbsolutePath();

            if (selectedfile.isFile()) {
                u.upload(selectedfile);
            }
        } else {
            System.out.println("FICHIER erroné");
        }

    }

    @FXML
    private void updatePromotion(ActionEvent event) throws SQLException, IOException {
       ServicePromotion sv = new ServicePromotion();

        String j = prix.getText();
        float d = Float.parseFloat(j);
        
        String id=idpr.getText();
                int r = Integer.parseInt(id);

       Date  d1 = java.sql.Date.valueOf(date.getValue());
        
        Promotion e1;
        //e1 = new Promotion(y, r, t, b, t, d, e, t);
       e1 = new Promotion(description.getText(),titre.getText(),d,lieu.getText(),d1);
        e1.setImage(selectedfile.getName());
       
       if (e1.getImage().equals(selectedfile.getName()))
         sv.ModifierPromotion(e1,r);  
       
        
          Parent page1 = FXMLLoader.load(getClass().getResource("Affichage1.fxml"));
                Scene scene = new Scene(page1);
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                stage.setScene(scene);
                stage.show();
      
    
    }

    @FXML
    private void returnn(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

}
