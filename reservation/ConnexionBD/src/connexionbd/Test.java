/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ASUS
 */
public class Test {
   static DataSource ds =new DataSource();
    
    
    public static void AjouterReservation(Reservation p) {
        PreparedStatement ste;
        try {
              String req= "insert into reservation values(?,?,?,?,?,?,?)";
            ste = ds.GetConnection().prepareStatement(req);
            
            
          ste.setInt(1,p.getIdReservation());
            ste.setInt(2,p.getNbrPersonnes());
            ste.setDate(3,p.getDateEntree());
            ste.setDate(4,p.getDateSortie());
            ste.setString(5,p.getType());
            ste.setInt(6,p.getIdPersonne());
            ste.setFloat(7,p.getPrix());
            
          
        ste.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    ////////////////////////////////////////
     public static void SupprimerPersonne(Reservation p) {
        Statement ste;
        try {
            ste = ds.GetConnection().createStatement();
            String req= "delete from reservation where idReservation="+p.getIdReservation()+"";
        ste.executeUpdate(req);
        
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
     //////////////////////////////////////////
      public static void ModifierReservation(int id ,Reservation p) {
          PreparedStatement ste;
        try {
            String req= "update reservation set nbrPresonnes=?,dateEntree=?,dateSortie=? where idReservation=?";
            ste = ds.GetConnection().prepareStatement(req);
            
            ste.setInt(1,p.getNbrPersonnes());
            ste.setDate(2,p.getDateEntree());
            ste.setDate(3,p.getDateSortie());
            ste.setInt(4,p.getIdReservation());
            
        ste.executeUpdate();
        
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
       public static List<Reservation> AfficherReservation() {
          PreparedStatement ste;
             List<Reservation> list = new ArrayList<>();
        try {
         
            String req= "SELECT *from reservation";
            ste = ds.GetConnection().prepareStatement(req);
            
            
        ResultSet result =ste.executeQuery();
        
        
        while(result.next()){
            Reservation v1 =new Reservation(result.getInt("idReservation"),result.getInt("nbrPresonnes"), result.getDate("dateEntree"),result.getDate("dateSortie"),result.getString("type"),result.getInt("idPersonne"),result.getFloat("Prix"));
            
            list.add(v1);
        }
        
        } catch (SQLException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
       return list;
        
    }
       

    
    public static void main(String[] args) {
        
         Reservation v1 =new Reservation(01,4,new Date(2018-1900,2-1,4),new Date(2018-1900,2-1,5),"sejours",1,(float) 4.5);
         Reservation v2 =new Reservation(02,7,new Date(2018-1900,2-1,8),new Date(2018-1900,2-1,9),"sejours",1,(float) 4.5);


         
         
              //AjouterReservation(v1);
        
       SupprimerPersonne(v1);
       // ModifierReservation(01,v1);
       AfficherReservation().forEach(System.out::println);
    }
    
}
