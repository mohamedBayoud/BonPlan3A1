/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author esprit
 */
public class User {

    private int IdPersonne;
    private String nom;
    private String prenom;
    private String cin;
    private String tel;
    private Date dateNaissance;
    private String email;
    private String role;
    private String login;
    private String mdp;

    public User(int IdPersonne, String nom, String prenom, String cin, String tel, Date dateNaissance, String email, String role, String login, String mdp) {
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
    public User(){}

    public int getIdPersonne() {
        return IdPersonne;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCin() {
        return cin;
    }

    public String getTel() {
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

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setTel(String tel) {
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
        hash = 29 * hash +  Objects.hashCode(this.cin);
        hash = 29 * hash + Objects.hashCode(this.tel);
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
