package Services;


import ConnexionBd.DataSource;
import Entities.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author esprit
 */

 public class ServiceUser {

    public Connection con = DataSource.getInstance().getConnection();
    public static Statement ste;

    public ServiceUser() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
   /* public  static User ChercherUser(String login1 , String mdp) 
     {
         User p = null;
         PreparedStatement ste;
        try {
         
            String req= "SELECT  * from personne where login="+login1+"";
            ste = ds.GetConnection().prepareStatement(req);
            ResultSet result =ste.executeQuery();
            
            p=new User(result.getInt("idPersonne"),result.getString("nom"), result.getString("prenom"), result.getString("cin")
                    , result.getString("tel"), result.getDate("dateNaissance"), result.getString("email"), result.getString("role"), 
                    result.getString("login"), result.getString("mdp"));
        }
        catch (SQLException ex) {
            Logger.getLogger(Crud1.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
     }*/
         public static  List<User> ChercherUser() throws SQLException{
        
             List<User> list = new ArrayList<>();
      
         
            String req= "SELECT *from personne";
          
           ResultSet result;
             result = ste.executeQuery(req);
            while (result.next()) {
          //  if (result.next()instanceof Covoiturage)
            User p=new User(result.getInt("idPersonne"),result.getString("nom"), result.getString("prenom"), result.getString("cin")
                    , result.getString("tel"), result.getDate("dateNaissance"), result.getString("email"), result.getString("role"), 
                    result.getString("login"), result.getString("mdp"));
            
            list.add(p);
        }
        
      
       return list;
        
    }
         public static boolean comparer(String s1,String s2)
         {
             if (s1==s2)
                 return true;
        return false;
         }
}

