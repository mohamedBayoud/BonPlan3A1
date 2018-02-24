/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.BP;
import Entities.Culinaire;
import Entities.Sejourner;
import Services.ServiceBP;
import Services.Upload;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class AjouterBP2Controller implements Initializable {

 
    @FXML
    private ImageView AjouterImg;
    @FXML
    private TextField imageTelecharger;
    File selectedfile;
    String path_img;
    Upload u = new Upload();
    private Pane paneImage;
    public static File file ;
    @FXML
    private TextField nbrPlace;
    @FXML
    private Button ajoutBp2;
    @FXML
    private TextField nomText2;
    @FXML
    private TextField villeText2;
    @FXML
    private TextField adresseText2;
    @FXML
    private TextArea descriptionText2;
    @FXML
    private Button browse;
	@FXML
	private Button retour;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
     private void AjoutBP2(ActionEvent event) throws SQLException, IOException  {
        ServiceBP sv = new ServiceBP();
        if ((nomText2.getText().isEmpty())&&(imageTelecharger.getText().isEmpty())&&(descriptionText2.getText().isEmpty())&&(villeText2.getText().isEmpty())&&(adresseText2.getText().isEmpty())&&(nbrPlace.getText().isEmpty()))
		{
			Image o = new Image("/Images/Cross.png");
			Notification.notificationVide(event, o);
		}
		else
		{
		String i = nbrPlace.getText();
        int nbrPlace = Integer.parseInt(i);
        
        BP s = new Culinaire(nomText2.getText(),imageTelecharger.getText(),descriptionText2.getText(),adresseText2.getText(),villeText2.getText(),nbrPlace);
        sv.AjouterBP(s);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Succes1.fxml"));
        Parent root =  loader.load();
        Succes1Controller frc = loader.getController();
        //frc.setSuccesText("Bon plan : "+nomText.getText()+" a été ajouté avec succées !");
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
      
                
    }   } 

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
	private void retour(ActionEvent event) throws IOException {
        
         Parent page = FXMLLoader.load(getClass().getResource("MenuChoix1.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    
    
}

