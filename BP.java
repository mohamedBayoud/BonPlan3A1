/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author senda
 */
public class BP {
    
    protected int idBonPlan ; 
    protected String nom ; 
    protected String photo ; 
    protected String description ; 
    protected String adresse ; 
    protected String ville ; 
    protected String type ; 
	protected int valide;
	protected String emailPersonne ; 
    

    public BP(){}

    public BP(int idBonPlan, String nom, String description, String adresse, String ville, String type , int valide) 
    {
        this.idBonPlan = idBonPlan;
        this.nom = nom;
        this.description = description;
        this.adresse = adresse;
        this.ville = ville;
        this.type = type;
		this.valide=valide;
		
    }
    public BP( String nom, String photo, String description, String adresse, String ville , int valide,String emailPersonne) 
    {  
        this.nom = nom;
        this.photo = photo;
        this.description = description;
        this.adresse = adresse;
        this.ville = ville;
		this.valide=valide;
		this.emailPersonne=emailPersonne;
    }

	public String getEmailPersonne() {
		return emailPersonne;
	}

	public void setEmailPersonne(String emailPersonne) {
		this.emailPersonne = emailPersonne;
	}
    
    
    

    public int getIdBonPlan() {
        return idBonPlan;
    }

    public void setIdBonPlan(int idBonPlan) {
        this.idBonPlan = idBonPlan;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
        final BP other = (BP) obj;
        if (this.idBonPlan != other.idBonPlan) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.photo, other.photo)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.adresse, other.adresse)) {
            return false;
        }
        if (!Objects.equals(this.ville, other.ville)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        return true;
    }

	public int getValide() {
		return valide;
	}

	public void setValide(int valide) {
		this.valide = valide;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + this.idBonPlan;
		hash = 59 * hash + Objects.hashCode(this.nom);
		hash = 59 * hash + Objects.hashCode(this.photo);
		hash = 59 * hash + Objects.hashCode(this.description);
		hash = 59 * hash + Objects.hashCode(this.adresse);
		hash = 59 * hash + Objects.hashCode(this.ville);
		hash = 59 * hash + Objects.hashCode(this.type);
		//hash = 59 * hash + this.valide;
		return hash;
	}
	

    @Override
    public String toString() {
        return "BP{" + "idBonPlan=" + idBonPlan + ", nom=" + nom + ", photo=" + photo + ", description=" + description + ", adresse=" + adresse + ", ville=" + ville + ", type=" + type + '}';
    }
	
	public String toString2() {
        return "BP{" + "idBonPlan=" + idBonPlan + ", nom=" + nom + ", photo=" + photo + ", description=" + description + ", adresse=" + adresse + ", ville=" + ville + ", type=" + type + " , valide = " +valide +'}';
    }

    
    
    
    
    
}
