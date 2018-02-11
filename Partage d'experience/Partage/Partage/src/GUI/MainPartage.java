/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import java.awt.Image;
import java.io.IOException;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Amina
 */
public class MainPartage extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {

       Parent root = FXMLLoader.load(getClass().getResource("AjouterAvis.fxml"));

       Parent root2 = FXMLLoader.load(getClass().getResource("Succes.fxml"));

        Scene scene = new Scene(root, 600, 400);
        Scene scene2 = new Scene(root2, 540, 239);

          scene.getStylesheets().add("VillaDidon.jpg");
          scene2.getStylesheets().add("Logo.png");
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
