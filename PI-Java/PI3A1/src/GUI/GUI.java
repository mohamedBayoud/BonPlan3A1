/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Entities.Voiture;
import Service.ServiceVoiture;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Amina
 */
public class GUI {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Voiture v = new Voiture("Juke", "Rouge");
        ServiceVoiture sv = new ServiceVoiture();
        try {
            sv.AjouterVoiture(v);
            // TODO code application logic here
        } catch (SQLException ex) {
            Logger.getLogger(GUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
