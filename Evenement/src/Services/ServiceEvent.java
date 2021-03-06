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
import Entites.Evenement;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Omar
 */
public class ServiceEvent {

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceEvent() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AjouterEvent(Evenement E) throws SQLException {
        System.out.println("Veuillez Entrer les Donnees");

        String req = "INSERT INTO `evenement`(`IdPersonne`, `idEvent`, `titre`, `description`, `lieu`, `image`, `date`, `dateDebut`, `dateFin`, `prix`, `nbPlace`, `type`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(2,E.getIdEvent());
        pre.setInt(1,E.getIdPersonne());
        pre.setString(3, E.getTitre());
        pre.setString(4, E.getDescription());
        pre.setString(5, E.getLieu());
        pre.setString(6, E.getImage());
        pre.setDate(7, E.getDate());
        pre.setDate(8, E.getDateDebut());
        pre.setDate(9, E.getDateFin());
        pre.setFloat(10, E.getPrix());
        pre.setInt(11, E.getNbPlace());
        pre.setString(12, E.getType());
        pre.executeUpdate();
        System.out.println("Evenement  Ajoutée");

    }

    public void supprimerEvent(int IdEvent) throws SQLException {
        Scanner sc = new Scanner(System.in);
       
           String req = "DELETE from  evenement  WHERE IdEvent =?";
          PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1, IdEvent);
            pre.executeUpdate();
    }

    public void ModifierEvent(Evenement E,int IdEvent) throws SQLException {
        Scanner sc = new Scanner(System.in);
      
     String req = "UPDATE `evenement` SET `IdPersonne`=?,IdEvent=?,`titre`=?,`description`=?,`lieu`=?,`image`=?,`date`=?,`dateDebut`=?,`dateFin`=?,`prix`=?,`nbPlace`=?,`type`=?  WHERE IdEvent=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,E.getIdPersonne());
        pre.setInt(2,E.getIdEvent());
        pre.setString(3, E.getTitre());
        pre.setString(4, E.getDescription());
        pre.setString(5, E.getLieu());
        pre.setString(6, E.getImage());
        pre.setDate(7, E.getDate());
        pre.setDate(8, E.getDateDebut());
        pre.setDate(9, E.getDateFin());
        pre.setFloat(10, E.getPrix());
        pre.setInt(11, E.getNbPlace());
        pre.setString(12, E.getType());
        pre.setInt(13,IdEvent);
        pre.executeUpdate();
      
        System.out.println("Event Modifie avec Succees");
    }

    public void ConsulterEvent() throws SQLException {
        ResultSet res;
        String req = "SELECT * from evenement";
        res = ste.executeQuery(req);
        while (res.next()) {

            System.out.println(res.getInt("idEvent"));
            System.out.println(res.getString("titre"));
            System.out.println(res.getString("description"));
            System.out.println(res.getString("lieu"));
            System.out.println(res.getString("image"));
            System.out.println(res.getDate("date"));
            System.out.println(res.getDate("dateDebut"));
            System.out.println(res.getDate("dateFin"));
            System.out.println(res.getFloat("prix"));
            System.out.println(res.getInt("nbPlace"));
            System.out.println(res.getString("type"));
            System.out.println("********************************");

        }

    }
}
