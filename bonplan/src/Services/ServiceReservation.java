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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maroua
 */
public class ServiceReservation {
    public Connection con=DataSource.getInstance().getConnection();
    public Statement ste;
    public ServiceReservation()
    {
        try{
            ste=con.createStatement();
        } 
        catch(SQLException ex){
            Logger.getLogger(ServiceReservation.class.getName()).log(Level.SEVERE,null,ex);
            
        }
        
    }
    
    public void AjouterReservation(Reservation r) throws SQLException
    {
        String req="INSERT INTO reservation(type,DateEntree,DateSortie,NbrPersonnes) VALUES(?,?,?,?)";
        PreparedStatement pre= con.prepareStatement(req);
    //preparedStatement temchi selon les parametres contrairement à statement temchi m3a requetes en bloc
    pre.setDate(2, r.getDateEntree());
    pre.setDate(3,r.getDateSortie());
    pre.setInt(4,r.getNbrPersonnes());
    pre.setString(1,r.getType());
    pre.executeUpdate();
    System.out.println("Réservation ajoutée");
    
    
    }
}
