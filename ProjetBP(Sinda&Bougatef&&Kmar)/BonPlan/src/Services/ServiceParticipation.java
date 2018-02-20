/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.Statement;
import ConnexionBd.DataSource;
import Entities.Covoiturage;
import Entities.Participation;
import Entities.Train;
import Entities.Transport;
import Services.ServiceTransport;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author esprit
 */
public class ServiceParticipation {
    //ajout
    
     public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceParticipation() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     public  void AjouterParticipation(Participation P) throws SQLException 
     {
         
       
              String req= "insert into participation values(?,?,?,?)";
            PreparedStatement pre = con.prepareStatement(req);
            pre.setInt(1,0);
           pre.setInt(2,P.getIdPersonne());
            pre.setInt(3,P.getIdTransport());
            pre.setString(4,P.getType());
                
        pre.executeUpdate();
    
     } 
     public  void SupprimerParticipation(int id) throws SQLException {
      
      
            String req= "delete from participation where idTransport="+id+"";
             PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate(req);
       
        
    }
}
