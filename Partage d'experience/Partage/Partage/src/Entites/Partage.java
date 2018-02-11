/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

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

    public Partage(String Avis, int NoteCuisine, int NoteRapport, int NoteService, int NoteAmbiance,String CommentaireAvis) {
        this.Avis = Avis;
        this.CommentaireAvis = CommentaireAvis;
        this.NoteCuisine = NoteCuisine;
        this.NoteRapport = NoteRapport;
        this.NoteService = NoteService;
        this.NoteAmbiance = NoteAmbiance;
    }

    
    

  
   
}
