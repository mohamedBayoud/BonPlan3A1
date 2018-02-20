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
public class AjouterBPController implements Initializable {

    @FXML
    private TextArea descriptionText;
    @FXML
    private TextField adresseText;
    @FXML
    private TextField villeText;
    private TextField photoText;
    @FXML
    private TextField nomText;
    @FXML
    private TextField nbrChambreText;
    @FXML
    private TextField prixNuitText;
    @FXML
    private ImageView AjouterImg;
    @FXML
    private Button ajoutBp;
    @FXML
    private TextField imageTelecharger;
    @FXML
    private Button browse;
    File selectedfile;
    String path_img;
    Upload u = new Upload();
    private Pane paneImage;
    public static File file ;
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
    private void AjoutBP(ActionEvent event) throws SQLException, IOException  {
        ServiceBP sv = new ServiceBP();
        if ((nomText.getText().isEmpty())&&(imageTelecharger.getText().isEmpty())&&(descriptionText.getText().isEmpty())&&(villeText.getText().isEmpty())&&(adresseText.getText().isEmpty())&&(nbrChambreText.getText().isEmpty())&&(prixNuitText.getText().isEmpty()))
		{
			Image o = new Image("/Images/Cross.png");
			Notification.notificationVide(event, o);
		}
		else
		{
		String i = nbrChambreText.getText();
        int nbreChambre = Integer.parseInt(i);
        String j = prixNuitText.getText();
        int prixNuit = Integer.parseInt(j);
        BP s = new Sejourner (nomText.getText(),imageTelecharger.getText(),descriptionText.getText(),adresseText.getText(),villeText.getText(),nbreChambre,prixNuit);
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
        
         Parent page = FXMLLoader.load(getClass().getResource("MenuChoix.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    
}

