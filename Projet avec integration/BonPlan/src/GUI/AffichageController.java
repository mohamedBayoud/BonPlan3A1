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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class AffichageController implements Initializable {

    File selectedfile;
    String path_img;
    Upload u = new Upload();
    public ArrayList<Promotion> ran;
    private Image image;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TableColumn<?, ?> IdPromotionC;
    @FXML
    private TableColumn<?, ?> titreC;
    @FXML
    private TableColumn<?, ?> descriptionC;
    @FXML
    private TableColumn<?, ?> lieuC;
    @FXML
    private TableColumn<?, ?> imageC;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private TableColumn<?, ?> prixC;
    @FXML
    private TableColumn<?, ?> nbplaceC;
    @FXML
    private TableColumn<?, ?> typeC;
    private ObservableList<Promotion> listeE;
    @FXML
    private TableView<Promotion> tableView;
    @FXML
    private ImageView img_view;
    @FXML
    private TextField id_text;
    @FXML
    private TextArea description_txt;
    @FXML
    private TextField lieu_txt;
    private TextField date_txt;
    @FXML
    private Button supprimePromotion;
    @FXML
    private TextField titre;
    @FXML
    private Button imagebrowse;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField type;
    @FXML
    private ListView<?> imagetelecharger;
    @FXML
    private TextField idpersonne;
    @FXML
    private Pane paneImage;
    @FXML
    private DatePicker dateaf;
    @FXML
    private Button modifierPromotion;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

         afficherPromotion();
    }

    public void afficherPromotion() {

        Task<ArrayList<Promotion>> task = new Task() {

            @Override
            protected Object call() {
                ran = (ArrayList<Promotion>) new ServicePromotion().AfficherPromotion();
                return ran;
            }
        };
        task.setOnSucceeded(e -> {

            // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
            IdPromotionC.setCellValueFactory(new PropertyValueFactory<>("IdPromotion"));
            titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC.setCellValueFactory(new PropertyValueFactory<>("date"));

            prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nbplaceC.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherPromotion();
        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void CLicked(MouseEvent event) {
        Promotion ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        // id_text.setText(ran.getType());
        String IdPromotion = Integer.toString(ev.getIdPromotion());
        String idpers = Integer.toString(ev.getIdPersonne());
        String nbp = Integer.toString(ev.getNbPlace());
        String prx = Float.toString(ev.getPrix());
        String date = ev.getDate().toString();
        description_txt.setText(ev.getDescription());
        lieu_txt.setText(ev.getLieu());
        idpersonne.setText(idpers);
        date_txt.setText(date);
        prix.setText(prx);
        nbplace.setText(nbp);
        titre.setText(ev.getTitre());
        type.setText(ev.getType());
        id_text.setText(IdPromotion);
        Image a1 = new Image("http://localhost/Image/" + ev.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/" + ev.getImage());

        img_view.setImage(a1);
    }

    @FXML
    private void SupprimerPromotion(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette Evenement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String e = id_text.getText();
            int r = Integer.parseInt(e);

            ServicePromotion s = new ServicePromotion();
            s.supprimerPromotion(r);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();

            afficherPromotion();
        }
    }

    @FXML
    private void ModifierPromotion(ActionEvent event) throws SQLException, IOException {
    ServicePromotion sv = new ServicePromotion();
        String i = nbplace.getText();
        int b = Integer.parseInt(i);
        String j = prix.getText();
        float d = Float.parseFloat(j);
        String e = id_text.getText();
        int r = Integer.parseInt(e);
        String t = idpersonne.getText();
        int y = Integer.parseInt(t);
        
       Date  d1 = java.sql.Date.valueOf(dateaf.getValue());
      
        
        Promotion e1;
       e1 = new Promotion(r,y,description_txt.getText(),b,titre.getText(),d,lieu_txt.getText(),type.getText(),d1);
        e1.setImage(selectedfile.getName());
       
       if (e1.getImage().equals(selectedfile.getName()))
         sv.ModifierPromotion(e1, r);  
      
    }

  
    @FXML
    private void returnnbut(ActionEvent event) throws IOException {
        Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
        Scene scene = new Scene(page1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
            System.out.println("sssssssssssssssss");
        } else {
            System.out.println("FICHIER erroné");
        }

    }

    public File getSelectedfile() {
        return selectedfile;
    }

    public Button getSupprimePromotion() {
        return supprimePromotion;
    }

    public Button getImagebrowse() {
        return imagebrowse;
    }

    public Button getModifierPromotion() {
        return modifierPromotion;
    }

    
    @FXML
    private void DatePicker(ActionEvent event) {
    }

}
