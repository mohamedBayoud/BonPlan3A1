/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import Entities.Reservation;
import ConnexionBd.DataSource;

import java.sql.Connection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maroua
 */
public class ServiceReservation {

    public static Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceReservation() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    public void AjouterReservation(Reservation r) throws SQLException {
        String req = "INSERT INTO reservation(type,DateEntree,DateSortie,NbrNuit,nbrChambre) VALUES(?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        //preparedStatement temchi selon les parametres contrairement à statement temchi m3a requetes en bloc
        pre.setString(1, r.getType());
        pre.setDate(2, r.getDateEntree());
        pre.setDate(3, r.getDateSortie());
        pre.setInt(4, r.getNbrNuit());
        pre.setInt(5, r.getNbrChambre());
        //pre.setInt(6, r.getNbrPersonnes());

        pre.executeUpdate();
        System.out.println("Réservation ajoutée");

    }

    public void SupprimerReservation(int idReservation) throws SQLException {
        Scanner sc = new Scanner(System.in);
        String req = "delete from reservation where idReservation=?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idReservation);
        pre.executeUpdate();

    }

    public List<Reservation> AfficherReservation() throws SQLException {
        PreparedStatement pre;
        List<Reservation> list = new ArrayList<>();
//        List<Personne> listp = new ArrayList<>();
//         List<Object> l = new ArrayList<>();

        String req = "SELECT idReservation,dateEntree,dateSortie,nbrNuit,type,nbrChambre from reservation order by idReservation desc limit 1";
        // String req ="SELECT reservation.idReservation,reservation.dateEntree,reservation.dateSortie,reservation.nbrPersonnes,reservation.type,personne.nom from reservation,personne where reservation.idPersonne=personne.idPersonne ";
        pre = con.prepareStatement(req);
        ResultSet result = pre.executeQuery();
        while (result.next()) {
            Reservation v1 = new Reservation(result.getInt("idReservation"), result.getInt("nbrNuit"), result.getDate("dateEntree"), result.getDate("dateSortie"), result.getString("type"), result.getInt("nbrChambre"));
            //Personne p1= new Personne(result.getString("nom"));
            list.add(v1);
            // listp.add(p1);
//            l.add(v1);
//            l.add(p1);
        }

        return list;

    }

    public void ModifierReservation(int idReservation, Reservation r) throws SQLException {

        String req = "update reservation set nbrNuit=?,dateEntree=?,dateSortie=?,type=?,nbrChambre=? where idReservation=?";

        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, r.getNbrNuit());
        pre.setDate(2, r.getDateEntree());
        pre.setDate(3, r.getDateSortie());
        pre.setString(4, r.getType());
        pre.setInt(5, r.getNbrChambre());
        pre.setInt(6, idReservation);
        pre.executeUpdate();

        System.out.println("Reservation Modifie avec Succees");
    }

}
