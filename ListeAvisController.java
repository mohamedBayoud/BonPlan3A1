/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Partage;
import Services.ServicePartage;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import static javafx.fxml.FXMLLoader.load;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

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
	private Button Supprimer;
	@FXML
	private Button Modifier;
	@FXML
	private TableColumn<Partage, String> Commentaire;

	@FXML
	private ComboBox<Integer> Box;
	@FXML
	private ComboBox<Integer> Box2;

	private TextField AvisText;

	@FXML
	private TextField CommentaireField;
	@FXML
	private Button Ajouter;
	@FXML
	private Button buttonStat;
	@FXML
	private TextField NoteCuisineField;
	@FXML
	private TextField NoteRapportField;
	@FXML
	private TextField NoteServiceField;
	@FXML
	private TextField NoteAmbianceField;
	Image etoileremp = new Image(getClass().getResource("star-shape-md2.png").toExternalForm());
	Image etoilevide = new Image(getClass().getResource("star-shape-md.png").toExternalForm());
	@FXML
	private HBox hboxRating;
	@FXML
	private ImageView etoilevide1;
	@FXML
	private ImageView etoilevide3;
	@FXML
	private ImageView etoilevide4;
	@FXML
	private ImageView etoilevide5;
	@FXML
	private ImageView etoilevide2;

	private static int note;
	@FXML
	private TextField role;
	@FXML
	private TextField id_client;
	@FXML
	private TextField email_client;
	@FXML
	private Button Retour;

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
		Partage P = new Partage();
		ServicePartage sv = new ServicePartage();

		afficherCommentaire(); // Commenataire.setCellValueFactory();

		List<Integer> id = new ArrayList<>();
		try {
			id = sv.AfficherIdPartage();
			for (Integer l : id) {
				Box.getItems().addAll(l);
				Box2.getItems().addAll(l);
			}
		} catch (SQLException ex) {
			Logger.getLogger(ListeAvisController.class.getName()).log(Level.SEVERE, null, ex);
		}
		//*****************************etoile 1***************************
		//*****************************etoile 1***************************
		//*****************************etoile 1***************************
		//*****************************etoile 1***************************

		System.out.println(sv.moyenneNote());
		if (sv.moyenneNote() > 0 && sv.moyenneNote() <= 1) {
			Rating1();
		} else if (sv.moyenneNote() > 1 && sv.moyenneNote() <= 2) {
			Rating2();
		} else if (sv.moyenneNote() > 2 && sv.moyenneNote() <= 3) {
			Rating3();
		} else if (sv.moyenneNote() > 3 && sv.moyenneNote() <= 4) {
			Rating4();
		} else if (sv.moyenneNote() > 4 && sv.moyenneNote() <= 5) {
			Rating5();
		}
		

	}

	@FXML
	private void ButtonSupprimer(ActionEvent event) throws IOException, SQLException {
		ServicePartage sv = new ServicePartage();
		int Id = Box2.getValue();
		// int IdPartage = Integer.parseInt(Id);
		Partage P = new Partage(Id);
		sv.supprimerAvis(P);
		afficherCommentaire();

	}

	@FXML
	private void Modifier(ActionEvent event) throws IOException, SQLException {
		{

			int Id = Box.getValue();

			ServicePartage sv = new ServicePartage();
			String a = NoteCuisineField.getText();
			int NoteCuisine = Integer.parseInt(a);
			String b = NoteCuisineField.getText();
			int NoteRapport = Integer.parseInt(b);
			String c = NoteCuisineField.getText();
			int NoteService = Integer.parseInt(c);
			String d = NoteCuisineField.getText();
			int NoteAmbiance = Integer.parseInt(d);
			Partage P = new Partage(NoteCuisine, NoteRapport, NoteService, NoteAmbiance, CommentaireField.getText(), java.sql.Date.valueOf(LocalDate.now()));
			if ((NoteCuisine >= 0 && NoteCuisine <= 5) && (NoteRapport >= 0 && NoteRapport <= 5) && (NoteService >= 0 && NoteService <= 5) && (NoteAmbiance >= 0 && NoteAmbiance <= 5)) 
			{
			sv.ModifierAvis(P, Id);
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Commentaire Modifié avec success");

			alert.showAndWait();
			afficherCommentaire();
			}
			else
			{
		    Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, an Error Dialog");
			alert.setContentText("Ooops, there was an error!");
			alert.showAndWait();
			}

		}
	}

	@FXML
	private void Ajouter(ActionEvent event) throws IOException {
		{
        if(this.role.getText().equals("membre"))
		{
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("AjouterAvis.fxml"));
		    Parent root = loader.load();
            AjouterAvisController frc = loader.getController();
           frc.setRole(role.getText());
           frc.setEmail(email_client.getText());
	       String e = id_client.getText();
           int r = Integer.parseInt(e);
           frc.setIDClient(r);
           
            Ajouter.getScene().setRoot(root);
		}else{
		 Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Error Dialog");
			alert.setHeaderText("Look, an Error Dialog");
			alert.setContentText("Vous n'etes pas un membre pour ajouter un commentaire!");
			alert.showAndWait();
		}
	}
	}

	@FXML
	private void ButtonStat(ActionEvent event) throws IOException {
		{
			 FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistics.fxml"));
		    Parent root = loader.load();
            StatisticsController frc = loader.getController();
           frc.setRole(role.getText());
           frc.setEmail(email_client.getText());
	       String e = id_client.getText();
           int r = Integer.parseInt(e);
           frc.setIDClient(r);
		               buttonStat.getScene().setRoot(root);

		}
	}
       
	/*
	private void ButtonStat2(ActionEvent event) throws IOException {
		{
			Parent Statistique = FXMLLoader.load(getClass().getResource("Stat3.fxml"));
			Scene Page_Stat = new Scene(Statistique);

			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.setScene(Page_Stat);
			Page_Stat.getStylesheets().add("HD.jpg");
			stage.show();

		}
	}
*/
	private void CLicked(MouseEvent event) {
		Partage pv = personsTable.getItems().get(personsTable.getSelectionModel().getSelectedIndex());
		// id_text.setText(ran.getType());
		AvisText.setText(pv.getAvis());
	}

	public void Rating1() {
		etoilevide1.setImage(etoileremp);
	}

	public void Rating2() {
		etoilevide1.setImage(etoileremp);
		etoilevide2.setImage(etoileremp);
	}

	public void Rating3() {
		etoilevide1.setImage(etoileremp);
		etoilevide2.setImage(etoileremp);
		etoilevide3.setImage(etoileremp);
	}

	public void Rating4() {
		etoilevide1.setImage(etoileremp);
		etoilevide2.setImage(etoileremp);
		etoilevide3.setImage(etoileremp);
		etoilevide4.setImage(etoileremp);

	}

	public void Rating5() {
		etoilevide1.setImage(etoileremp);
		etoilevide2.setImage(etoileremp);
		etoilevide3.setImage(etoileremp);
		etoilevide4.setImage(etoileremp);
		etoilevide5.setImage(etoileremp);

	}
	
		@FXML
	private void ReturnButton(ActionEvent event) throws IOException {
		    FXMLLoader loader = new FXMLLoader(getClass().getResource("ConsulterR.fxml"));
		    Parent root = loader.load();
            ConsulterRController frc = loader.getController();
            //frc.setRole(role.getText());
            //frc.setEmail(email_client.getText());
	        String e = id_client.getText();
            int r = Integer.parseInt(e);
            //frc.setIDClient(r);
	        Retour.getScene().setRoot(root);

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
