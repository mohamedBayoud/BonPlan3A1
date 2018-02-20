/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import ConnexionBd.DataSource;
import Entites.Partage;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Omar
 */
public class ServicePartage {

	public Connection con = DataSource.getInstance().getConnection();
	public Statement ste;

	public ServicePartage() {
		try {
			ste = con.createStatement();
		} catch (SQLException ex) {
			Logger.getLogger(ServicePartage.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	public void AjouterAvis(Partage P) throws SQLException {
		System.out.println("Veuillez Entrer les Donnees");
		String req = "INSERT INTO Partage (CommentaireAvis,NoteCuisine,NoteRapport,NoteService,NoteAmbiance,DateCommentaire) VALUES (?,?,?,?,?,?)";
		PreparedStatement pre = con.prepareStatement(req);
		pre.setString(1, P.getCommentaireAvis());
		pre.setInt(2, P.getNoteCuisine());
		pre.setInt(3, P.getNoteRapport());
		pre.setInt(4, P.getNoteService());
		pre.setInt(5, P.getNoteAmbiance());
		pre.setDate(6, (Date) P.getDateCommentaire());
		pre.executeUpdate();

		System.out.println("Avis Ajoutée");

	}

	public void AjouterAvis2() throws SQLException {

		System.out.println("Veuillez Entrer les Donnees");

		Scanner sc = new Scanner(System.in);

		String Avis = sc.nextLine();
		String CommentaireAvis = sc.nextLine();
		int Note = sc.nextInt();

		String req = "INSERT INTO Partage(Avis,CommentaireAvis,Note) VALUES ('" + Avis + "','" + CommentaireAvis + "','" + Note + "')";
		Statement ste = con.prepareStatement(req);
		ste.executeUpdate(req);

		System.out.println("Avis Ajoutée");
	}

	public void supprimerAvis(Partage P) throws SQLException {

		String req = "DELETE FROM `partage` WHERE DateCommentaire='" + P.getDateCommentaire() + "'";
		ste.executeUpdate(req);
		System.out.println("Avis Supprimeé avec Succees");
	}

	public void ModifierAvis(Partage P, int id) {

		try {

			// String req = "UPDATE Partage set ('"+Avis+"','"+CommentaireAvis+"','"+Note+"') where ('"+idPartage+"') ";
			String req = "UPDATE partage SET NoteCuisine='" + P.getNoteCuisine() + "',NoteRapport='" + P.getNoteRapport() + "',NoteService='" + P.getNoteService() + "',NoteAmbiance='" + P.getNoteAmbiance() + "',commentaireAvis='" + P.getCommentaireAvis() + "' WHERE idPartage='" + id + "' ;";
			PreparedStatement pre = con.prepareStatement(req);

			ste.executeUpdate(req);
			System.out.println("Mise à jour effectuée avec succès");

		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(ServicePartage.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	public List<Partage> AfficherCommentaire() throws SQLException {
		List<Partage> list = new ArrayList<>();
		String req = "SELECT * from Partage ";
		ResultSet res;
		res = ste.executeQuery(req);
		while (res.next()) {
			Partage P1 = new Partage(res.getString("commentaireAvis"));
			list.add(P1);

		}
		return list;

	}

	public List<String> AfficherAvisPartage() throws SQLException {
		List<String> list = new ArrayList<>();
		String req = "SELECT * from Personne ";
		ResultSet res;
		res = ste.executeQuery(req);
		while (res.next()) {

			list.add(res.getString("login"));

		}
		return list;

	}

	public List<Integer> AfficherIdPartage() throws SQLException {
		List<Integer> list = new ArrayList<>();
		String req = "SELECT * from Partage ";
		ResultSet res;
		res = ste.executeQuery(req);
		while (res.next()) {

			list.add(res.getInt("idPartage"));

		}
		return list;

	}

	public List<Date> AfficherDatePartage() throws SQLException {
		List<Date> list = new ArrayList<>();
		String req = "SELECT * from Partage ";
		ResultSet res;
		res = ste.executeQuery(req);
		while (res.next()) {

			list.add(res.getDate("DateCommentaire"));

		}
		return list;

	}

	/**
	 *
	 * @return @throws SQLException
	 */
	public List<Integer> AfficherIdEtDate() throws SQLException {
		List<Integer> list = new ArrayList<>();
		String req = "SELECT * from Partage ";
		ResultSet res;
		res = ste.executeQuery(req);
		while (res.next()) {

			list.add(res.getInt("idPartage"), res.getInt("DateCommentaire"));

		}
		return list;

	}

	public List<Double> AfficherIdPartage2() throws SQLException {
		List<Double> list = new ArrayList<>();
		String req = "SELECT * from Partage ";
		ResultSet res;
		res = ste.executeQuery(req);
		while (res.next()) {

			list.add(res.getDouble("idPartage"));

		}
		return list;

	}

	public float moyenneNote() {

		float moy=0;
		String req = "select (SUM(NoteCuisine+NoteRapport+NoteService+NoteAmbiance)/4)/COUNT(idPartage) from partage ";
        
		try {
			ResultSet rs = ste.executeQuery(req);
			rs.next();
			 moy = rs.getFloat(1);
		} catch (SQLException ex) {
			ex.printStackTrace();
			Logger.getLogger(ServicePartage.class.getName()).log(Level.SEVERE, null, ex);
		}
		return moy;
	}
	
	public int AfficherMoyenne() throws SQLException {
		String req = "SELECT SUM(NoteCuisine) from partage";
        int a = Integer.parseInt(req);
		return a;
	

	}

}
