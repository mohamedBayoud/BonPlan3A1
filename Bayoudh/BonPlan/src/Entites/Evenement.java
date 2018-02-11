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
public class Evenement {

    private int IdEvent;
    private int IdPersonne;
    private String description;
    private int nbPlace;
    private String titre;
    private float prix;
    private String lieu;
    private String image;
    private String type;
    private Date date;
    

    public Evenement() {
    }

    public Evenement(int IdEvent, int IdPersonne, String description, int nbPlace, String titre, float prix, String lieu, String type, Date date) {
        this.IdEvent = IdEvent;
        this.IdPersonne = IdPersonne;
        this.description = description;
        this.nbPlace = nbPlace;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.type = type;
        this.date = date;
       
    }

    public Evenement(int IdEvent, int IdPersonne, String description, int nbPlace, String titre, float prix, String lieu, String image, String type, Date date) {
        this.IdEvent = IdEvent;
        this.IdPersonne = IdPersonne;
        this.description = description;
        this.nbPlace = nbPlace;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.image = image;
        this.type = type;
        this.date = date;
     
    }
public Evenement(int IdEvent, int IdPersonne, String description, int nbPlace, String titre, float prix, String lieu, String image, String type) {
        this.IdEvent = IdEvent;
        this.IdPersonne = IdPersonne;
        this.description = description;
        this.nbPlace = nbPlace;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.image = image;
        this.type = type;
       
     
    }
    public Evenement(int IdEvent, int IdPersonne, String description, int nbPlace, String titre, float prix, String lieu, String type) {
        this.IdEvent = IdEvent;
        this.IdPersonne = IdPersonne;
        this.description = description;
        this.nbPlace = nbPlace;
        this.titre = titre;
        this.prix = prix;
        this.lieu = lieu;
        this.type = type;
    }

    

   

  
    public int getIdEvent() {
        return IdEvent;
    }

    public int getIdPersonne() {
        return IdPersonne;
    }

    public String getDescription() {
        return description;
    }

    public int getNbPlace() {
        return nbPlace;
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

    public String getType() {
        return type;
    }

    public Date getDate() {
        return date;
    }


    public void setIdEvent(int IdEvent) {
        this.IdEvent = IdEvent;
    }

    public void setIdPersonne(int IdPersonne) {
        this.IdPersonne = IdPersonne;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setNbPlace(int nbPlace) {
        this.nbPlace = nbPlace;
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

    public void setType(String type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

   
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.IdEvent;
        hash = 71 * hash + this.IdPersonne;
        hash = 71 * hash + Objects.hashCode(this.description);
        hash = 71 * hash + this.nbPlace;
        hash = 71 * hash + Objects.hashCode(this.titre);
        hash = 71 * hash + Float.floatToIntBits(this.prix);
        hash = 71 * hash + Objects.hashCode(this.lieu);
        hash = 71 * hash + Objects.hashCode(this.image);
        hash = 71 * hash + Objects.hashCode(this.type);
        hash = 71 * hash + Objects.hashCode(this.date);
      
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
        final Evenement other = (Evenement) obj;
        if (this.IdEvent != other.IdEvent) {
            return false;
        }
        if (this.IdPersonne != other.IdPersonne) {
            return false;
        }
        if (this.nbPlace != other.nbPlace) {
            return false;
        }
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
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.date, other.date)) {
            return false;
        }
       
        return true;
    }

    @Override
    public String toString() {
        return "Evenement{" + "IdEvent=" + IdEvent + ", IdPersonne=" + IdPersonne + ", description=" + description + ", nbPlace=" + nbPlace + ", titre=" + titre + ", prix=" + prix + ", lieu=" + lieu + ", image=" + image + ", type=" + type + ", date=" + date +  '}';
    }

}
