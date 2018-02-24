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
import Entities.Train;
import Entities.Transport;

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
public class ServiceTransport {
 
     public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceTransport() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     //ajout
     public  void AjouterTransport(Transport t) throws SQLException 
     {
        
       
              String req= "insert into transport values(?,?,?,?,?,?,?,?,?,?,?,?)";
             PreparedStatement pre = con.prepareStatement(req);
            if (t instanceof Covoiturage){
           pre.setInt(1,t.getId_transport());
          pre.setString(2,t.getVilleDepart());
           pre.setString(3,t.getVilleArrive());
            pre.setInt(4,t.getNbrPlaceDispo());
            pre.setDate(5,t.getDateDepart());
            pre.setInt(6,t.getHeureDepart());
            pre.setInt(7,t.getHeureArrive());
            pre.setString(8,"covoiturage");
            pre.setInt(9,((Covoiturage) t).getId_client());
            pre.setFloat(10,((Covoiturage) t).getprixPersonne());
            pre.setFloat(11,0); //train
            pre.setFloat(12,0);//train
            }
            else if (t instanceof Train)
            {
                 pre.setInt(1,t.getId_transport());
           pre.setString(2,t.getVilleDepart());
            pre.setString(3,t.getVilleArrive());
            pre.setInt(4,t.getNbrPlaceDispo());
            pre.setDate(5,t.getDateDepart());
            pre.setInt(6,t.getHeureDepart());
            pre.setInt(7,t.getHeureArrive());
            pre.setString(8,"train");
            pre.setInt(9,0); //covoiturage id_client qui a un covoiturage
            pre.setFloat(10,0);
            pre.setFloat(11,((Train) t).getclassLuxe());
            pre.setFloat(12,((Train) t).getclassEco());
            }
       
             
            
          
        pre.executeUpdate();
       
     }
     //supprimer
     public  void SupprimerTransport(Transport t) throws SQLException {
       
           
            String req= "delete from transport where idTransport="+t.getId_transport()+"";
             PreparedStatement pre = con.prepareStatement(req);
        ste.executeUpdate(req);
        
        

    }
     ////////////modifier
      public  void ModifierTransport(Transport t,int id_Transport) throws SQLException {
        
       
            String req= "update Transport set villeDepart=?,villeArrive=?,nbrPlaceDispo=?,dateDepart=?,heureDepart=?"
                    + ",heureArrivee=?,type=?,prixPersonne=?,classLuxe=? ,classEco=? "
                    + " where idTransport=?";
             PreparedStatement pre = con.prepareStatement(req);
            if (t instanceof Covoiturage)
            {
             pre.setString(1,t.getVilleDepart());
            pre.setString(2,t.getVilleArrive());
            pre.setInt(3,t.getNbrPlaceDispo());
             pre.setDate(4,t.getDateDepart());
            pre.setInt(5,t.getHeureDepart());
            pre.setInt(6,t.getHeureArrive());
            pre.setString(7,"covoiturage");
            pre.setFloat(8,((Covoiturage) t).getprixPersonne());
            pre.setFloat(9,0); //train
            pre.setFloat(10,0);//train
             pre.setInt(11,id_Transport);
            
            }
            else if (t instanceof Train)
            {
            pre.setString(1,t.getVilleDepart());
            pre.setString(2,t.getVilleArrive());
            pre.setInt(3,t.getNbrPlaceDispo());
            pre.setDate(4,t.getDateDepart());
            pre.setInt(5,t.getHeureDepart());
            pre.setInt(6,t.getHeureArrive());
            pre.setString(7,"train");
             //covoiturage id_client qui a un covoiturage
            pre.setFloat(8,0);
            pre.setFloat(9,((Train) t).getclassLuxe());
            pre.setFloat(10,((Train) t).getclassEco());
            pre.setInt(11,id_Transport);
            }
            
        pre.executeUpdate();
       
    }
       public  List<Covoiturage> AfficherCovoiturage() throws SQLException {
           String p="covoiturage";
        
             List<Covoiturage> list = new ArrayList<>();
        
            String req= "SELECT *from transport where type='"+p+"'";
             PreparedStatement pre = con.prepareStatement(req);
          
            
            
        ResultSet result =pre.executeQuery();
        
        
        while(result.next()){
          //  if (result.next()instanceof Covoiturage)
            Covoiturage v1 =new Covoiturage(result.getInt("idTransport"),result.getString("villeDepart"),
                    result.getString("villeArrive"),result.getInt("nbrPlaceDispo"),result.getInt("heureDepart"),
                    result.getInt("heureArrivee"),result.getDate("dateDepart"),result.getInt("idPersonne"),result.getFloat("prixPersonne"));
            
            list.add((Covoiturage) v1);
        }
        
       
       return list;
        
    }
        public  List<Train> AfficherTrain() throws SQLException {
           String p="train";
      
             List<Train> list = new ArrayList<>();
        
            String req= "SELECT *from transport where type='"+p+"'";
             PreparedStatement pre = con.prepareStatement(req);
            ResultSet result =pre.executeQuery();
        
        
        while(result.next()){
            Train v1 =new Train(result.getInt("idTransport"),result.getString("villeDepart"),
                    result.getString("villeArrive"),result.getInt("nbrPlaceDispo"),result.getInt("heureDepart"),
                    result.getInt("heureArrivee"),result.getDate("dateDepart"),result.getFloat("classLuxe"),result.getFloat("classEco"),result.getInt("idPersonne"));
            
            list.add((Train) v1);
        }
        
     
       return list;
        
    }
        public  void SupprimerTransport1(int id) throws SQLException {
       
      
            String req= "delete from transport where idTransport="+id+"";
            PreparedStatement pre = con.prepareStatement(req);
        pre.executeUpdate(req);
        
        
        
    }
   
    
}
