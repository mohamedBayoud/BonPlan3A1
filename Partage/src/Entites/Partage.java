/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entites;

/**
 *
 * @author Omar
 */
public class Partage {

    private int IdPartage;
    private String Avis;
    private String CommentaireAvis;
    private int Note;

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
        this.CommentaireAvis = CommentaireAvis;
    }

    public int getNote() {
        return Note;
    }

    public void setNote(int Note) {
        this.Note = Note;
    }

    public Partage(String Avis, String CommentaireAvis, int Note) {
        this.Avis = Avis;
        this.CommentaireAvis = CommentaireAvis;
        this.Note = Note;
    }

    public Partage() {
    }

    @Override
    public String toString() {
        return "Partage{" + "IdPartage=" + IdPartage + ", Avis=" + Avis + ", CommentaireAvis=" + CommentaireAvis + ", Note=" + Note + '}';
    }

}
