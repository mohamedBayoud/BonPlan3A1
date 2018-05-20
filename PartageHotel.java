/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Date;

/**
 *
 * @author Omar
 */
public class PartageHotel {
	private int IdPartageH;
    private String CommentaireAvisH;
    private int NoteConfortH;
    private int NoteRapportH;
    private int NoteServiceH;
    private int NotePersonnelH;
    private Date DateCommentaireH;

	public PartageHotel() {
	}
 public PartageHotel(int IdPartageH) {
        this.IdPartageH = this.IdPartageH;
    }
	

	public int getIdPartageH() {
		return IdPartageH;
	}

	public void setIdPartageH(int IdPartageH) {
		this.IdPartageH = IdPartageH;
	}

	public String getCommentaireAvisH() {
		return CommentaireAvisH;
	}

	public void setCommentaireAvisH(String CommentaireAvisH) {
		this.CommentaireAvisH = CommentaireAvisH;
	}

	public int getNoteConfortH() {
		return NoteConfortH;
	}

	public void setNoteConfortH(int NoteConfortH) {
		this.NoteConfortH = NoteConfortH;
	}

	public int getNoteRapportH() {
		return NoteRapportH;
	}

	public void setNoteRapportH(int NoteRapportH) {
		this.NoteRapportH = NoteRapportH;
	}

	public int getNoteServiceH() {
		return NoteServiceH;
	}

	public void setNoteServiceH(int NoteServiceH) {
		this.NoteServiceH = NoteServiceH;
	}

	public int getNotePersonnelH() {
		return NotePersonnelH;
	}

	public void setNotePersonnelH(int NotePersonnelH) {
		this.NotePersonnelH = NotePersonnelH;
	}

	public Date getDateCommentaireH() {
		return DateCommentaireH;
	}

	public void setDateCommentaireH(Date DateCommentaireH) {
		this.DateCommentaireH = DateCommentaireH;
	}

	public PartageHotel(String CommentaireAvisH, int NoteConfortH, int NoteRapportH, int NoteServiceH, int NotePersonnelH, Date DateCommentaireH) {
		this.CommentaireAvisH = CommentaireAvisH;
		this.NoteConfortH = NoteConfortH;
		this.NoteRapportH = NoteRapportH;
		this.NoteServiceH = NoteServiceH;
		this.NotePersonnelH = NotePersonnelH;
		this.DateCommentaireH = DateCommentaireH;
	}

	public PartageHotel(String CommentaireAvisH) {
		this.CommentaireAvisH = CommentaireAvisH;
	}
	
	
	
	
}
