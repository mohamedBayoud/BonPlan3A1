/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author Omar
 */
public class User {

    private int IdPersonne;
    private String nom;
    private String prenom;
    private int cin;
    private int tel;
    private Date dateNaissance;
    private String email;
    private String role;
    private String login;
    private String mdp;

    public User(int IdPersonne, String nom, String prenom, int cin, int tel, Date dateNaissance, String email, String role, String login, String mdp) {
        this.IdPersonne = IdPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.tel = tel;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.role = role;
        this.login = login;
        this.mdp = mdp;
    }

    public User(String text, String text0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdPersonne() {
        return IdPersonne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getCin() {
        return cin;
    }

    public int getTel() {
        return tel;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public String getLogin() {
        return login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setIdPersonne(int IdPersonne) {
        this.IdPersonne = IdPersonne;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.IdPersonne;
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.prenom);
        hash = 29 * hash + this.cin;
        hash = 29 * hash + this.tel;
        hash = 29 * hash + Objects.hashCode(this.dateNaissance);
        hash = 29 * hash + Objects.hashCode(this.email);
        hash = 29 * hash + Objects.hashCode(this.role);
        hash = 29 * hash + Objects.hashCode(this.login);
        hash = 29 * hash + Objects.hashCode(this.mdp);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final User other = (User) obj;
        if (this.IdPersonne != other.IdPersonne) {
            return false;
        }
        if (this.cin != other.cin) {
            return false;
        }
        if (this.tel != other.tel) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.login, other.login)) {
            return false;
        }
        if (!Objects.equals(this.mdp, other.mdp)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" + "IdPersonne=" + IdPersonne + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", tel=" + tel + ", dateNaissance=" + dateNaissance + ", email=" + email + ", role=" + role + ", login=" + login + ", mdp=" + mdp + '}';
    }

    
}
