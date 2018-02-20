/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI; 
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.sql.*;
import javafx.scene.*;
import Entities.Promotion;
import static GUI.AcceuilController.file;
import Services.ServicePromotion;
import Services.Upload;
//import com.google.zxing.qrcode.QRCodeWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import static java.lang.System.out;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.stream.FileCacheImageOutputStream;
import javax.imageio.stream.FileImageOutputStream;
//import net.glxn.qrgen.image.ImageType;


/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class AcceuilController implements Initializable {

    Upload u = new Upload();
    @FXML
    private TextField idPersonne;
    @FXML
    private DatePicker date;
    
    @FXML
    private TextField idPromotion;
    @FXML
    private TextField titre;
    @FXML
    private TextArea description;
    @FXML
    private TextField lieu;
    @FXML
    private TextField prix;
    @FXML
    private TextField nbplace;
    @FXML
    private TextField type;
    @FXML
    private Button updatePromotion;
    @FXML
    private Button imagebrowse;
    public static File file;
    @FXML
    private ListView imagetelecharger;
    @FXML
    private Button AjouterPromotion;

    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     * @param args
     * @throws java.io.IOException
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       
    }

    @FXML
    private void AjouterPromotion(ActionEvent event) throws IOException, SQLException {
        ServicePromotion sv = new ServicePromotion();
              
      //  QRCodeWriter qrcodeWriter;
        
        
          String e = idPromotion.getText();
        int r = Integer.parseInt(e);

        String t = idPersonne.getText();
        int y = Integer.parseInt(t);
                u.upload(selectedfile);
        String i = nbplace.getText();
        int b = Integer.parseInt(i);
        String j = prix.getText();
        float d = Float.parseFloat(j);
       

         Date  d1 = java.sql.Date.valueOf(date.getValue());
      
         Date d2=java.sql.Date.valueOf(LocalDate.now());
        
        Promotion e1;
        //e1 = new Promotion(y, r, t, b, t, d, e, t);
       e1 = new Promotion(r,y,description.getText(),b,titre.getText(),d,lieu.getText(),type.getText(),d1);
        e1.setImage(selectedfile.getName());
        sv.AjouterPromotion(e1);
        if(d1.before(d2))
        {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERREUR");
            alert.setHeaderText("look, an error Dialog");
            alert.setContentText("ouups , there was an error !");
            alert.showAndWait();

        }
        }
      
        
    

    @FXML
    private void updatePromotion(ActionEvent event) throws IOException {
    
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
            Parent root = loader.load();

            AffichageController frc = loader.getController();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
    }
    File selectedfile;
    String path_img;

    @FXML
    private void browse(ActionEvent event) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image", "*.jpg", "*.png")
        );
        selectedfile = fc.showOpenDialog(null);
        if (selectedfile != null) {
            System.out.println("aaaaaaaaaa");
            imagetelecharger.getItems().add(selectedfile.getName());
            path_img = selectedfile.getAbsolutePath();
            System.out.println("sssssssssssssssss");
        } else {
            System.out.println("FICHIER erroné");
        }

    }
}
