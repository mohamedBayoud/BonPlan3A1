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
 * @author Omar
 */
public class Promotion {

   private int id;
    private String description;
    private String titre;
    private float prix;
    private String lieu;
    private String image;
    private Date date;
    

    
    public Promotion() {
    }

    public Promotion(int id, String description, String titre, float prix, String lieu, String image, Date date) {
        this.id = id;
        this.description = description;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.image = image;
        this.date = date;
    }

    public Promotion(String description, String titre, float prix, String lieu, Date date) {
        this.description = description;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.date = date;
    }

    public Promotion(String description, String titre, float prix, String lieu, String image, Date date) {
        this.description = description;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.image = image;
        this.date = date;
    }

    public int getId() {
        return id;
    }
    

    public String getDescription() {
        return description;
    }

    public String getTitre() {
        return titre;
    }

    public float getPrix() {
        return prix;
    }

    public String getLieu() {
        return lieu;
    }

    public String getImage() {
        return image;
    }

    public Date getDate() {
        return date;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.description);
        hash = 13 * hash + Objects.hashCode(this.titre);
        hash = 13 * hash + Float.floatToIntBits(this.prix);
        hash = 13 * hash + Objects.hashCode(this.lieu);
        hash = 13 * hash + Objects.hashCode(this.image);
        hash = 13 * hash + Objects.hashCode(this.date);
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
        final Promotion other = (Promotion) obj;
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        if (!Objects.equals(this.lieu, other.lieu)) {
            return false;
        }
        if (!Objects.equals(this.image, other.image)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
        return true;
    }

    
    
}
