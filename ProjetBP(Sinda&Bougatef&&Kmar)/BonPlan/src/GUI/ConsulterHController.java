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

/**
 * FXML Controller class
 *
 * @author senda
 */
public class ConsulterHController implements Initializable {

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
    public ArrayList<Sejourner> ran;
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
	private Button Avis;


	 

        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
      afficherH();
	}
    public void afficherH()
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Sejourner>) new ServiceBP().AfficherHotel();
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
		Sejourner bp = (Sejourner) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
		String idBP1 = Integer.toString(bp.getIdBonPlan());
		idBP.setText(idBP1);
		nomL.setText(bp.getNom());
		System.out.println("------------------------------");
		System.out.println("http://localhost/Images/" + bp.getPhoto());
		imgView.setImage(new Image("http://localhost/Image/"+bp.getPhoto()));
		descriptionL.setText(bp.getDescription());
		adresseL.setText(bp.getAdresse());
		villeL.setText(bp.getVille());
	}

	@FXML
	private void chercher(ActionEvent event) throws SQLException {
		
		String d = chercherText.getText() ;
		List<Sejourner> list = new ArrayList<>();
        ServiceBP su = new ServiceBP();
        list = su.chercherBP(d);
        String a = chercherText.getText();
                
        for(int i=0; i<list.size(); i++){ 	
			afficherH1(list.get(i).getVille());
			}	
	}
	public void afficherH1(String d)
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Sejourner>) new ServiceBP().chercherBP(d);
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
			afficherH1(d);
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

	@FXML
	private void AvisAction(ActionEvent event) throws IOException {
		    Parent page = FXMLLoader.load(getClass().getResource("ListeAvis.fxml"));
            Scene scene = new Scene(page);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
	}
	
     
}
	
	

	

