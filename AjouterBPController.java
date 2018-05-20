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
import Services.Upload;
import com.sun.javafx.tk.Toolkit;
import static com.sun.javafx.tk.Toolkit.getToolkit;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.swing.JFormattedTextField;

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
	@FXML
	private TextField email;
	@FXML
	private TextField role;
	@FXML
	private Label valide1;
	@FXML
	private Label valide6;
	@FXML
	private Label valide5;
	@FXML
	private Label valide4;
	@FXML
	private Label valide3;
	@FXML
	private Label valide2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

@FXML
    private void AjoutBP(ActionEvent event) throws SQLException, IOException  {
		
		/* boolean isnotAlphDesc = TextFieldValidation.textalphabet(nomText, valide1, "Pas de numeros ");
		 //boolean isnotAlphDesc2 = TextFieldValidation.textalphabet(descriptionText, valide2, "Pas de numeros ");
		 //boolean isnotAlphDesc3 = TextFieldValidation.textalphabet(adresseText, valide3, "Pas de numeros ");
		 boolean isnotAlphDesc4 = TextFieldValidation.textalphabet(villeText, valide4, "Pas de numeros ");
		 boolean isnotAlphDesc5 = TextFieldValidation.texNum(nbrChambreText, valide5, "numéros obligatoires ");
		 boolean isnotAlphDesc6 = TextFieldValidation.texNum(prixNuitText, valide6, "numéros obligatoires ");*/
        ServiceBP sv = new ServiceBP();
        if ((nomText.getText().isEmpty())&&(imageTelecharger.getText().isEmpty())&&(descriptionText.getText().isEmpty())&&(villeText.getText().isEmpty())&&(adresseText.getText().isEmpty())&&(nbrChambreText.getText().isEmpty())&&(prixNuitText.getText().isEmpty()))
		{
			Image o = new Image("/Images/Cross.png");
			Notification.notificationVide(event, o);
		}
		else if ((nomText.getText().isEmpty())||(imageTelecharger.getText().isEmpty())||(descriptionText.getText().isEmpty())||(villeText.getText().isEmpty())||(adresseText.getText().isEmpty())||(nbrChambreText.getText().isEmpty())||(prixNuitText.getText().isEmpty()))
		{
			Image o = new Image("/Images/Cross.png");
			Notification.notificationErreur(event, o);
		}
		else
		{
		String i = nbrChambreText.getText();
        int nbreChambre = Integer.parseInt(i);
		
        String j = prixNuitText.getText();
        int prixNuit = Integer.parseInt(j);
		
        BP s = new Sejourner (nomText.getText(),imageTelecharger.getText(),descriptionText.getText(),adresseText.getText(),villeText.getText(),prixNuit,nbreChambre,0,email.getText());
        sv.AjouterBP(s);
        List<Notif> list1 = new ArrayList<>();
        ServiceNotif sv1 = new ServiceNotif();
        list1=sv1.chercheville(s.getVille());
        if (!list1.isEmpty()){
            for(int p = 0; p <  list1.size(); p++){
             Mailer.sendMail(list1.get(p).getEmail_client(), "BonPlan ","ya un bon plan dans la ville que vous cherchiez : "+list1.get(p).getVille());
        }}
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

