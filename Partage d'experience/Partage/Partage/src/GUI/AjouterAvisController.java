/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Partage;
import Services.ServicePartage;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class AjouterAvisController implements Initializable {

    @FXML
    private TextField AvisText;
    @FXML
    private Slider NoteCuisineSlider;
     @FXML
    private Slider NoteRapportSlider;
      @FXML
    private Slider NoteServiceSlider;
       @FXML
    private Slider NoteAmbianceSlider;
    @FXML
    private TextField CommentaireField;
    @FXML
    private Button ButtonAjouter;
    @FXML
    private Button ButtonListe;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void ButtonAjouter(ActionEvent event) throws SQLException, IOException {

        ServicePartage sv = new ServicePartage();
        //Ajout d'un entier
        /*String i = NoteSlider.getT;
            int Note = Integer.parseInt(i);*/

        Partage v = new Partage(AvisText.getText(), NoteCuisineSlider.getMinorTickCount(),NoteRapportSlider.getMinorTickCount(),NoteServiceSlider.getMinorTickCount(),NoteAmbianceSlider.getMinorTickCount() ,CommentaireField.getText());

        sv.AjouterAvis(v);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Succes.fxml"));
        Parent root = loader.load();
        SuccesController frc = loader.getController();
        Stage stage = new Stage();
        stage.setScene(new Scene(root, 43, 100));
        stage.show();

    }
    @FXML
        private void ButtonListe(ActionEvent event) throws IOException {
            {
                 Parent ListeAvis=FXMLLoader.load(getClass().getResource("ListeAvis.fxml"));
                 Scene Page_ListeAvis = new Scene(ListeAvis);
         
               Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
               stage.setScene(Page_ListeAvis);
               stage.show();

            }
   
}
}