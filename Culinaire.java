/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author senda
 */
public class Culinaire extends BP {
    protected int nbrePlace ; 

    
    public Culinaire(String nom,String photo,String description,String adresse,String ville, int nbrePlace,int valide,String emailPersonne) {
        super( nom, photo, description, adresse, ville ,valide, emailPersonne);
        this.nbrePlace = nbrePlace;
    }
	


    public int getNbrePlace() {
        return nbrePlace;
    }

    public void setNbrePlace(int nbrePlace) {
        this.nbrePlace = nbrePlace;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.nbrePlace;
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
        final Culinaire other = (Culinaire) obj;
        if (this.nbrePlace != other.nbrePlace) {
            return false;
        }
        return true;
    }
    
    

    
    
}
