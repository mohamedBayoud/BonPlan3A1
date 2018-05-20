/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.BP;
import Entities.Sejourner;
import Services.ServiceBP;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class AdminHotelController implements Initializable {

	@FXML
	private Button valider;
	@FXML
	private Label adresseL;
	@FXML
	private Label villeL;
	@FXML
	private Label descriptionL;
	@FXML
	private Label nomL;
	@FXML
	private ImageView imgView;
	@FXML
	private TableView<BP> tableView;
	@FXML
	private TableColumn<?, ?> nom;
	@FXML
	private TableColumn<?, ?> photo;
	@FXML
	private TableColumn<?, ?> description;
	@FXML
	private TableColumn<?, ?> ville;
	@FXML
	private TableColumn<?, ?> adresse;
	@FXML
	private Button supprimer;
	public ArrayList<Sejourner> ran;
	private ObservableList<BP> masterData = FXCollections.observableArrayList();
	@FXML
	private TextField idBP;
	@FXML
	private Button consulter;
	@FXML
	private Button retour;
	@FXML
	private Button logout;
	@FXML
	private TextField email;
	@FXML
	private TextField role;



	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		afficherH();
	}
 public void afficherH()
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Sejourner>) new ServiceBP().AfficherHotelA();
				return ran;
			}
		};
		task.setOnSucceeded((WorkerStateEvent e) -> {

				  
			nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
			description.setCellValueFactory(new PropertyValueFactory<>("description"));
			adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
			tableView.setItems(FXCollections.observableArrayList(task.getValue()));
		});
		task.setOnFailed(e -> {
			afficherH();
		});
		Thread th = new Thread(task);
		th.start();
	}	

	@FXML
	private void valider(ActionEvent event) throws IOException {
		
		ServiceBP s = new ServiceBP();
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation de validation");
        alert.setHeaderText("voulez-vous vraiment valider cet hôtel ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
		String b = idBP.getText();
        int r=Integer.parseInt(b);
        s.validerH(r);
		afficherH();
		Image o = new Image("/Images/Tick.png");
		Notification.notificationDeValidation(event, o);
		Parent page = FXMLLoader.load(getClass().getResource("AdminHotel.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
		}
				
	}

	@FXML
	private void CLicked(MouseEvent event) {
		valider.setVisible(true);
		supprimer.setVisible(true);
		Sejourner bp = (Sejourner) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
		String idBP1 = Integer.toString(bp.getIdBonPlan());
		idBP.setText(idBP1);
		nomL.setText(bp.getNom());
		/*System.out.println("------------------------------");
		System.out.println("http://localhost/Images/" + bp.getPhoto());
		imgView.setImage(new Image("http://localhost/Image/"+bp.getPhoto()));*/
		System.out.println("------------------------------");
		System.out.println("http://localhost/Images/" + bp.getPhoto());
		imgView.setImage(new Image("file://"+bp.getPhoto()));
		descriptionL.setText(bp.getDescription());
		adresseL.setText(bp.getAdresse());
		villeL.setText(bp.getVille());
	}

	@FXML
	private void supprimer(ActionEvent event) {
		ServiceBP s = new ServiceBP();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez-vous vraiment supprimer cet hôtel ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        String b = idBP.getText();
        int r=Integer.parseInt(b);
        s.SupprimerBP(r);
		Image o = new Image("/Images/Tick.png");
		Notification.notificationDeSuppression(event, o);
        afficherH();
		System.out.println(r);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();
        }
	}

	@FXML
	private void consulter(ActionEvent event) throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterBP.fxml"));
        Parent root = loader.load();
        ConsulterBPController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
        retour.getScene().setRoot(root);
	}

	@FXML
	private void logout(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController frc = loader.getController();
        logout.getScene().setRoot(root);
	}

	@FXML
	private void retour(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("BonPlanMenu.fxml"));
        Parent root = loader.load();
        BonPlanMenuController frc = loader.getController();
        frc.setEmail(email.getText());
		frc.setRole(role.getText());
        retour.getScene().setRoot(root);
}
	public TextField getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email.setText(email);
	}

	public TextField getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role.setText(role);
	}
	
	
}
