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
public class Sejourner extends BP {
    
    protected int nbreChambreDispo ; 
    protected int prixnuit ; 
 
    public Sejourner (){}
    public Sejourner(String nom,String photo,String description,String adresse,String ville,int prixnuit, int nbreChambreDispo, int valide,String emailPersonne) {
        super(nom, photo, description, adresse, ville , valide, emailPersonne);
        this.nbreChambreDispo = nbreChambreDispo;
        this.prixnuit = prixnuit;
    }

	
    public int getNbreChambreDispo() {
        return nbreChambreDispo;
    }

    public void setNbreChambreDispo(int nbreChambreDispo) {
        this.nbreChambreDispo = nbreChambreDispo;
    }

    public int getPrixnuit() {
        return prixnuit;
    }

    public void setPrixnuit(int prixnuit) {
        this.prixnuit = prixnuit;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.nbreChambreDispo;
        hash = 89 * hash + this.prixnuit;
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
        final Sejourner other = (Sejourner) obj;
        if (this.nbreChambreDispo != other.nbreChambreDispo) {
            return false;
        }
        if (this.prixnuit != other.prixnuit) {
            return false;
        }
        return true;
    }

	public static class setCellValueFactory {

		public setCellValueFactory() {
		}
	}
    
    
    
    
    
}
