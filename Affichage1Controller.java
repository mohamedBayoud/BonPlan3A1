/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Promotion;
import Services.ServicePromotion;
import Services.Upload;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.stream.FileImageOutputStream;
import net.glxn.qrgen.QRCode;
import net.glxn.qrgen.image.ImageType;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class Affichage1Controller implements Initializable {

    File selectedfile;
    String path_img;
    Upload u = new Upload();
    public ArrayList<Promotion> ran;
    private Image image;
   
    @FXML
    private TableColumn<?, ?> titreC;
    @FXML
    private TableColumn<?, ?> descriptionC;
    @FXML
    private TableColumn<?, ?> lieuC;
    @FXML
    private TableColumn<?, ?> imageC;
    @FXML
    private TableColumn<?, ?> dateC;
    @FXML
    private TableColumn<?, ?> prixC;
   
    
    private ObservableList<Promotion> listeE;
    @FXML
    private TableView<Promotion> tableView;
    private ImageView img_view;
    @FXML
    private TextArea description_txt;
    @FXML
    private TextField lieu_txt;
    private TextField date_txt;
    @FXML
    private Button supprimePromotion;
    @FXML
    private TextField titre;
    @FXML
    private Button imagebrowse;
    @FXML
    private TextField prix;
    @FXML
    private ListView<?> imagetelecharger;
    @FXML
    private Pane paneImage;
    @FXML
    private Button modifierPromotion;
    @FXML
    private TextField id_Text;
    @FXML
    private TableColumn<?, ?> id_TextC;
    @FXML
    private DatePicker dateFin;
    @FXML
    private TextField id_client;
    @FXML
    private TextField role;
    @FXML
    private TextField email_client;
    @FXML
    private Button retour;
    @FXML
    private Button stat;
	@FXML
	private ImageView qrcode;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

         afficherPromotion();
    }

    public void afficherPromotion() {

        Task<ArrayList<Promotion>> task = new Task() {

            @Override
            protected Object call() {
                ran = (ArrayList<Promotion>) new ServicePromotion().AfficherPromotion();
                return ran;
            }
        };
        task.setOnSucceeded(e -> {

                        id_TextC.setCellValueFactory(new PropertyValueFactory<>("id"));

            titreC.setCellValueFactory(new PropertyValueFactory<>("titre"));
            descriptionC.setCellValueFactory(new PropertyValueFactory<>("description"));
            lieuC.setCellValueFactory(new PropertyValueFactory<>("lieu"));
            imageC.setCellValueFactory(new PropertyValueFactory<>("image"));
            dateC.setCellValueFactory(new PropertyValueFactory<>("dateFin"));
            prixC.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tableView.setItems(FXCollections.observableArrayList(task.getValue()));
        });
        task.setOnFailed(e -> {
            afficherPromotion();
        });
        Thread th = new Thread(task);
        th.start();
    }

    @FXML
    private void CLicked(MouseEvent event) throws IOException {
		 Image l = new Image("/Images/Cross.png");
          System.out.println("Gkkkkkk");
        Promotion ev = tableView.getItems().get(tableView.getSelectionModel().getSelectedIndex());
         id_Text.setText(Integer.toString(ev.getId()));
      
                titre.setText(ev.getTitre());

        description_txt.setText(ev.getDescription());
        lieu_txt.setText(ev.getLieu());
        Image a1 = new Image("http://localhost/Image/" + ev.getImage());
        System.out.println("------------------------------");
        System.out.println("http://localhost/Image/" + ev.getImage());
		Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Qrc code");
        alert.setHeaderText("cheack your desktop");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String e = id_Text.getText();
            
           
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Qrcode creer avec succes");
            alert.show();

            afficherPromotion();
        }

			//img_view.setImage(a1);
                      String date = ev.getDate().toString();
 
//        date_txt.setText(date);
       
		
	
		//qrcode.setImage(new Image("/Users/senda/Desktop/ProjetBP Einsteam/BonPlanX/test66.jpg"));
        

       prix.setText(Float.toString(ev.getPrix()));
        
    }

    @FXML
    private void SupprimerPromotion(ActionEvent event) throws SQLException {
        Date  d1 = java.sql.Date.valueOf(dateFin.getValue());
         Date d2=java.sql.Date.valueOf(LocalDate.now());
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("voulez vous effacer cette promotion ?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            String e = id_Text.getText();
            int r = Integer.parseInt(e);
          
            ServicePromotion s = new ServicePromotion();
             
            s.supprimerPromotion(r);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Delete succes!");
            alert.show();

            afficherPromotion();
        }
    }

    @FXML
    private void ModifierPromotion(ActionEvent event) throws SQLException, IOException {
    ServicePromotion sv = new ServicePromotion();
        
        String j = prix.getText();
        float d = Float.parseFloat(j);
        String e = id_Text.getText();
        int r = Integer.parseInt(e);
      
        
       Date  d1 = java.sql.Date.valueOf(dateFin.getValue());
      
        
        Promotion e1;
       e1 = new Promotion(description_txt.getText(),titre.getText(),d,lieu_txt.getText(),d1);
        e1.setImage(selectedfile.getName());
       
       if (e1.getImage().equals(selectedfile.getName()))
         sv.ModifierPromotion(e1, r);  
                   afficherPromotion();

      
    }

	public Button getStat() {
		return stat;
	}
  
  
    @FXML
    private void returnnbut(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("PromotionMenu.fxml"));
        Parent root = loader.load();
        PromotionMenuController frc = loader.getController();
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
        retour.getScene().setRoot(root);
    }

    @FXML
    private void browse(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println(selectedfile.getName());
            FileInputStream inp = new FileInputStream(selectedfile.getPath());
            System.out.println(selectedfile.getName());
            ImageView a = new ImageView(new Image(inp));
            a.setFitHeight(150);
            a.setFitWidth(100);

            paneImage.getChildren().add(a);

            path_img = selectedfile.getAbsolutePath();

            if (selectedfile.isFile()) {
                u.upload(selectedfile);
            }
            System.out.println("sssssssssssssssss");
        } else {
            System.out.println("FICHIER erroné");
        }

    }

    public File getSelectedfile() {
        return selectedfile;
    }

    public Button getSupprimePromotion() {
        return supprimePromotion;
    }

    public Button getImagebrowse() {
        return imagebrowse;
    }

    public Button getModifierPromotion() {
        return modifierPromotion;
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
    private void stat(ActionEvent event) throws IOException {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("statpromotion.fxml"));
        Parent root = loader.load();
        StatpromotionController frc = loader.getController();
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
        stat.getScene().setRoot(root);
    }

}
