/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partage;

import Entites.Partage;
import Services.ServicePartage;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Omar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Partage P = new Partage("omar", "kriaa", 10);
        Partage P2 = new Partage();

        int c;
        Scanner in = new Scanner(System.in);
        ServicePartage sv = new ServicePartage();
        do {

            do {
                System.out.println("*****************************");
                System.out.println("1 - Ajouter Un Avis");
                System.out.println("2 - Supprimer Un Avis");
                System.out.println("3 - Consulter un Avis");
                System.out.println("4 - Modifier un Avis");
                System.out.println("0 - Quitter");
                System.out.println("*****************************");
                c = in.nextInt();
            } while (c < 0 || c > 5);
            if (c == 0) {
                return;
            } else if (c == 1) {
                try {
                    sv.AjouterAvis2();
                } catch (SQLException ex) {
                    Logger.getLogger(Partage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c == 2) {
                try {
                    sv.supprimerAvis();
                } catch (SQLException ex) {
                    Logger.getLogger(Partage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c == 3) {
                try {
                    sv.ConsulterAvis();
                } catch (SQLException ex) {
                    Logger.getLogger(Partage.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    sv.ModifierAvis();
                } catch (SQLException ex) {
                    Logger.getLogger(Partage.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (c != 0);
    }
}
