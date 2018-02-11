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
import java.util.ArrayList;
import java.util.List;
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
        String req = "INSERT INTO Partage (Avis,CommentaireAvis,NoteCuisine,NoteRapport,NoteService,NoteAmbiance) VALUES (?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setString(1, P.getAvis());
        pre.setString(2, P.getCommentaireAvis());
        pre.setInt(3, P.getNoteCuisine());
        pre.setInt(4, P.getNoteRapport());
        pre.setInt(5, P.getNoteService());
        pre.setInt(6, P.getNoteAmbiance());

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

    public void supprimerAvis(Partage P) throws SQLException {

        String req = "DELETE FROM `partage` WHERE idPartage='"+P.getIdPartage()+"'";
        ste.executeUpdate(req);
        System.out.println("Avis Supprimeé avec Succees");
    }

    public void ModifierAvis(Partage P) throws SQLException {
     

        // String req = "UPDATE Partage set ('"+Avis+"','"+CommentaireAvis+"','"+Note+"') where ('"+idPartage+"') ";
        String req = "UPDATE partage SET Avis='"+P.getAvis()+"',commentaireAvis='"+P.getCommentaireAvis()+"',NoteCuisine='"+P.getNoteCuisine()+"',NoteRapport='"+P.getNoteRapport()+"',NoteService='"+P.getNoteService()+"',NoteAmbiance='"+P.getNoteAmbiance()+"' WHERE idPartage=1";
              // PreparedStatement pre = con.prepareStatement(req);

        //pre.setString(1, P.getAvis());
      // pre.setString(1, P.getCommentaireAvis());

        //pre.setInt(3, P.getNoteCuisine());
        //pre.setInt(4, P.getNoteRapport());
        //pre.setInt(5, P.getNoteService());
        //pre.setInt(6, P.getNoteAmbiance());
        ste.executeUpdate(req);
        System.out.println("Avis Modifie avec Succees");
    }

    public List<Partage> AfficherCommentaire() throws SQLException{
         List<Partage> list = new ArrayList<>();
            String req = "SELECT * from Partage ";
            ResultSet res;
            res = ste.executeQuery(req);
            while (res.next()) {
                Partage P1 = new Partage(res.getString("commentaireAvis"));
                list.add(P1);
            
        }
        return list;

    }
    

}