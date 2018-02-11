/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entites.Evenement;
import Services.ServiceEvent;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class AffichageController implements Initializable {

    public ArrayList<Evenement> ran;
    private Image image;
    @FXML
    private DatePicker DatePicker;
    @FXML
    private TableColumn<?, ?> IdEventC;
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
    private TableColumn<?, ?> date_debutC;
    @FXML
    private TableColumn<?, ?> date_finC;
    @FXML
    private TableColumn<?, ?> prixC;
    @FXML
    private TableColumn<?, ?> nbplaceC;
    @FXML
    private TableColumn<?, ?> typeC;
    private ObservableList<Evenement> listeE;
    @FXML
    private TableView<Evenement> tableView;
    @FXML
    private ImageView img_view;
    @FXML
    private TextField id_text;
    @FXML
    private TextArea description_txt;
    @FXML
    private TextField lieu_txt;
    @FXML
    private TextField date_txt;
    @FXML
    private Button supprimeEvent;
    @FXML
    private Button modifierEvent;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
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

            // IdPersonneC.setCellValueFactory(new PropertyValueFactory<>("IdPersonne"));
            IdEventC.setCellValueFactory(new PropertyValueFactory<>("IdEvent"));
            titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC.setCellValueFactory(new PropertyValueFactory<>("date"));
            date_debutC.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));
            date_finC.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
            nbplaceC.setCellValueFactory(new PropertyValueFactory<>("nbPlace"));
            typeC.setCellValueFactory(new PropertyValueFactory<>("type"));
            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherEvenement();
        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void CLicked(MouseEvent event) {
        Evenement ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
        // id_text.setText(ran.getType());
        description_txt.setText(ev.getDescription());
        lieu_txt.setText(ev.getLieu());
        String date = ev.getDate().toString();
        date_txt.setText(date);
        String IdEvent = Integer.toString(ev.getIdEvent());
        id_text.setText(IdEvent);
        Image a1 = new Image("http://localhost/Image/" + ev.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/" + ev.getImage());

        img_view.setImage(a1);
    }

    @FXML
    private void SupprimerEvent(ActionEvent event) throws SQLException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette Evenement ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String e = id_text.getText();
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
    private void ModifierEvent(ActionEvent event) {
        Evenement e = new Evenement();
        e = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());

        try {
            Parent page1 = FXMLLoader.load(getClass().getResource("ModifierEvent.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(AffichageController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void DatePicker(ActionEvent event) throws SQLException {
/*List<Evenement> ev = new ArrayList<>();
 ServiceEvent sv = new ServiceEvent();
        Date d= java.sql.Date.valueOf(DatePicker.getValue());
       ev= sv.chercher(d);*/
afficherEvenement();
    }

    @FXML
    private void returnnbut(ActionEvent event) throws IOException {
         Parent page1 = FXMLLoader.load(getClass().getResource("Acceuil.fxml"));
            Scene scene = new Scene(page1);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
    }

}
