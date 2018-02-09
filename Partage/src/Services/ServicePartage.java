/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import ConnexionBd.DataSource;
import Entites.Partage;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Omar
 */
public class ServicePartage {

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServicePartage() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePartage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AjouterAvis(Partage P) throws SQLException {
        System.out.println("Veuillez Entrer les Donnees");
        String req = "INSERT INTO Partage (Avis,CommentaireAvis,Note) VALUES (?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, P.getAvis());
        pre.setString(2, P.getCommentaireAvis());
        pre.setInt(3, P.getNote());

        pre.executeUpdate();
        System.out.println("Avis Ajoutée");

    }

    public void AjouterAvis2() throws SQLException {

        System.out.println("Veuillez Entrer les Donnees");

        Scanner sc = new Scanner(System.in);

        String Avis = sc.nextLine();
        String CommentaireAvis = sc.nextLine();
        int Note = sc.nextInt();

        String req = "INSERT INTO Partage(Avis,CommentaireAvis,Note) VALUES ('" + Avis + "','" + CommentaireAvis + "','" + Note + "')";
        Statement ste = con.prepareStatement(req);
        ste.executeUpdate(req);

        System.out.println("Avis Ajoutée");
    }

    public void supprimerAvis() throws SQLException {
        Scanner sc = new Scanner(System.in);

        String Note = sc.nextLine();
        String req = "DELETE FROM Partage where ('" + Note + "') ";
        ste.executeUpdate(req);
        System.out.println("Avis Supprimeé avec Succees");
    }

    public void ModifierAvis() throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Donner l'id pour modifier");
        int idPartage = sc.nextInt();
        System.out.println("Entrer les donnees pour modifier");
        sc.nextLine();
        String Avis = sc.nextLine();
        String CommentaireAvis = sc.nextLine();
        int Note = sc.nextInt();

        // String req = "UPDATE Partage set ('"+Avis+"','"+CommentaireAvis+"','"+Note+"') where ('"+idPartage+"') ";
        String req = "UPDATE `partage` SET `avis`='" + Avis + "',`commentaireAvis`='" + CommentaireAvis + "',`Note`='" + Note + "' WHERE idPartage='" + idPartage + "'";
        ste.executeUpdate(req);
        System.out.println("Avis Modifie avec Succees");
    }

    public void ConsulterAvis() throws SQLException {
        ResultSet res;
        String req = "SELECT * from Partage";
        res = ste.executeQuery(req);
        while (res.next()) {
            System.out.println(res.getInt("IdPartage"));
            System.out.println(res.getString("Avis"));
            System.out.println(res.getString("CommentaireAvis"));
            System.out.println(res.getInt("Note"));

        }

    }
}
