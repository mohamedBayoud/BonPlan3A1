/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package partage;

import Entites.User;
import Services.ServiceUser;
import java.time.LocalTime;
import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import static sun.net.www.http.HttpClient.New;

/**
 *
 * @author Omar
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
User U1 = new User(12,"Sam","mefteh", 12,25222555, new Date(2018 - 1900, 2 - 1, 8),"kk@jjj","membre","sam","sam");
        User U2;
        U2 = new User(3,"gatef","mefteh", 12,25222555, new Date(2018 - 1900, 2 - 1, 8),"kk@jjj","membre","sam","sam");
       

        int c;
        Scanner in = new Scanner(System.in);
        ServiceUser sv = new ServiceUser();
        do {

            do {
                System.out.println("*****************************");
                System.out.println("1 - Ajouter Un User");
                System.out.println("2 - Supprimer Un User");
                System.out.println("3 - Consulter un User");
                System.out.println("4 - Modifier un User");
                System.out.println("0 - Quitter");
                System.out.println("*****************************");
                c = in.nextInt();
            } while (c < 0 || c > 5);
            if (c == 0) {
                return;
            } else if (c == 1) {
                try {
                    sv.AjouterUser(U1);
                    sv.AjouterUser(U2);

                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c == 2) {
                try {
                    sv.supprimerUser(12);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (c == 3) {
                try {
                    sv.ConsulterUser();
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {

                    sv.ModifierUser(new User(7772,"Sam","mefteh", 12,25222555, new Date(2018 - 1900, 2 - 1, 8),"kk@jjj","membre","sam","sam"),3);
                } catch (SQLException ex) {
                    Logger.getLogger(User.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } while (c != 0);
    }
}
