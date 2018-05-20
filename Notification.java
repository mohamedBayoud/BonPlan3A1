/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;



/**
 *
 * @author Aycha
 */
public class Notification {
      public static void notificationDeModification(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("BON PLAN")
                .text(" Votre blon plan a été modifié .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
	  
	  public static void notificationDeqrc(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("BON PLAN")
                .text(" Votre QRcode dans votre bureau .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
	  
	
	 public static void notificationDeSuppression(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("BON PLAN")
                .text(" Votre blon plan a été supprimé .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
    public static void notificationDeConfirmation(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("Evénements")
                .text(" Creation faite")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
    
    
    
    public  static void notificationErreur(ActionEvent event,Image i){
          Notifications notificationBuilder = Notifications.create()
                .title("Champs Manquant Ou Dates invalides")
                .text(" Veuillez Verifier vos champs")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
       
       } 
        
        public  static void notificationVide(ActionEvent event,Image i){
          Notifications notificationBuilder = Notifications.create()
                .title("Vos champs Sont vides")
                .text(" Veuillez Remplir Vos champs !!!")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
       
       }
		public static void notificationDeValidation(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("BON PLAN")
                .text(" Un nouveau bon plan a été ajouté .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
     public static void notificationValidation(ActionEvent event,Image i,int x){
        
         Notifications notificationBuilder = Notifications.create()
                .title(" Transport")
                .text(" vous avez "+x+" covoiturage à valider")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
	 public static void notificationValidation1(ActionEvent event,Image i,int x){
        
         Notifications notificationBuilder = Notifications.create()
                .title(" BON PLAN")
                .text(" vous avez "+x+" bon plan à valider !")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent event) {
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
   
		public static void notificationDeSuppressionTransport(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("Transport")
                .text(" Suppression effectuée .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
    public static void notificationDeModif(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title(" Transport")
                .text(" Modification effectuée avec succés")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
           
        public  static void notificationVide1(ActionEvent event,Image i){
          Notifications notificationBuilder = Notifications.create()
                .title(" Vos champs Sont vides")
                .text(" Veuillez Remplir Vos champs !!!")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
       
       }
		public  static void notificationErreur1(ActionEvent event,Image i){
          Notifications notificationBuilder = Notifications.create()
                .title(" Champ Manquant ")
                .text(" Veuillez remplir tous vos champs")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
       
       }
		 public static void notificationDajout(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title(" Transport")
                .text(" Ajout effectué avec succés")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
                   public  static void notificationcomplet(ActionEvent event,Image i){
          Notifications notificationBuilder = Notifications.create()
                .title("Evenement ")
                .text(" Evenement Complet ")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
       
       }  
		public static void notificationChercher(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("BON PLAN")
                .text(" Aucun Bon Plan n'existe dans cette ville , vous recevrez un mail en cas d'ajout .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }
		public static void notificationInexistant(ActionEvent event,Image i){
        
         Notifications notificationBuilder = Notifications.create()
                .title("BON PLAN")
                .text(" Cette ville ne contient pas de bon plan pour le moment .")
                 
                .graphic(new ImageView(i))
                .hideAfter(Duration.seconds(5))
                .position(Pos.BASELINE_LEFT)
                .onAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
            
                
            
            }
        });
           notificationBuilder.darkStyle();
           notificationBuilder.show();
    }		   

}
