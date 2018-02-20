/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Partage;
import Services.ServicePartage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class AjouterAvisController implements Initializable {

	public static String id;
	@FXML
	private Button RatingButton;
	@FXML
	private TextField LoginText;
	@FXML
	private TextField NoteCuisineField;
	@FXML
	private TextField NoteRapportField;
	@FXML
	private TextField NoteServiceField;
	@FXML
	private TextField NoteAmbianceField;
	@FXML
	private TextField CommentaireField;
	@FXML
	private Button ButtonAjouter;
	@FXML
	private ImageView ImageView;
	@FXML
	private Button ReturnButton;

	/**
	 * Initializes the controller class.
	 */
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		List<String> Avis = new ArrayList<>();

		ServicePartage sv = new ServicePartage();
		try {
			Avis = sv.AfficherAvisPartage();
			for (String l : Avis) {
				LoginText.setText(l);
			}

		} catch (SQLException ex) {

			Logger.getLogger(AjouterAvisController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	@FXML
	private void ButtonAjouter(ActionEvent event) throws SQLException, IOException {

		ServicePartage sv = new ServicePartage();
		//Ajout d'un entier
		String a = NoteCuisineField.getText();
		int NoteCuisine = Integer.parseInt(a);
		String b = NoteCuisineField.getText();
		int NoteRapport = Integer.parseInt(b);
		String c = NoteCuisineField.getText();
		int NoteService = Integer.parseInt(c);
		String d = NoteCuisineField.getText();
		int NoteAmbiance = Integer.parseInt(d);

		Partage v = new Partage(NoteCuisine, NoteRapport, NoteService, NoteAmbiance, CommentaireField.getText(), java.sql.Date.valueOf(LocalDate.now()));
		if ((NoteCuisine >= 0 && NoteCuisine <= 10) && (NoteRapport >= 0 && NoteRapport <= 10) && (NoteService >= 0 && NoteService <= 10) && (NoteAmbiance >= 0 && NoteAmbiance <= 10)) 
		{
			sv.AjouterAvis(v);
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Succes.fxml"));
		Parent root = loader.load();
		SuccesController frc = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root, 43, 100));
		stage.show();
		 
		}else{
			Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error Dialog");
		alert.setHeaderText("Look, an Error Dialog");
		alert.setContentText("Ooops, there was an error!");
		alert.showAndWait();

		
		}
	}

	@FXML
	private void ReturnButton(ActionEvent event) throws IOException {
		Parent page1 = FXMLLoader.load(getClass().getResource("ListeAvis.fxml"));
		Scene scene = new Scene(page1);
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(scene);
		stage.show();
	}

	@FXML
	private void RatingButton(ActionEvent event) throws IOException {

		id = RatingButton.getAccessibleText();

		Parent Rating = FXMLLoader.load(getClass().getResource("Rating.fxml"));
		Scene Page_Rating = new Scene(Rating);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(Page_Rating);
		Page_Rating.getStylesheets().add("HD.jpg");
		stage.show();
	}

}
