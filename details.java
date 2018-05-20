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
public class details {
     private int idReservation;
    private int nbrPersonnes;
    private Date dateEntree;
    private Date dateSortie;
    private String type;
     private int idPersonne;
    private int nbrChambre;
    private int nbrNuit;
    
    private int IdPersonne;
    private String nom;
    private String prenom;
    
    private String nomH;
    private int prix;
    
        public details(int idReservation,Date dateEntree, Date dateSortie,int nbrNuit, String type,int nbrChambre,String nom,String prenom) {
        this.idReservation = idReservation;
        this.nbrChambre = nbrChambre;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.type = type;
        this.nom = nom;
        this.nbrNuit=nbrNuit;
        this.prenom=prenom;
       
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNomH() {
        return nomH;
    }

    public void setNomH(String nomH) {
        this.nomH = nomH;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    
    public int getIdReservation() {
        return idReservation;
    }

    public void setIdReservation(int idReservation) {
        this.idReservation = idReservation;
    }

    public int getNbrPersonnes() {
        return nbrPersonnes;
    }

    public void setNbrPersonnes(int nbrPersonnes) {
        this.nbrPersonnes = nbrPersonnes;
    }

    public Date getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Date dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public int getNbrChambre() {
        return nbrChambre;
    }

    public void setNbrChambre(int nbrChambre) {
        this.nbrChambre = nbrChambre;
    }

    public int getNbrNuit() {
        return nbrNuit;
    }

    public void setNbrNuit(int nbrNuit) {
        this.nbrNuit = nbrNuit;
    }

  

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    
    
}
