/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;
import java.util.Objects;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Omar
 */
public class Partage {

    private int IdPartage;
    private String Avis;
    private String CommentaireAvis;
    private int NoteCuisine;
    private int NoteRapport;
    private int NoteService;
    private int NoteAmbiance;
    private Date DateCommentaire;

    public Partage() {
    }

    
    
    public Partage(int IdPartage) {
        this.IdPartage = IdPartage;
    }

    public Partage(String CommentaireAvis) {
        this.CommentaireAvis = CommentaireAvis;
    }

    public Partage(int IdPartage, String Avis, String CommentaireAvis, int NoteCuisine, int NoteRapport, int NoteService, int NoteAmbiance) {
        this.IdPartage = IdPartage;
        this.Avis = Avis;
        this.CommentaireAvis = CommentaireAvis;
        this.NoteCuisine = NoteCuisine;
        this.NoteRapport = NoteRapport;
        this.NoteService = NoteService;
        this.NoteAmbiance = NoteAmbiance;
    }

    public Partage(Date DateCommentaire) {
       this.DateCommentaire=DateCommentaire;
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
        final Partage other = (Partage) obj;
        if (!Objects.equals(this.Avis, other.Avis)) {
            return false;
        }
        return true;
    }

  


    public int getIdPartage() {
        return IdPartage;
    }

    public void setIdPartage(int IdPartage) {
        this.IdPartage = IdPartage;
    }

    public String getAvis() {
        return Avis;
    }

    public void setAvis(String Avis) {
        this.Avis = Avis;
    }

    public String getCommentaireAvis() {
        return CommentaireAvis;
    }

    public void setCommentaireAvis(String CommentaireAvis) {
       this.CommentaireAvis=CommentaireAvis;
    }

    public int getNoteCuisine() {
        return NoteCuisine;
    }

    public void setCuisine(int NoteCuisine) {
        this.NoteCuisine = NoteCuisine;
    }

	public void setNoteCuisine(int NoteCuisine) {
		this.NoteCuisine = NoteCuisine;
	}

    public int getNoteRapport() {
        return NoteRapport;
    }

    public void setNoteRapport(int NoteRapport) {
        this.NoteRapport = NoteRapport;
    }

    public int getNoteService() {
        return NoteService;
    }

    public void setNoteService(int NoteService) {
        this.NoteService = NoteService;
    }

    public int getNoteAmbiance() {
        return NoteAmbiance;
    }

    public void setNoteAmbiance(int NoteAmbiance) {
        this.NoteAmbiance = NoteAmbiance;
    }

    public Partage(int NoteCuisine, int NoteRapport, int NoteService, int NoteAmbiance,String CommentaireAvis,Date DateCommentaire) {
        this.CommentaireAvis = CommentaireAvis;
        this.NoteCuisine = NoteCuisine;
        this.NoteRapport = NoteRapport;
        this.NoteService = NoteService;
        this.NoteAmbiance = NoteAmbiance;
    this.DateCommentaire=DateCommentaire;
    }

    public Date getDateCommentaire() {
        return DateCommentaire;
    }

    public void setDateCommentaire(Date DateCommentaire) {
        this.DateCommentaire = DateCommentaire;
    }
    
    

    
    

  
   
}
