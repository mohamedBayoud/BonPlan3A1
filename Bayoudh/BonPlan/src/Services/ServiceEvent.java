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
import static com.mysql.jdbc.Messages.getString;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
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

        String req = "INSERT INTO `evenement`(`IdPersonne`, `idEvent`, `titre`, `description`, `lieu`, `image`, `date`, `prix`, `nbPlace`, `type`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
         pre.setInt(1, E.getIdPersonne());
        pre.setInt(2, E.getIdEvent());
        pre.setString(3, E.getTitre());
        pre.setString(4, E.getDescription());
        pre.setString(5, E.getLieu());
        pre.setString(6, E.getImage());
        pre.setDate(7, E.getDate());
        pre.setFloat(8, E.getPrix());
        pre.setInt(9, E.getNbPlace());
        pre.setString(10, E.getType());
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

    public void ModifierEvent(Evenement E, int IdEvent) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String req = "UPDATE `evenement` SET `IdPersonne`=?,`idEvent`=?,`titre`=?,`description`=?,`lieu`=?,`image`=?,`date`=?,`prix`=?,`nbPlace`=?,`type`=?   WHERE IdEvent=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, E.getIdPersonne());
        pre.setInt(2, E.getIdEvent());
        pre.setString(3, E.getTitre());
        pre.setString(4, E.getDescription());
        pre.setString(5, E.getLieu());
        pre.setString(6, E.getImage());
        pre.setDate(7, E.getDate());
        pre.setFloat(8, E.getPrix());
        pre.setInt(9, E.getNbPlace());
        pre.setString(10, E.getType());
        pre.setInt(11, IdEvent);
        pre.executeUpdate();

        System.out.println("Event Modifie avec Succees");
    }

    public List<Evenement> AfficherEvenement() {

        List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * from evenement";
            ResultSet res;
            res = ste.executeQuery(req);
            while (res.next()) {
                Evenement v1 = new Evenement(res.getInt("idEvent"), res.getInt("idPersonne"), res.getString("description"), res.getInt("nbPlace"), getString("titre"), res.getFloat("prix"), res.getString("lieu"), res.getString("image"), res.getString("type"), res.getDate("date"));
                list.add(v1);
              
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }

   /* public void SelectDate() throws SQLException {
        ResultSet res;
        String req = "SELECT * from evenement where date = ? ";
        res = ste.executeQuery(req);
        while (res.next()) {
            System.out.println(res.getDate("date"));
        }
    }*/

    /**
     *
     * @return @throws SQLException
     */
    public List<Date> AfficherDate() throws SQLException {

        ResultSet res;
        String req = "SELECT date from evenement ";
        res = ste.executeQuery(req);
        List<Date> dates = new ArrayList<>();
        while (res.next()) {
            dates.add(res.getDate("date"));
        }
        return dates;
    }
    public List<Evenement> chercher(Date d) throws SQLException {

        List<Evenement> list = new ArrayList<>();
        try {
            String req = "SELECT * from evenement where date ="+d+"";
            ResultSet res;
            res = ste.executeQuery(req);
            while (res.next()) {
                Evenement v1 = new Evenement(res.getInt("idEvent"), res.getInt("idPersonne"), res.getString("description"), res.getInt("nbPlace"), getString("titre"), res.getFloat("prix"), res.getString("lieu"), res.getString("image"), res.getString("type"), res.getDate("date"));
                System.out.println(res.getString("lieu"));
                list.add(v1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    

}
