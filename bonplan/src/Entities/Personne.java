/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Personne {
    private int idPersonne;
    private String nom;
 private String prenom;
  private String cin;
 private Date dateNaissance;
  private String tel ;

    public Personne(int idPersonne, String nom, String prenom, String cin, Date dateNaissance, String tel) {
        this.idPersonne = idPersonne;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.tel = tel;
    }

    public Personne(String nom) {
        this.nom = nom;
    }
    

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
  


    
}
