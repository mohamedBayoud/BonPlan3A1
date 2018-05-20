/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.BP;
import Entities.Sejourner;
import Services.ServiceBP;
import Services.Upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class ModifierBPController implements Initializable {

	@FXML
	private ImageView AjouterImg;
	@FXML
	private TextField prixNuitText;
	@FXML
	private TextField nbrChambreText;
	@FXML
	private Button modifierBP;
	@FXML
	private TextField nomText;
	@FXML
	private TextField villeText;
	@FXML
	private TextField adresseText;
	@FXML
	private TextArea descriptionText;
	private ListView<?> photoText;
	@FXML
	private Button browse;
	@FXML
	private Button supprimerBP;
	@FXML
	private TableColumn<?, ?> nomBP;
	@FXML
	private TableColumn<?, ?> photoBP;
	@FXML
	private TableColumn<?, ?> descriptionBP;
	@FXML
	private TableColumn<?, ?> adresseBP;
	@FXML
	private TableColumn<?, ?> villeBP;
	public ArrayList<Sejourner> ran;
	@FXML
	private TableView<BP> tableView;
	File selectedfile;
	String path_img;
	Upload u = new Upload();
	private Pane paneImage;
	private ImageView photo;
	@FXML
	private ImageView imgView;
	@FXML
	private TextField imageTelecharger;
	@FXML
	private TextField idBP;
	@FXML
	private Button retour;
	@FXML
	private TextField valide;
	@FXML
	private TableColumn<?, ?> villeBP1;
	@FXML
	private TextField email;
	@FXML
	private TextField role;
	@FXML
	private Label valide2;
	@FXML
	private Label valide3;
	@FXML
	private Label valide4;
	@FXML
	private Label valide5;
	@FXML
	private Label valide6;
	@FXML
	private Label valide1;

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		afficherBP();
	}

	public void afficherBP() {
		Task<ArrayList<BP>> task = new Task() {

			@Override
			protected Object call() throws SQLException {
				ran = (ArrayList<Sejourner>) new ServiceBP().AfficherHotel();
				return ran;
			}
		};
		task.setOnSucceeded((WorkerStateEvent e) -> {

			nomBP.setCellValueFactory(new PropertyValueFactory<>("nom"));
			photoBP.setCellValueFactory(new PropertyValueFactory<>("photo"));
			descriptionBP.setCellValueFactory(new PropertyValueFactory<>("description"));
			adresseBP.setCellValueFactory(new PropertyValueFactory<>("adresse"));
			villeBP.setCellValueFactory(new PropertyValueFactory<>("ville"));
			//nbrChambreBP.setCellFactory(new PropertyValueFactory<>("nbreChambreDispo"));
			//prixNuitBP.setCellFactory(new PropertyValueFactory<>("prixNuit"));
			tableView.setItems(FXCollections.observableArrayList(task.getValue()));
		});
		task.setOnFailed(e -> {
			afficherBP();
		});
		Thread th = new Thread(task);
		th.start();
	}

	@FXML
	private void modifierBP(ActionEvent event) throws SQLException, IOException {
		ServiceBP p = new ServiceBP();

		String i = idBP.getText();
		int b = Integer.parseInt(i);

		String j = nbrChambreText.getText();
		int nbrChambre = Integer.parseInt(j);
		
		String k = prixNuitText.getText();
		int prixNuit = Integer.parseInt(k);
		
		String l = valide.getText();
		int valide = Integer.parseInt(l);
		
		 //boolean isnotAlphDesc = TextFieldValidation.textalphabet(nomText, valide2, "Pas de numeros ");
		 //boolean isnotAlphDesc2 = TextFieldValidation.textalphabet(descriptionText, valide3, "Pas de numeros ");
		 //boolean isnotAlphDesc3 = TextFieldValidation.textalphabet(adresseText, valide4, "Pas de numeros ");
		 //boolean isnotAlphDesc4 = TextFieldValidation.textalphabet(villeText, valide5, "Pas de numeros ");
		 //boolean isnotAlphDesc6 = TextFieldValidation.texNum(nbrChambreText, valide6, "numéros obligatoires ");
		 //boolean isnotAlphDesc5 = TextFieldValidation.texNum(prixNuitText, valide1, "numéros obligatoires ");

		
		Sejourner pa = new Sejourner(nomText.getText(), imageTelecharger.getText(), descriptionText.getText(), adresseText.getText(), villeText.getText(),prixNuit,nbrChambre,valide,email.getText());
		//  System.out.println("hhhhhhhh");
		p.ModifierBP(pa, b);
		Image o = new Image("/Images/Tick.png");
		Notification.notificationDeModification(event, o);
		Parent page = FXMLLoader.load(getClass().getResource("ModifierBP.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
		System.out.println("hhhhhhhh");
		//afficherReservation();
		afficherBP();

	}

	@FXML
	private void browse(ActionEvent event) throws FileNotFoundException, IOException {
		FileChooser fc = new FileChooser();
		fc.getExtensionFilters().addAll(
				new FileChooser.ExtensionFilter("Images", "*.jpg", "*.png")
		);
		selectedfile = fc.showOpenDialog(null);
		if (selectedfile != null) {
			System.out.println(selectedfile.getName());
			FileInputStream inp = new FileInputStream(selectedfile.getPath());
			System.out.println(selectedfile.getName());
			ImageView a = new ImageView(new Image(inp));
			a.setFitHeight(54);
			a.setFitWidth(62);
			//paneImage.getChildren().add(a);
			path_img = selectedfile.getAbsolutePath();

			if (selectedfile.isFile()) {
				u.upload(selectedfile);
			}
			System.out.println(selectedfile.getCanonicalPath());
			//imgView.setImage(new Image("file://"+selectedfile.getCanonicalPath()));
			imageTelecharger.setText(selectedfile.getAbsolutePath());
		} else {
			System.out.println("FICHIER erroné");
		}
	}

	@FXML
	private void supprimerBP(ActionEvent event) throws SQLException, IOException {
		 ServiceBP s = new ServiceBP();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez-vous effacer cet hôtel ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
        String b = idBP.getText();
        int r=Integer.parseInt(b);
        s.SupprimerBP(r);
		Image o = new Image("/Images/Tick.png");
		Notification.notificationDeSuppression(event, o);
		Parent page = FXMLLoader.load(getClass().getResource("ModifierBP.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        afficherBP();
		System.out.println(r);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();
        }
		
	}

	@FXML
	private void CLicked(MouseEvent event) throws IOException {
		modifierBP.setDisable(false);
		supprimerBP.setDisable(false);
		
		Sejourner bp = (Sejourner) tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
			System.out.println(bp.getEmailPersonne());
		// id_text.setText(ran.getType());
		ServiceBP p = new ServiceBP();
		
		String idBP1 = Integer.toString(bp.getIdBonPlan());
		idBP.setText(idBP1);
		nomText.setText(bp.getNom());
		System.out.println("http://localhost/Images/" + bp.getPhoto());
		imgView.setImage(new Image("file://"+bp.getPhoto()));
		//imgView.setImage(a1);
		descriptionText.setText(bp.getDescription());
		adresseText.setText(bp.getAdresse());
		villeText.setText(bp.getVille());
		String s = Integer.toString(bp.getNbreChambreDispo());
		nbrChambreText.setText(s);
		String d = Integer.toString(bp.getPrixnuit());
		prixNuitText.setText(d);
		String val=Integer.toString(bp.getValide());
		valide.setText(val);
		if (!email.getText().equals(bp.getEmailPersonne()))
		{
			modifierBP.setDisable(true);
			supprimerBP.setDisable(true);
		}

	}
	@FXML
	private void retour(ActionEvent event) throws IOException {
        
        
		FXMLLoader loader = new FXMLLoader(getClass().getResource("MenuChoix.fxml"));
        Parent root = loader.load();
        MenuChoixController frc = loader.getController();
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
