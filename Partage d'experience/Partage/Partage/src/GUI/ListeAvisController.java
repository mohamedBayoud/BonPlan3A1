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
import java.util.ArrayList;
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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class ListeAvisController implements Initializable {
    
    public ArrayList<Partage> ran;

    @FXML
    private TableView<Partage> personsTable;
    @FXML
   
    private ObservableList<Partage> data;

    @FXML
    private Button Supprimer;
    @FXML
    private Button Modifier;
    @FXML
    private TextField SuppField;
    @FXML
    private TableColumn<Partage,String> Commentaire;
    
    @FXML
    private TabPane TabPaneModifier;
    @FXML
    private TextField ModiferId;
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

    /**
     * Initializes the controller class.
     */
    public void afficherCommentaire() {

        Task<ArrayList<Partage>> task = new Task() {

            @Override
            protected Object call() throws SQLException {
             ran = (ArrayList<Partage>) new ServicePartage().AfficherCommentaire();
             return ran;
            }
        };
        task.setOnSucceeded(e -> {

            Commentaire.setCellValueFactory(new PropertyValueFactory<>("commentaireAvis"));
            personsTable.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherCommentaire();
        });
        Thread th = new Thread(task);
        th.start();
    } 
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afficherCommentaire(); // Commenataire.setCellValueFactory();
    }

    @FXML
    private void ButtonSupprimer(ActionEvent event) throws IOException, SQLException {
        ServicePartage sv = new ServicePartage();
        String Id = SuppField.getText();
        int IdPartage = Integer.parseInt(Id);
        Partage P = new Partage(IdPartage);
        sv.supprimerAvis(P);
        afficherCommentaire();

    }

    @FXML
    private void Modifier(ActionEvent event) throws IOException, SQLException {
        {
            
          
          
            ServicePartage sv = new ServicePartage();

          //  String Id = ModiferId.getText();
            //int IdPartage = Integer.parseInt(Id);
            
           Partage P=new Partage(AvisText.getText(), NoteCuisineSlider.getMinorTickCount(),NoteRapportSlider.getMinorTickCount(),NoteServiceSlider.getMinorTickCount(),NoteAmbianceSlider.getMinorTickCount(),CommentaireField.getText());
            System.out.println("hhhhhhhh");
            sv.ModifierAvis(P);
            afficherCommentaire();

            
        }
    }
}
