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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class AjouterAvisController implements Initializable {

	public static String id;
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
	private Button ReturnButton;
	@FXML
	private HBox hboxRating;
	@FXML
	private ImageView etoilevide1;
	@FXML
	private ImageView etoilevide2;
	@FXML
	private ImageView etoilevide3;
	@FXML
	private ImageView etoilevide4;
	@FXML
	private ImageView etoilevide5;
	@FXML
	private HBox hboxRating1;
	@FXML
	private ImageView etoilevide11;
	@FXML
	private ImageView etoilevide21;
	@FXML
	private ImageView etoilevide31;
	@FXML
	private ImageView etoilevide41;
	@FXML
	private ImageView etoilevide51;
	@FXML
	private HBox hboxRating2;
	@FXML
	private ImageView etoilevide12;
	@FXML
	private ImageView etoilevide22;
	@FXML
	private ImageView etoilevide32;
	@FXML
	private ImageView etoilevide42;
	@FXML
	private ImageView etoilevide52;
	@FXML
	private HBox hboxRating3;
	@FXML
	private ImageView etoilevide13;
	@FXML
	private ImageView etoilevide23;
	@FXML
	private ImageView etoilevide33;
	@FXML
	private ImageView etoilevide43;
	@FXML
	private ImageView etoilevide53;
	
	Image etoileremp = new Image(getClass().getResource("star-shape-md2.png").toExternalForm());
	Image etoilevide = new Image(getClass().getResource("star-shape-md.png").toExternalForm());
	
	    private  int note1;
		private  int note2;
		private  int note3;
		private  int note4;


	/**
	 * Initializes the controller class.
	 */
	public void initialize(URL url, ResourceBundle rb) {
		// TODO
		List<String> Avis = new ArrayList<>();
		ServicePartage sv = new ServicePartage();
		/*
		Avis = sv.AfficherAvisPartage();
		for (String l : Avis) {
		LoginText.setText(l);
		}
		
		} catch (SQLException ex) {
		
		Logger.getLogger(AjouterAvisController.class.getName()).log(Level.SEVERE, null, ex);
		}*/
		Rating1();
		Rating2();
		Rating3();
		Rating4();
	}

	@FXML
	private void ButtonAjouter(ActionEvent event) throws SQLException, IOException {

		
		
		ServicePartage sv = new ServicePartage();
		//Ajout d'un entier
	      
		
		  
	     int a=Rating1();
		 int b=Rating2();
		 int c=Rating3();
		 int d=Rating4();

		Partage v = new Partage(a, b, c, d, CommentaireField.getText(), java.sql.Date.valueOf(LocalDate.now()));
		if ((a >= 0 && a <= 5) && (b >= 0 && b <= 5) && (c >= 0 && c <= 5) && (d >= 0 && d <= 5)) {
			sv.AjouterAvis(v);
			/*	FXMLLoader loader = new FXMLLoader(getClass().getResource("Succes.fxml"));
		Parent root = loader.load();
		SuccesController frc = loader.getController();
		Stage stage = new Stage();
		stage.setScene(new Scene(root, 43, 100));
		stage.show();*/
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Commentaire ajouté avec success");

			alert.showAndWait();

		} else {
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

	private void RatingButton(ActionEvent event) throws IOException {

		id = RatingButton.getAccessibleText();

		Parent Rating = FXMLLoader.load(getClass().getResource("Rating.fxml"));
		Scene Page_Rating = new Scene(Rating);

		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(Page_Rating);
		Page_Rating.getStylesheets().add("HD.jpg");
		stage.show();
	}
	
	 public void setEmail(String email) {
        this.LoginText.setText(email);
    }
  /*  public void setIDClient(int id) {
        String s = "";
        s = s + id;
        this.LoginText.setText(s);
    }*/
	
	public int Rating1()
	{
		etoilevide1.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide1.setImage(etoileremp);
			etoilevide2.setImage(etoilevide);
			etoilevide3.setImage(etoilevide);
			note1 = 1;
			System.out.println(note1);
			etoilevide1.setAccessibleText("touche pas");
		});
		etoilevide1.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide1.getAccessibleText() != "touche pas") {
				etoilevide1.setImage(etoilevide);
			}
		});
		etoilevide1.setOnMouseEntered((MouseEvent) -> {

			etoilevide1.setImage(etoileremp);
		});

		//************************etoile2***************************
		etoilevide2.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide1.setImage(etoileremp);
			etoilevide2.setImage(etoileremp);
			etoilevide3.setImage(etoilevide);
			etoilevide1.setAccessibleText("touche pas");
			etoilevide2.setAccessibleText("touche pas");
			note1 = 2;
			System.out.println(note1);
		});

		etoilevide2.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide2.getAccessibleText() != "touche pas") {
				etoilevide1.setImage(etoilevide);
				etoilevide2.setImage(etoilevide);
				etoilevide3.setImage(etoilevide);
			}
		});
		etoilevide2.setOnMouseEntered((MouseEvent) -> {

			etoilevide2.setImage(etoileremp);
			etoilevide2.setImage(etoileremp);
		});
		//*****************************etoile3***************************
		etoilevide3.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide1.setImage(etoileremp);
			etoilevide2.setImage(etoileremp);
			etoilevide3.setImage(etoileremp);
			etoilevide1.setAccessibleText("touche pas");
			etoilevide2.setAccessibleText("touche pas");
			etoilevide3.setAccessibleText("touche pas");
			note1 = 3;
			System.out.println(note1);

		});

		etoilevide3.setOnMouseEntered((MouseEvent) -> {

			etoilevide1.setImage(etoileremp);
			etoilevide2.setImage(etoileremp);
			etoilevide3.setImage(etoileremp);
		});
		//******************************etoile4**************************
		etoilevide4.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide1.setImage(etoileremp);
			etoilevide2.setImage(etoilevide);
			etoilevide3.setImage(etoilevide);
			note1 = 4;
			System.out.println(note1);
			etoilevide1.setAccessibleText("touche pas");
		});
		etoilevide4.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide4.getAccessibleText() != "touche pas") {
				etoilevide4.setImage(etoilevide);
			}
		});
		etoilevide4.setOnMouseEntered((MouseEvent) -> {

			etoilevide4.setImage(etoileremp);
		});
		//*******************************etoile5************************
		etoilevide5.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide1.setImage(etoileremp);
			etoilevide2.setImage(etoilevide);
			etoilevide3.setImage(etoilevide);
			note1 = 5;
			System.out.println(note1);
			etoilevide5.setAccessibleText("touche pas");
		});
		etoilevide5.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide5.getAccessibleText() != "touche pas") {
				etoilevide5.setImage(etoilevide);
			}
		});
		etoilevide5.setOnMouseEntered((MouseEvent) -> {

			etoilevide5.setImage(etoileremp);
		});
		return note1;
	
}
	
	public int Rating2()
	{
		etoilevide11.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide11.setImage(etoileremp);
			etoilevide21.setImage(etoilevide);
			etoilevide31.setImage(etoilevide);
			note2 = 1;
			System.out.println(note2);
			etoilevide11.setAccessibleText("touche pas");
		});
		etoilevide11.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide11.getAccessibleText() != "touche pas") {
				etoilevide11.setImage(etoilevide);
			}
		});
		etoilevide11.setOnMouseEntered((MouseEvent) -> {

			etoilevide11.setImage(etoileremp);
		});

		//************************etoile2***************************
		etoilevide21.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide11.setImage(etoileremp);
			etoilevide21.setImage(etoileremp);
			etoilevide31.setImage(etoilevide);
			etoilevide11.setAccessibleText("touche pas");
			etoilevide21.setAccessibleText("touche pas");
			note2 = 2;
			System.out.println(note2);
		});

		etoilevide21.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide21.getAccessibleText() != "touche pas") {
				etoilevide11.setImage(etoilevide);
				etoilevide21.setImage(etoilevide);
				etoilevide31.setImage(etoilevide);
			}
		});
		etoilevide21.setOnMouseEntered((MouseEvent) -> {

			etoilevide21.setImage(etoileremp);
			etoilevide21.setImage(etoileremp);
		});
		//*****************************etoile3***************************
		etoilevide31.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide11.setImage(etoileremp);
			etoilevide21.setImage(etoileremp);
			etoilevide31.setImage(etoileremp);
			etoilevide11.setAccessibleText("touche pas");
			etoilevide21.setAccessibleText("touche pas");
			etoilevide31.setAccessibleText("touche pas");
			note2 = 3;
			System.out.println(note2);

		});

		etoilevide31.setOnMouseEntered((MouseEvent) -> {

			etoilevide11.setImage(etoileremp);
			etoilevide21.setImage(etoileremp);
			etoilevide31.setImage(etoileremp);
		});
		//******************************etoile4**************************
		etoilevide41.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide11.setImage(etoileremp);
			etoilevide21.setImage(etoilevide);
			etoilevide31.setImage(etoilevide);
			note2 = 4;
			System.out.println(note2);
			etoilevide11.setAccessibleText("touche pas");
		});
		etoilevide41.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide41.getAccessibleText() != "touche pas") {
				etoilevide41.setImage(etoilevide);
			}
		});
		etoilevide41.setOnMouseEntered((MouseEvent) -> {

			etoilevide41.setImage(etoileremp);
		});
		//*******************************etoile5************************
		etoilevide51.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide11.setImage(etoileremp);
			etoilevide21.setImage(etoilevide);
			etoilevide31.setImage(etoilevide);
			note2 = 5;
			System.out.println(note2);
			etoilevide51.setAccessibleText("touche pas");
		});
		etoilevide51.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide51.getAccessibleText() != "touche pas") {
				etoilevide51.setImage(etoilevide);
			}
		});
		etoilevide51.setOnMouseEntered((MouseEvent) -> {

			etoilevide51.setImage(etoileremp);
		});
		return note2;
	
}
	public int Rating3()
	{
		etoilevide12.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide12.setImage(etoileremp);
			etoilevide22.setImage(etoilevide);
			etoilevide32.setImage(etoilevide);
			note4 = 1;
			System.out.println(note4);
			etoilevide12.setAccessibleText("touche pas");
		});
		etoilevide12.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide12.getAccessibleText() != "touche pas") {
				etoilevide12.setImage(etoilevide);
			}
		});
		etoilevide12.setOnMouseEntered((MouseEvent) -> {

			etoilevide12.setImage(etoileremp);
		});

		//************************etoile2***************************
		etoilevide22.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide12.setImage(etoileremp);
			etoilevide22.setImage(etoileremp);
			etoilevide32.setImage(etoilevide);
			etoilevide12.setAccessibleText("touche pas");
			etoilevide22.setAccessibleText("touche pas");
			note4 = 2;
			System.out.println(note4);
		});

		etoilevide22.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide22.getAccessibleText() != "touche pas") {
				etoilevide12.setImage(etoilevide);
				etoilevide22.setImage(etoilevide);
				etoilevide32.setImage(etoilevide);
			}
		});
		etoilevide22.setOnMouseEntered((MouseEvent) -> {

			etoilevide22.setImage(etoileremp);
		});
		//*****************************etoile3***************************
		etoilevide32.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide12.setImage(etoileremp);
			etoilevide22.setImage(etoileremp);
			etoilevide32.setImage(etoileremp);
			etoilevide12.setAccessibleText("touche pas");
			etoilevide22.setAccessibleText("touche pas");
			etoilevide32.setAccessibleText("touche pas");
			note4 = 3;
			System.out.println(note4);

		});

		etoilevide32.setOnMouseEntered((MouseEvent) -> {

			etoilevide12.setImage(etoileremp);
			etoilevide22.setImage(etoileremp);
			etoilevide32.setImage(etoileremp);
		});
		//******************************etoile4**************************
		etoilevide42.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide12.setImage(etoileremp);
			etoilevide22.setImage(etoilevide);
			etoilevide32.setImage(etoilevide);
			note4 = 4;
			System.out.println(note4);
			etoilevide12.setAccessibleText("touche pas");
		});
		etoilevide42.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide42.getAccessibleText() != "touche pas") {
				etoilevide42.setImage(etoilevide);
			}
		});
		etoilevide42.setOnMouseEntered((MouseEvent) -> {

			etoilevide42.setImage(etoileremp);
		});
		//*******************************etoile5************************
		etoilevide52.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide12.setImage(etoileremp);
			etoilevide22.setImage(etoilevide);
			etoilevide32.setImage(etoilevide);
			note4 = 5;
			System.out.println(note4);
			etoilevide52.setAccessibleText("touche pas");
		});
		etoilevide52.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide52.getAccessibleText() != "touche pas") {
				etoilevide52.setImage(etoilevide);
			}
		});
		etoilevide52.setOnMouseEntered((MouseEvent) -> {

			etoilevide52.setImage(etoileremp);
		});
		return note3;
	
}
	
	public int Rating4()
	{
		etoilevide13.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide13.setImage(etoileremp);
			etoilevide23.setImage(etoilevide);
			etoilevide33.setImage(etoilevide);
			note3 = 1;
			System.out.println(note3);
			etoilevide1.setAccessibleText("touche pas");
		});
		etoilevide13.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide13.getAccessibleText() != "touche pas") {
				etoilevide13.setImage(etoilevide);
			}
		});
		etoilevide13.setOnMouseEntered((MouseEvent) -> {

			etoilevide13.setImage(etoileremp);
		});

		//************************etoile2***************************
		etoilevide23.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide13.setImage(etoileremp);
			etoilevide23.setImage(etoileremp);
			etoilevide33.setImage(etoilevide);
			etoilevide13.setAccessibleText("touche pas");
			etoilevide23.setAccessibleText("touche pas");
			note3 = 2;
			System.out.println(note3);
		});

		etoilevide23.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide23.getAccessibleText() != "touche pas") {
				etoilevide13.setImage(etoilevide);
				etoilevide23.setImage(etoilevide);
				etoilevide33.setImage(etoilevide);
			}
		});
		etoilevide23.setOnMouseEntered((MouseEvent) -> {

			etoilevide23.setImage(etoileremp);
		});
		//*****************************etoile3***************************
		etoilevide33.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide13.setImage(etoileremp);
			etoilevide23.setImage(etoileremp);
			etoilevide3.setImage(etoileremp);
			etoilevide13.setAccessibleText("touche pas");
			etoilevide23.setAccessibleText("touche pas");
			etoilevide33.setAccessibleText("touche pas");
			note3 = 3;
			System.out.println(note3);

		});

		etoilevide33.setOnMouseEntered((MouseEvent) -> {

			etoilevide13.setImage(etoileremp);
			etoilevide23.setImage(etoileremp);
			etoilevide33.setImage(etoileremp);
		});
		//******************************etoile4**************************
		etoilevide43.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide13.setImage(etoileremp);
			etoilevide23.setImage(etoilevide);
			etoilevide33.setImage(etoilevide);
			note3 = 4;
			System.out.println(note3);
			etoilevide13.setAccessibleText("touche pas");
		});
		etoilevide43.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide43.getAccessibleText() != "touche pas") {
				etoilevide43.setImage(etoilevide);
			}
		});
		etoilevide43.setOnMouseEntered((MouseEvent) -> {

			etoilevide43.setImage(etoileremp);
		});
		//*******************************etoile5************************
		etoilevide53.setOnMouseClicked((javafx.scene.input.MouseEvent E1) -> {
			etoilevide13.setImage(etoileremp);
			etoilevide23.setImage(etoilevide);
			etoilevide33.setImage(etoilevide);
			note3 = 5;
			System.out.println(note3);
			etoilevide53.setAccessibleText("touche pas");
		});
		etoilevide53.setOnMouseExited((javafx.scene.input.MouseEvent E2) -> {
			if (etoilevide53.getAccessibleText() != "touche pas") {
				etoilevide53.setImage(etoilevide);
			}
		});
		etoilevide53.setOnMouseEntered((MouseEvent) -> {

			etoilevide53.setImage(etoileremp);
		});
		
		return note4;
	
}
	
	
}
