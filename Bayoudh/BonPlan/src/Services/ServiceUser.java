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
import Entites.User;
import java.sql.ResultSet;
import java.util.Scanner;

/**
 *
 * @author Omar
 */
public class ServiceUser {

    public Connection con = DataSource.getInstance().getConnection();
    public Statement ste;

    public ServiceUser() {
        try {
            ste = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(ServiceUser.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void AjouterUser(User E) throws SQLException {
        System.out.println("Veuillez Entrer les Donnees");

        String req = "INSERT INTO `personne`(`idPersonne`, `nom`, `prenom`, `cin`, `tel`, `dateNaissance`, `email`, `role`, `login`, `mdp`) VALUES (?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement pre = con.prepareStatement(req);

        pre.setInt(1, E.getIdPersonne());
        pre.setString(2, E.getNom());
        pre.setString(3, E.getPrenom());
        pre.setInt(4, E.getCin());
        pre.setInt(5, E.getTel());
        pre.setDate(6, E.getDateNaissance());
        pre.setString(7, E.getEmail());
        pre.setString(8, E.getRole());
        pre.setString(9, E.getLogin());
        pre.setString(10, E.getMdp());
        pre.executeUpdate();
        System.out.println("User  Ajoutée");

    }

    public void supprimerUser(int idPersonne) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String req = "DELETE FROM `personne` WHERE idPersonne =?";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1, idPersonne);
        pre.executeUpdate();
    }

    public void ModifierUser(User E, int idPersonne) throws SQLException {
        Scanner sc = new Scanner(System.in);

        String req = "UPDATE `personne` SET `idPersonne`=?,`nom`=?,`prenom`=?,`cin`=?,`tel`=?,`dateNaissance`=?,`email`=?,`role`=?,`login`=?,`mdp`=? WHERE idPersonne=? ";
        PreparedStatement pre = con.prepareStatement(req);
        pre.setInt(1,E.getIdPersonne());
        pre.setString(2, E.getNom());
        pre.setString(3, E.getPrenom());
        pre.setInt(4, E.getCin());
        pre.setInt(5, E.getTel());
        pre.setDate(6, E.getDateNaissance());
        pre.setString(7, E.getEmail());
        pre.setString(8, E.getRole());
        pre.setString(9, E.getLogin());
        pre.setString(10, E.getMdp());
        pre.setInt(11, idPersonne);
        pre.executeUpdate();

        System.out.println("User Modifie avec Succees");
    }

    public void ConsulterUser() throws SQLException {
        ResultSet res;
        String req = "SELECT * from personne";
        res = ste.executeQuery(req);
        while (res.next()) {

            System.out.println(res.getInt("idPersonne"));
            System.out.println(res.getString("nom"));
            System.out.println(res.getString("prenom"));
            System.out.println(res.getInt("cin"));
            System.out.println(res.getInt("tel"));
            System.out.println(res.getDate("dateNaissance"));
            System.out.println(res.getString("email"));
            System.out.println(res.getString("role"));
            System.out.println(res.getString("login"));

            System.out.println("********************************");

        }

    }
}
