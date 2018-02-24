/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnexionBd.DataSource;
import Entities.participerevent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * public class ServiceEvent {
 *
 *
 *
 * }
 *
 * @author esprit
 */
public class ServiceParticiperEvent {

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceParticiperEvent() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceParticiperEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //ajout
    public void AjouterParticipation(participerevent P) throws SQLException {

        String req = "INSERT INTO `participerevent`(idEvent,idpersonne) VALUES (?,?)";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, P.getIdEvent());
        pre.setInt(2, P.getIdPersonne());

        pre.executeUpdate();
//        System.out.println("participation  Ajoutée");

    }

    public void SupprimerParticipation(int id) throws SQLException {

        String req = "delete from participerevent where idEvent=" + id + "";
        PreparedStatement pre = con.prepareStatement(req);

        pre.executeUpdate();

    }
}
