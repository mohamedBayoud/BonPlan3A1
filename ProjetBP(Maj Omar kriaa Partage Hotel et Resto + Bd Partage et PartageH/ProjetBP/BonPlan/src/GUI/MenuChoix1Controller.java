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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author senda
 */
public class MenuChoix1Controller implements Initializable {

    @FXML
    private Button modifierR;
    @FXML
    private Button ajouterR;
	@FXML
	private Button retour;
	@FXML
	private Button logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void modifierR(ActionEvent event) throws IOException {
         Parent page = FXMLLoader.load(getClass().getResource("ModifierBP2.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
        
    }

    @FXML
    private void ajouterR(ActionEvent event) throws IOException {
        
        Parent page = FXMLLoader.load(getClass().getResource("AjouterBP2.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
	@FXML
	private void retour(ActionEvent event) throws IOException {
        
         Parent page = FXMLLoader.load(getClass().getResource("MenuChoix0.fxml"));
        Scene scene = new Scene(page);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

	@FXML
	private void logout(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            Parent root =  loader.load();
            LoginController frc = loader.getController();
            logout.getScene().setRoot(root);
	}
    
}
