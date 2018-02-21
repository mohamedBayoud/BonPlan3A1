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
import Entities.Promotion;
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
public class ServicePromotion {

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServicePromotion() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    

    public void AjouterPromotion(Promotion E) throws SQLException {
        System.out.println("Veuillez Entrer les Donnees");

        String req = "INSERT INTO `promotion`(`IdPersonne`, `idPromotion`, `titre`, `description`, `lieu`, `image`, `date`, `prix`, `nbPlace`, `type`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
         pre.setInt(1, E.getIdPersonne());
        pre.setInt(2, E.getIdPromotion());
        pre.setString(3, E.getTitre());
        pre.setString(4, E.getDescription());
        pre.setString(5, E.getLieu());
        pre.setString(6, E.getImage());
        pre.setDate(7, E.getDate());
        pre.setFloat(8, E.getPrix());
        pre.setInt(9, E.getNbPlace());
        pre.setString(10, E.getType());
        pre.executeUpdate();
        System.out.println("Promotion  Ajoutée");

    }

    public void supprimerPromotion(int IdEvent) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String req = "DELETE from  promotion  WHERE IdPromotion =?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, IdEvent);
        pre.executeUpdate();
    }

    public void ModifierPromotion(Promotion E, int IdPromotion) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String req = "UPDATE `promotion` SET `IdPersonne`=?,`idPromotion`=?,`titre`=?,`description`=?,`lieu`=?,`image`=?,`date`=?,`prix`=?,`nbPlace`=?,`type`=?   WHERE IdEvent=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, E.getIdPersonne());
        pre.setInt(2, E.getIdPromotion());
        pre.setString(3, E.getTitre());
        pre.setString(4, E.getDescription());
        pre.setString(5, E.getLieu());
        pre.setString(6, E.getImage());
        pre.setDate(7, E.getDate());
        pre.setFloat(8, E.getPrix());
        pre.setInt(9, E.getNbPlace());
        pre.setString(10, E.getType());
        pre.setInt(11, IdPromotion);
        pre.executeUpdate();

        System.out.println("Promotion Modifie avec Succees");
    }

    public List<Promotion> AfficherPromotion() {

        List<Promotion> list = new ArrayList<>();
        try {
            String req = "SELECT * from promotion";
            ResultSet res;
            res = ste.executeQuery(req);
            while (res.next()) {
                Promotion v1 = new Promotion(res.getInt("idPromotion"), res.getInt("idPersonne"), res.getString("description"), res.getInt("nbPlace"), getString("titre"), res.getFloat("prix"), res.getString("lieu"), res.getString("image"), res.getString("type"), res.getDate("date"));
                list.add(v1);
              
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
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
        String req = "SELECT date from promotion ";
        res = ste.executeQuery(req);
        List<Date> dates = new ArrayList<>();
        while (res.next()) {
            dates.add(res.getDate("date"));
        }
        return dates;
    }
    public List<Promotion> chercher(Date d) throws SQLException {

        List<Promotion> list = new ArrayList<>();
        try {
            String req = "SELECT * from promotion where date ="+d+"";
            ResultSet res;
            res = ste.executeQuery(req);
            while (res.next()) {
                Promotion v1 = new Promotion(res.getInt("idPromotion"), res.getInt("idPersonne"), res.getString("description"), res.getInt("nbPlace"), getString("titre"), res.getFloat("prix"), res.getString("lieu"), res.getString("image"), res.getString("type"), res.getDate("date"));
                System.out.println(res.getString("lieu"));
                list.add(v1);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServicePromotion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
    

}
