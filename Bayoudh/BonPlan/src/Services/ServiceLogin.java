/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import ConnexionBd.DataSource;
import Entites.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Asus Pc
 */
public class ServiceLogin {
      public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceLogin() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean login(String login,String mdp) {
  
 try {
     String req="select * from personne where login=? and mdp=?";
         PreparedStatement ps = con.prepareStatement(req);
            ps.setString(1,login); 
             ps.setString(2, mdp);   
         ResultSet rs;
        rs = ste.executeQuery(req);
         if (rs.next())
         {
                      User per=new User(rs.getInt("IdPersonne"),rs.getString("nom"),rs.getString("prenom"),rs.getInt("cin"),rs.getInt("tel"),rs.getDate("dateNaissance"),rs.getString("email"),rs.getString("role"),rs.getString("login"),rs.getString("mdp"));

             System.err.println("yess");
             return true;
         }
        } catch (SQLException ex) {
           //Logger.getLogger(PersonneDao.class.getName()).log(Level.SEVERE, null, ex);
         
        }
 return false;
       
    }
}
