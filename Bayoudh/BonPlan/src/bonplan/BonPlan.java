/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bonplan;

import Entites.Evenement;
import Services.ServiceEvent;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus Pc
 */
public class BonPlan {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Evenement E1;
        E1 = new Evenement(1882, 1, "MC8angou", 2, "kdkdd", 12, "bjkn", "bjk", "uikl", new Date(2018 - 1900, 2 - 1, 8), new Date(2018 - 1900, 2 - 1, 8), new Date(2018 - 1900, 2 - 1, 8));
        Evenement E2;
        E2 = new Evenement(182, 2, "llll", 2, "hk", 12, "pooiuyyy", "tyddytt", "yftiti", new Date(2018 - 1900, 2 - 1, 8), new Date(2018 - 1900, 2 - 1, 8), new Date(2018 - 1900, 2 - 1, 8));
        Evenement E3;

        int c;
        Scanner in = new Scanner(System.in);
        ServiceEvent sv = new ServiceEvent();
        do {

            do {
                System.out.println("*****************************");
                System.out.println("1 - Ajouter Un Event");
                System.out.println("2 - Supprimer Un Event");
                System.out.println("3 - Consulter un Event");
                System.out.println("4 - Modifier un Event");
                System.out.println("0 - Quitter");
                System.out.println("*****************************");
                c = in.nextInt();
            } while (c < 0 || c > 5);
            if (c == 0) {
                return;
            } else if (c == 1) {
                try {
                    sv.AjouterEvent(E1);
                    sv.AjouterEvent(E2);

                } catch (SQLException ex) {
                    Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c == 2) {
                try {
                    sv.supprimerEvent(1882);
                } catch (SQLException ex) {
                    Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c == 3) {
             /*   try {
                    sv.AfficherEvenement();
                } catch (SQLException ex) {
                    Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                }*/
            } else {
                try {

                    sv.ModifierEvent(new Evenement(15855582, 2, "sport", 2, "lezem", 12, "okk", "edddd", "ferfer", new Date(2018 - 1900, 2 - 1, 8), new Date(2018 - 1900, 2 - 1, 8), new Date(2018 - 1900, 2 - 1, 8)),1882);
                } catch (SQLException ex) {
                    Logger.getLogger(Evenement.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (c != 0);
    }
    
}
