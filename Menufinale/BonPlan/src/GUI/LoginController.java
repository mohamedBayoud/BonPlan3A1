/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.User;
import Services.ServiceUser;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Asus Pc
 */
public class LoginController implements Initializable {

    @FXML
    private TextField login;
    @FXML
    private PasswordField mdp;
    @FXML
    private Button inscrire;
    @FXML
    private Button connecter;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void inscrire(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Isncription.fxml"));
        Parent root = loader.load();
        IsncriptionController frc = loader.getController();
        inscrire.getScene().setRoot(root);
    }

    @FXML
    private void connecter(ActionEvent event) throws SQLException, IOException {
         List<User> list = new ArrayList<>();
        ServiceUser su = new ServiceUser();
        list = ServiceUser.ChercherUser();
        //System.out.println("frr");
        String e = login.getText();
        String e1 = mdp.getText();
                
        for(int i = 0; i <  list.size(); i++){
            
            if (list.get(i).getLogin().equals(e) &&list.get(i).getMdp().equals(e1)) 
            {
                
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
            Parent root =  loader.load();
            MenuController frc = loader.getController();
            frc.setRole(list.get(i).getRole());
            frc.setIDClient(list.get(i).getIdPersonne());
            frc.setEmail(list.get(i).getEmail());
            connecter.getScene().setRoot(root);     
        
            
            }}
        
    }
  
    
    
}
