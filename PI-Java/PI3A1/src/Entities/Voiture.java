/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author Amina
 */
public class Voiture {
    private int id;
    private String marque;
    private String couleur;

    public int getId() {
        return id;
    }

    public String getMarque() {
        return marque;
    }

    public String getCouleur() {
        return couleur;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public Voiture() {
    }

    public Voiture(String marque, String couleur) {
        this.marque = marque;
        this.couleur = couleur;
    }

    @Override
    public String toString() {
        return "Voiture{" + "id=" + id + ", marque=" + marque + ", couleur=" + couleur + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Voiture other = (Voiture) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.marque, other.marque)) {
            return false;
        }
        if (!Objects.equals(this.couleur, other.couleur)) {
            return false;
        }
        return true;
    }

    
    
    
}
