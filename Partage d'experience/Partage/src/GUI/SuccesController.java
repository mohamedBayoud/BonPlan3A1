/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Omar
 */
public class SuccesController implements Initializable {

    @FXML
    private Label succesText;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public Label getSuccesText() {
        return succesText;
    }

    public void setSuccesText(String succesText) {
        this.succesText.setText(succesText); 
    }
    
}
