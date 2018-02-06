/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class DataSource {
    final String ur1 ="jdbc:mysql://localhost:3306/bonplan";
    final String user ="root";
    final String Password="";
    Connection connection;
    
    public DataSource (){
        
        try {
            connection = DriverManager.getConnection(ur1, user,Password) ;
        } catch (SQLException ex) {
            Logger.getLogger(DataSource.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
    public Connection GetConnection (){
        return connection;
    }
    
}
