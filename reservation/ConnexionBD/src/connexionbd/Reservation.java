/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connexionbd;

import java.sql.Date;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class Reservation {
  private int idReservation;
 private int nbrPersonnes;
  private Date dateEntree;
 private Date dateSortie;
  private String type ;
private int idPersonne;
private float prix;

    public Reservation(int idReservation, int nbrPersonnes, Date dateEntree, Date dateSortie, String type, int idPersonne, float prix) {
        this.idReservation = idReservation;
        this.nbrPersonnes = nbrPersonnes;
        this.dateEntree = dateEntree;
        this.dateSortie = dateSortie;
        this.type = type;
        this.idPersonne = idPersonne;
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Reservation{" + "idReservation=" + idReservation + ", nbrPersonnes=" + nbrPersonnes + ", dateEntree=" + dateEntree + ", dateSortie=" + dateSortie + ", type=" + type + ", idPersonne=" + idPersonne + ", prix=" + prix + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idReservation;
        hash = 59 * hash + this.nbrPersonnes;
        hash = 59 * hash + Objects.hashCode(this.dateEntree);
        hash = 59 * hash + Objects.hashCode(this.dateSortie);
        hash = 59 * hash + Objects.hashCode(this.type);
        hash = 59 * hash + this.idPersonne;
        hash = 59 * hash + Float.floatToIntBits(this.prix);
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
        final Reservation other = (Reservation) obj;
        if (this.idReservation != other.idReservation) {
            return false;
        }
        if (this.nbrPersonnes != other.nbrPersonnes) {
            return false;
        }
        if (this.idPersonne != other.idPersonne) {
            return false;
        }
        if (Float.floatToIntBits(this.prix) != Float.floatToIntBits(other.prix)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.dateEntree, other.dateEntree)) {
            return false;
        }
        if (!Objects.equals(this.dateSortie, other.dateSortie)) {
            return false;
        }
        return true;
    }
  
   
    
    
    
}
