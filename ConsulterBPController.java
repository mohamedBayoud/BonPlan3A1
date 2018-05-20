/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.BP;
import Entities.Notif;
import Entities.Sejourner;
import Services.ServiceBP;
import Services.ServiceNotif;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.controlsfx.glyphfont.FontAwesome;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class ConsulterBPController implements Initializable {

	@FXML
	private Button chercher;
	@FXML
	private TextField chercherText;
	@FXML
	private Label adresseL;
	@FXML
	private Label villeL;
	@FXML
	private Label descriptionL;
	@FXML
	private Label nomL;
	@FXML
	private TextField idBP;
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
	public ArrayList<BP> ran;
	private ObservableList<BP> masterData = FXCollections.observableArrayList();
	@FXML
	private TableColumn<?, ?> type;
	@FXML
	private Label hotel;
	@FXML
	private Label restaurant;
	@FXML
	private Button logout;
	@FXML
	private Button retour;
	@FXML
	private TableView<?> tableView1;
	@FXML
	private TableColumn<?, ?> nom1;
	@FXML
	private TableColumn<?, ?> photo1;
	@FXML
	private TableColumn<?, ?> description1;
	@FXML
	private TableColumn<?, ?> ville1;
	@FXML
	private TableColumn<?, ?> adresse1;
	@FXML
	private TextField email;
	@FXML
	private TextField role;
	@FXML
	private TextField role1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		afficher();
	}	
	public void afficher()
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<BP>) new ServiceBP().Afficher();
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
			afficher();
		});
		Thread th = new Thread(task);
		th.start();
	}

	@FXML
	private void chercher(ActionEvent event) throws SQLException, IOException {
		String d = chercherText.getText() ;
		List<BP> list = new ArrayList<>();
        ServiceBP su = new ServiceBP();
        list = su.chercher(d);
        String a = chercherText.getText();
        if (list.isEmpty()){
			tableView1.setVisible(true);
		Image o = new Image("/Images/cross.png");
		Notification.notificationInexistant(event, o);
        }
        else {
        for(int i=0; i<list.size(); i++){ 	
			afficher1(list.get(i).getVille());
			}	
	}
	}
	public void afficher1(String d)
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<BP>) new ServiceBP().chercher(d);
				return ran;
			}
		};
		task.setOnSucceeded((WorkerStateEvent e) -> {

				  
			nom.setCellValueFactory(new PropertyValueFactory<>("nom"));
			photo.setCellValueFactory(new PropertyValueFactory<>("photo"));
			description.setCellValueFactory(new PropertyValueFactory<>("description"));
			adresse.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			ville.setCellValueFactory(new PropertyValueFactory<>("ville"));
            type.setCellValueFactory(new PropertyValueFactory<>("type"));
			tableView.setItems(FXCollections.observableArrayList(task.getValue()));
		});
		task.setOnFailed(e -> {
			//afficherH1(d);
		});
		Thread th = new Thread(task);
		th.start();
	}

	@FXML
	private void CLicked(MouseEvent event) {
		BP bp = (BP) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
		System.out.println();
		hotel.setVisible(true);
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
		System.out.println("papa");
		
		
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
