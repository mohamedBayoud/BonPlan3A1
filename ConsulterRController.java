/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.BP;
import Entities.Culinaire;
import Entities.Notif;
import Entities.Sejourner;
import Entities.User;
import Services.ServiceBP;
import Services.ServiceNotif;
import Services.ServiceUser;
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
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SortEvent;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadowBuilder;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class ConsulterRController implements Initializable {

	@FXML
	private TableView<BP> tableView;
	@FXML
	private TableColumn<?,?> nom;
	@FXML
	private TableColumn<?,?> description;
	@FXML
	private TableColumn<?,?>ville;
	@FXML
	private TableColumn<?,?> adresse;
    public ArrayList<Culinaire> ran;
	@FXML
	private ImageView imgView;
	@FXML
	private TextField idBP;
	@FXML
	private TableColumn<?, ?> photo;
	@FXML
	private Label nomL;
	@FXML
	private Label descriptionL;
	@FXML
	private Label villeL;
	@FXML
	private Label adresseL;
	@FXML
	private TextField chercherText;
	@FXML
	private Button chercher;
	private ObservableList<BP> masterData = FXCollections.observableArrayList();
	@FXML
	private Button reserver;
	@FXML
	private TextField email_client;
	@FXML
	private Button retour;
	@FXML
	private Button logout;
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
	private Button Avis;
	@FXML
	private TextField email;
	@FXML
	private TextField role;
	@FXML
	private TextField role1;
	@FXML
	private TextField id_client;
	@FXML
	private TextField email_client1;


	 

        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
      afficherH();
	}
    public void afficherH()
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Culinaire>) new ServiceBP().AfficherRestoM();
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
	private void CLicked(MouseEvent event) throws IOException {
		reserver.setVisible(true);
		Avis.setVisible(true);
		Culinaire bp = (Culinaire) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
		String idBP1 = Integer.toString(bp.getIdBonPlan());
		idBP.setText(idBP1);
		nomL.setText(bp.getNom());
		System.out.println("------------------------------");
		System.out.println("http://localhost/Images/" + bp.getPhoto());
		imgView.setImage(new Image("file://"+bp.getPhoto()));
		descriptionL.setText(bp.getDescription());
		adresseL.setText(bp.getAdresse());
		villeL.setText(bp.getVille());
	}

	@FXML
	private void chercher(ActionEvent event) throws SQLException, IOException {
		
		String d = chercherText.getText() ;
		List<Culinaire> list = new ArrayList<>();
        ServiceBP su = new ServiceBP();
        list = su.chercherBP2(d);
        String a = chercherText.getText();
        if (list.isEmpty()){
			tableView1.setVisible(true);
            ServiceNotif s = new ServiceNotif();
            Notif n = new Notif(1,email.getText(),d);
            s.AjouterNotif(n);
            System.out.println("sk");
			Image o = new Image("/Images/cross.png");
		Notification.notificationChercher(event, o);
        }
        else {
        for(int i=0; i<list.size(); i++){ 	
			afficherH2(list.get(i).getVille());
			}	
	} }
	public void afficherH2(String d)
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Culinaire>) new ServiceBP().chercherBP2(d);
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
			//afficherH2(d);
		});
		Thread th = new Thread(task);
		th.start();
	}

	@FXML
	private void reserver(ActionEvent event) throws IOException {
		 Parent page = FXMLLoader.load(getClass().getResource("Reservation.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
		
	}
	 public TextField getEmail_client() {
        return email_client;
    }

    public void setEmail_client(String email_client) {
        this.email_client.setText(email_client); 
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

	@FXML
	private void avisAction(ActionEvent event) throws IOException {
		if(this.role1.getText().equals("membre"))
		{
		 FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAvis.fxml"));
		    Parent root = loader.load();
            ListeAvisController frc = loader.getController();
           frc.setRole(role1.getText());
           frc.setEmail(email_client1.getText());
	       String e = id_client.getText();
           int r = Integer.parseInt(e);
           frc.setIDClient(r);           
           Avis.getScene().setRoot(root);
	}
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

	public TextField getRole1() {
		return role1;
	}

	public void setRole1(TextField role1) {
		this.role1 = role1;
	}

	public TextField getId_client() {
		return id_client;
	}

	public void setId_client(TextField id_client) {
		this.id_client = id_client;
	}

	public TextField getEmail_client1() {
		return email_client1;
	}

	public void setEmail_client1(TextField email_client1) {
		this.email_client1 = email_client1;
	}

	void setIDClient(int r) {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
	
	
     
}
	
	

	

