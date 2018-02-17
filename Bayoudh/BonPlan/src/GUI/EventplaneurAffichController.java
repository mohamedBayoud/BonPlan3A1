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
import javafx.scene.Parent;
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

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class EventplaneurAffichController implements Initializable {
 File selectedfile;
    String path_img;
    Upload u = new Upload();
    public ArrayList<Evenement> ran;
    private Image image;
    private ObservableList<Evenement> listeE;
    @FXML
    private TableView<Evenement> tableView;
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
    private DatePicker DatePicker;
    
    @FXML
    private TextField idEvent;
    @FXML
    private ImageView img_view;
    @FXML
    private TextField titre;
    @FXML
    private TextField lieu_txt;
    @FXML
    private TextField date_txt;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbplace;
    @FXML
    private TextArea description_txt;
    @FXML
    private ListView<?> imagetelecharger;
    @FXML
    private DatePicker dateaf;
    @FXML
    private Button imagebrowse;
    @FXML
    private Pane paneImage;
    @FXML
    private Button supprimer;
    @FXML
    private Button modifier;
    @FXML
    private TextField type2;
    @FXML
    private TableColumn<?, ?> IdEventC3;
    @FXML
    private TableColumn<?, ?> titreC3;
    @FXML
    private TableColumn<?, ?> descriptionC3;
    @FXML
    private TableColumn<?, ?> lieuC3;
    @FXML
    private TableColumn<?, ?> imageC3;
    @FXML
    private TableColumn<?, ?> dateC3;
    @FXML
    private TableColumn<?, ?> prixC3;
    @FXML
    private TableColumn<?, ?> nbplaceC3;
    @FXML
    private TableColumn<?, ?> typeC3;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
          DatePicker.setDayCellFactory((DatePicker dp) -> {
            return new DateCell() {
                @Override

                public void updateItem(LocalDate item, boolean empty) {
                    LocalDate d1 = null;
                    Date dte = java.sql.Date.valueOf(item);
                    Evenement ev = new Evenement();
                    List<Date> dates = new ArrayList<>();
                    ServiceEvent sv = new ServiceEvent();
                    super.updateItem(item, empty);
                    try {

                        dates = sv.AfficherDate();
                        for (Date d : dates) {

                            if (dte.equals(d)) {

                                setTooltip(new Tooltip("Demain !"));
                                setStyle("-fx-background-color: cyan;");

                            }
                        }
                    } catch (SQLException ex) {
                        Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            };
        });
    }  
    public void afficherEvenement() {

        Task<ArrayList<Evenement>> task = new Task() {

            @Override
            protected Object call() {
                ran = (ArrayList<Evenement>) new ServiceEvent().AfficherEvenement();
                return ran;
            }
        };
        task.setOnSucceeded(e -> {
            IdEventC3.setCellValueFactory(new PropertyValueFactory<>("IdEvent"));
            titreC3.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC3.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC3.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC3.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC3.setCellValueFactory(new PropertyValueFactory<>("date"));
            prixC3.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nbplaceC3.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            typeC3.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherEvenement();
        });
        Thread th = new Thread(task);
        th.start();
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
    private void DatePicker(ActionEvent event) {
         afficherEvenement();
    }

    @FXML
    private void CLicked(MouseEvent event) {
         Evenement ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
     
        String IdEvent = Integer.toString(ev.getIdEvent());
        String idpers = Integer.toString(ev.getIdPersonne());
        String nbp = Integer.toString(ev.getNbPlace());
        String prx = Float.toString(ev.getPrix());
        String date = ev.getDate().toString();
        description_txt.setText(ev.getDescription());
        lieu_txt.setText(ev.getLieu());
       id_client.setText(idpers);
        date_txt.setText(date);
        prix.setText(prx);
        nbplace.setText(nbp);
        titre.setText(ev.getTitre());
        type2.setText(ev.getType());
        idEvent.setText(IdEvent);
        Image a1 = new Image("http://localhost/Image/" + ev.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/" + ev.getImage());

        img_view.setImage(a1);
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

    @FXML
    private void supprimer(ActionEvent event) throws SQLException {
          Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette Evenement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String e = idEvent.getText();
            int r = Integer.parseInt(e);

            ServiceEvent s = new ServiceEvent();
            s.supprimerEvent(r);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();

            afficherEvenement();
        }
    }

    @FXML
    private void modifier(ActionEvent event) throws SQLException {
         ServiceEvent sv = new ServiceEvent();
        String i = nbplace.getText();
        int b = Integer.parseInt(i);
        String j = prix.getText();
        float d = Float.parseFloat(j);
        String e = idEvent.getText();
        int r = Integer.parseInt(e);
        String t = id_client.getText();
        int y = Integer.parseInt(t);
        Date d1 = java.sql.Date.valueOf(dateaf.getValue());
        Evenement e1;
        e1 = new Evenement(y, description_txt.getText(), b, titre.getText(), d, lieu_txt.getText(),type2.getText(), d1,1);
        e1.setImage(selectedfile.getName());
        if (e1.getImage().equals(selectedfile.getName())) {
            sv.ModifierEvent(e1, r);
            afficherEvenement();
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
