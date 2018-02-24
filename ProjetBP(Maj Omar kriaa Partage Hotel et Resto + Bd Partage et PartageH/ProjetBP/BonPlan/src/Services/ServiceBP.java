package Services;

import Entities.BP;
import Entities.Sejourner;
import Entities.Culinaire;
import ConnexionBd.DataSource;
import static com.mysql.jdbc.Messages.getString;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ServiceBP {

	 public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;
    
    public ServiceBP() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvent.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

	public  void AjouterBP(BP bp)  throws SQLException  {
		
		try {
			String req = "insert into bonplan values(?,?,?,?,?,?,?,?,?,?)";
			  PreparedStatement pre = con.prepareStatement(req);

			if (bp instanceof Sejourner) {
				pre.setInt(1, bp.getIdBonPlan());
				pre.setString(2, bp.getNom());
				pre.setString(3, bp.getPhoto());
				pre.setString(4, bp.getDescription());
				pre.setString(5, bp.getAdresse());
				pre.setString(6, bp.getVille());
				pre.setString(7, "sejour");
				pre.setInt(8, ((Sejourner) bp).getNbreChambreDispo());
				pre.setInt(9, ((Sejourner) bp).getPrixnuit());
				pre.setInt(10, 0);
			} else if (bp instanceof Culinaire) {
				pre.setInt(1, bp.getIdBonPlan());
				pre.setString(2, bp.getNom());
				pre.setString(3, bp.getPhoto());
				pre.setString(4, bp.getDescription());
				pre.setString(5, bp.getAdresse());
				pre.setString(6, bp.getVille());
				pre.setString(7, "culinaire");
				pre.setInt(8, 0);
				pre.setInt(9, 0);
				pre.setInt(10, ((Culinaire) bp).getNbrePlace());
			}
			pre.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

/////////////////////////////////////////////////////////////////////////////////////////////////
	public  void SupprimerBP(int id) {
		
		try {
			
			String req = "delete from bonplan where idBonPlan=" + id + "";
                        PreparedStatement pre = con.prepareStatement(req);
			pre.executeUpdate(req);

		} catch (SQLException ex) {
			Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
///////////////////////////////////////////////////////////////////////////////////////////////////

	public  void ModifierBP(BP bp, int a) {
		
		          System.out.println("id == "+a);
		try {
			String req = "update bonplan set nom=?,photo=?,description=?,adresse=?,ville=? , "
					+ "type=? ,nbreChambreDispo=? , prixNuit=? , nbrePlace=?  where idBonPlan=?";
			PreparedStatement pre = con.prepareStatement(req);
			if (bp instanceof Sejourner) {
				System.out.println("test1");
				pre.setString(1, bp.getNom());
				pre.setString(2, bp.getPhoto());
				pre.setString(3, bp.getDescription());
				pre.setString(4, bp.getAdresse());
				pre.setString(5, bp.getVille());
				pre.setString(6, "sejour");
				pre.setInt(7, ((Sejourner) bp).getNbreChambreDispo());
				pre.setInt(8, ((Sejourner) bp).getPrixnuit());
				pre.setInt(9, 0);
				pre.setInt(10, a);

			} else if (bp instanceof Culinaire) {
				System.out.println("test2");
				pre.setString(1, bp.getNom());
				pre.setString(2, bp.getPhoto());
				pre.setString(3, bp.getDescription());
				pre.setString(4, bp.getAdresse());
				pre.setString(5, bp.getVille());
				pre.setString(6, "culinaire");
				pre.setInt(7, 0);
				pre.setInt(8, 0);
				pre.setInt(9, ((Culinaire) bp).getNbrePlace());
				pre.setInt(10, a);

			}

			pre.executeUpdate();

		} catch (SQLException ex) {
			Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
//////////////////////////////////////////////////////////////////////////////////////////////

	public  List<Sejourner> AfficherHotel() {
		String p = "sejour";
		
		List<Sejourner> list = new ArrayList<>();
		try {

			String req = "SELECT *from bonplan where type ='" + p + "'";
			PreparedStatement pre = con.prepareStatement(req);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				BP bp = new Sejourner(result.getString("nom"), result.getString("photo"),
						result.getString("description"), result.getString("adresse"), result.getString("ville"), result.getInt("nbreChambreDispo"),
						 result.getInt("prixnuit"));
				bp.setIdBonPlan(result.getInt("idBonPlan"));
				list.add((Sejourner) bp);
			}

		} catch (SQLException ex) {
			Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;

	}

	public  List<Culinaire> AfficherResto() {
		String p = "culinaire";
		
		List<Culinaire> list = new ArrayList<>();
		try {

			String req = "SELECT *from bonplan where type ='" + p + "'";
			PreparedStatement pre = con.prepareStatement(req);

			ResultSet result = pre.executeQuery();

			while (result.next()) {
				BP bp = new Culinaire(result.getString("nom"), result.getString("photo"),
						result.getString("description"), result.getString("adresse"), result.getString("ville"), result.getInt("nbrePlace")
				);
  				bp.setIdBonPlan(result.getInt("idBonPlan"));
				list.add((Culinaire) bp);
			}

		} catch (SQLException ex) {
			Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
		}
		return list;

	}
	
	public  List<Sejourner> chercherBP(String d) throws SQLException {
		String d1="sejour";
		List<Sejourner> list = new ArrayList<>();
        try {
            String req = "SELECT * from bonplan where ville='"+d+"' and type='"+d1+"'";
			PreparedStatement pre = con.prepareStatement(req);
			
			 ResultSet result= ste.executeQuery(req);       
			while (result.next()) {
				
                BP bp = new Sejourner(result.getString("nom"), result.getString("photo"),
						result.getString("description"), result.getString("adresse"), result.getString("ville"), result.getInt("nbreChambreDispo"),
						 result.getInt("prixnuit"));
               // System.out.println(result.getString("ville"));
                list.add((Sejourner) bp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
	public  List<Culinaire> chercherBP2(String d) throws SQLException {
		List<Culinaire> list = new ArrayList<>();
        try {
            String req = "SELECT * from bonplan where ville='"+d+"'";
			 PreparedStatement pre = con.prepareStatement(req);
			
			 ResultSet result = ste.executeQuery(req);         
			while (result.next()) {
				
                BP bp = new Sejourner(result.getString("nom"), result.getString("photo"),
						result.getString("description"), result.getString("adresse"), result.getString("ville"), result.getInt("nbreChambreDispo"),
						 result.getInt("prixnuit"));
                System.out.println(result.getString("ville"));
                list.add((Culinaire) bp);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceBP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;

    }
	
	
	}

