/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class PromotionMenuController implements Initializable {

    @FXML
    private TextField role;
    @FXML
    private TextField id_client;
    @FXML
    private TextField email_client;
    @FXML
    private Button retour;
    @FXML
    private Button sedeconnecter;
    @FXML
    private Button afficher;
    @FXML
    private Button ajouter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void retour(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
        Parent root = loader.load();
        MenuController frc = loader.getController();
        retour.getScene().setRoot(root);
        frc.setRole(role.getText());
        frc.setEmail(email_client.getText());
        String e = id_client.getText();
        int r = Integer.parseInt(e);
        frc.setIDClient(r);
    }

    @FXML
    private void sedeconnecter(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
        Parent root = loader.load();
        LoginController frc = loader.getController();
        sedeconnecter.getScene().setRoot(root);
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
    private void afficher(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Affichage.fxml"));
        Parent root = loader.load();

        AffichageController frc = loader.getController();
        afficher.getScene().setRoot(root);

        if (this.role.getText().equals("membre")) {
            frc.getModifierPromotion().setVisible(false);
            frc.getSupprimePromotion().setVisible(false);
            frc.getImagebrowse().setVisible(false);
        } else if (this.role.getText().equals("bonplaneur")) {
            frc.getModifierPromotion().setVisible(true);
            frc.getSupprimePromotion().setVisible(true);
            frc.getImagebrowse().setVisible(true);

        }

    }

    @FXML
    private void ajouter(ActionEvent event) throws IOException {
      FXMLLoader loader = new FXMLLoader(getClass().getResource("Acceuil.fxml"));
            Parent root = loader.load();
            AcceuilController frc = loader.getController();
            ajouter.getScene().setRoot(root);
        
    }

}
