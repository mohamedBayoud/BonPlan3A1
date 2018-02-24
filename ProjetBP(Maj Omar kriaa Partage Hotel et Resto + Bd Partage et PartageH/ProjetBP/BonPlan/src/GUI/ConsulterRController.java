/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.BP;
import Entities.Culinaire;
import Entities.Sejourner;
import Entities.User;
import Services.ServiceBP;
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
	private Button Avis;
	@FXML
	private TextField role;
	@FXML
	private TextField id_client;
	@FXML
	private TextField email_client;


	 

        
	@Override
	public void initialize(URL url, ResourceBundle rb) {
      afficherH();
	}
    public void afficherH()
    {	
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Culinaire>) new ServiceBP().AfficherResto();
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
	private void chercher(ActionEvent event) throws SQLException {
		
		String d = chercherText.getText() ;
		List<Culinaire> list = new ArrayList<>();
        ServiceBP su = new ServiceBP();
        list = su.chercherBP2(d);
        String a = chercherText.getText();
                
        for(int i=0; i<list.size(); i++){
            if (list.get(i).getVille().equals(a) ) 
            {			
		     afficherH(); 
		    System.out.println("g"); 
			}
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

	@FXML
	private void AvisRestoAction(ActionEvent event) throws IOException {
		  FXMLLoader loader = new FXMLLoader(getClass().getResource("ListeAvis.fxml"));
		    Parent root = loader.load();
            ListeAvisController frc = loader.getController();
            frc.setRole(role.getText());
            frc.setEmail(email_client.getText());
            String e = id_client.getText();
            int r = Integer.parseInt(e);
            frc.setIDClient(r);
            Avis.getScene().setRoot(root);
	}
	
     
}
	
	

	

