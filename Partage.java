/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.ImageIO;
import com.mycompagny.Service.ServiceTask;
import com.mycompagny.Service.ServcePartageHotel;
import com.mycompany.Entite.PartageHotel;

import com.mycompany.Entite.Task;
import com.sun.webkit.ThemeClient;
import java.util.ArrayList;
import java.util.List;
import com.codename1.ui.util.Resources;
import java.io.IOException;
import java.io.OutputStream;
import com.codename1.share.FacebookShare;
import com.codename1.facebook.FaceBookAccess;
import com.codename1.ui.Slider;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 *
 * @author sana
 */
public class Partage {

	Form f;
	Label l1, l2, l3, l4, l5, L,Lb;
	TextField CommentaireAvis, NoteServiceH, NoteRapportH, NoteConfortH, NotePersonnelH;
	Slider idSupp;
	ComboBox<Integer> Box;
	Button btnajout, btnaff, btnDelete, Email, Share;
    String Qualite;
	float Moy;

	Button Search;
	TextField Note;

	Button btnShare;

	SpanLabel lb;
	Container C;

	private Resources theme;

	public Partage() {
		f = new Form("Partage",BoxLayout.y());
		
		theme = UIManager.initFirstTheme("/theme_1");

		l1 = new Label("Votre Avis");
		l2 = new Label("Note Service");
		l3 = new Label("Note Rapport");
		l4 = new Label("Note Confort");
		l5 = new Label("Note Personnel");
		L = new Label("Ou par Email");

		CommentaireAvis = new TextField();
		CommentaireAvis.getUnselectedStyle().setFgColor(000000);
		NoteServiceH = new TextField();
		NoteServiceH.getUnselectedStyle().setFgColor(000000);
		NoteRapportH = new TextField();
		NoteRapportH.getUnselectedStyle().setFgColor(000000);
		NoteConfortH = new TextField();
		NoteConfortH.getUnselectedStyle().setFgColor(000000);
		NotePersonnelH = new TextField();
		NotePersonnelH.getUnselectedStyle().setFgColor(000000);
        Lb=new Label("Note : 0");
		idSupp=new Slider();
		idSupp.setMinValue(0);
        idSupp.setMaxValue(5);
        idSupp.setEditable(true);
		Note = new TextField();
		Note.getUnselectedStyle().setFgColor(000000);
        Note.setHint("Tapez ici Pour faire votre recherche");
        
		btnajout = new Button("Partager");
		btnaff = new Button("Liste des Hotels");
		btnDelete = new Button("Supprimer");
		Search = new Button("Chercher");
		Email = new Button("Email");
		//btnShare = new Button("Share");

		f.add(l1);
		f.add(CommentaireAvis);
		f.add(l2);
		f.add(NoteServiceH);
		f.add(l3);
		f.add(NoteRapportH);
		f.add(l4);
		f.add(NoteConfortH);
		f.add(l5);
		f.add(NotePersonnelH);
		f.add(btnajout).getUnselectedStyle().setFgColor(5542241);
		f.add(L);
		f.add(Email);
		//f.add(btnShare);
		//f.add(btnaff);
		f.add(Lb);
		f.add(idSupp);
		f.add(btnDelete);
		f.add(Note);
		f.add(Search);


		btnajout.addActionListener((e) -> {
			ServcePartageHotel ser = new ServcePartageHotel();
			String a = NoteServiceH.getText();
			int NoteService = Integer.parseInt(a);
			String b = NoteRapportH.getText();
			int NoteRapport = Integer.parseInt(b);
			String c = NoteConfortH.getText();
			int NoteConfort = Integer.parseInt(c);
			String d = NotePersonnelH.getText();
			int NotePersonnel = Integer.parseInt(d);

			PartageHotel t = new PartageHotel(CommentaireAvis.getText(), NoteService, NoteRapport, NoteConfort, NotePersonnel);
			if (NoteService <= 5 && NoteRapport <= 5 && NoteConfort <= 5 && NotePersonnel <= 5) {
				ser.ajoutAvisHotel(t);
				Dialog.show("Succees 'ajout", "OK", "Ok", null);

			} else {
				Dialog.show("Erreur", "Pas d'ajout,Tu dois ajouter une note entre 0 et 5", "Ok", null);
			}

		});
		Email.addActionListener((e) -> {
			Message m = new Message("");
			Display.getInstance().sendMessage(new String[]{""}, "Subject of message", m);
		});

		/*btnaff.addActionListener((e) -> {
			
		});*/
		
		f.getToolbar().addCommandToOverflowMenu("Liste des Hotels", null, (ev)-> {

		AffichagePartage a = new AffichagePartage();
			a.getF().show();
		});
		
		idSupp.addActionListener((e)->{
			Lb.setText("Note Service:"+idSupp.getProgress());
		});
		
		btnDelete.addActionListener((e) -> {
			
			ServcePartageHotel ser = new ServcePartageHotel();
			/*String a = idSupp.get
			int id = Integer.parseInt(a);*/
			ser.Supprimer(idSupp.getProgress());
			Dialog.show("Succes", "Avis Supprimer avec Succes", "ok", null);
			
		});
      
		
		Search.addActionListener((e) -> {
			ServcePartageHotel SP=new ServcePartageHotel();
			PartageHotel P=new PartageHotel();
			 Form F2 = new Form(BoxLayout.y());
            String d = Note.getText();
            ArrayList<PartageHotel> liche = SP.ChercherCommentaire(d);
            for (PartageHotel lis : liche) {
                System.out.println();
                Label aa = new Label("Avis  : " + lis.getCommentaireAvisH());
                Label desc = new Label("Note Service  : " + lis.getNoteServiceH());
                Label prixx = new Label("Note Rapport :" + lis.getNoteRapportH());
                Label nbpl = new Label("Note Confort :" + lis.getNoteConfortH());
                Label datee = new Label("Note Personnel :" + lis.getNotePersonnelH());
				Label del = new Label("********************************************");

                F2.add(aa);
                F2.add(datee); 
                F2.add(prixx);
                F2.add(nbpl);
                F2.add(desc);
				F2.add(del);
				
				
                
              /*  F2.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    AffichageEvent h = new AffichageEvent();
                    h.getF().show();

                });*/
                F2.show();

            }

		});
		/*
		btnShare.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				FaceBookAccess.setClientId("1470798689671712");
				
				FaceBookAccess.setClientSecret("EAACEdEose0cBAIbK7P0N2Qo7m4BURJeVHkjZCITup93zQTQA55cuM9UCGsJHZAhTgL3OYPKeUjfWYZBrxdkf7aDZBm0nAj43XAxUBVmZAV0WEFYesAv4ouuJusYMWLT3HjFZC8GVw3T3Vg4NbmEGzhAKtp3G207j9SIByZCh4zcZBFFvVYI7ZAlFBD82GH34QuJEQtQLWiDHelAZDZD");
				FaceBookAccess.setToken("EAACEdEose0cBAC5rJQtZCmafVZANCkmaZB3ZAQT3zCr22slqwRqwg5jWXj6FRGwtMhkLxNJTTfGhZAQemgZB14J3zgbHjFOzZCbUZCApt7Mjl9RleJsqFmqmAL42JQfqIQllbm8KRFueZAd3WVNlz1GUQ4bKo2fZAtX5EbuJ12SyJHts2U1Yf0u1UZBUMKcaA8lXs7PICQejWVbFgZDZD");
				FacebookShare fs=new FacebookShare();
				fs.share("");
			}
		});
		
		*/
		f.getToolbar().addCommandToLeftBar("", theme.getImage("icons8-facebook-50.png"), e -> {
			
			    FaceBookAccess.setClientId("1470798689671712");
				FaceBookAccess.setClientSecret("EAACEdEose0cBAIbK7P0N2Qo7m4BURJeVHkjZCITup93zQTQA55cuM9UCGsJHZAhTgL3OYPKeUjfWYZBrxdkf7aDZBm0nAj43XAxUBVmZAV0WEFYesAv4ouuJusYMWLT3HjFZC8GVw3T3Vg4NbmEGzhAKtp3G207j9SIByZCh4zcZBFFvVYI7ZAlFBD82GH34QuJEQtQLWiDHelAZDZD");
				FaceBookAccess.setToken("EAACEdEose0cBAC5rJQtZCmafVZANCkmaZB3ZAQT3zCr22slqwRqwg5jWXj6FRGwtMhkLxNJTTfGhZAQemgZB14J3zgbHjFOzZCbUZCApt7Mjl9RleJsqFmqmAL42JQfqIQllbm8KRFueZAd3WVNlz1GUQ4bKo2fZAtX5EbuJ12SyJHts2U1Yf0u1UZBUMKcaA8lXs7PICQejWVbFgZDZD");
				FacebookShare fs=new FacebookShare();
				PartageHotel P=new PartageHotel();
				ServcePartageHotel ser=new ServcePartageHotel();
				 ArrayList<PartageHotel> lis=ser.getList2();
		 for (PartageHotel lis1 : lis) {
                System.out.println();
				Moy=(lis1.getNoteServiceH()+lis1.getNoteRapportH()+lis1.getNoteConfortH()+lis1.getNotePersonnelH())/4;
				if (Moy>0 && Moy<=1)
				{
				 Qualite="Horrible";
			    }
				if (Moy>1 && Moy<=2)
				{
				 Qualite="Mediocre";
			    }
				if (Moy>2 && Moy<=3)
				{
				 Qualite="Moyen";
			    }
				if (Moy>3 && Moy<=4)
				{
				 Qualite="Tres Bon";
			    }
				if (Moy>4 && Moy<=5)
				{
				 Qualite="Excellent";
			    }
								

			   fs.share("Venez vistez notre Bon Plan.Numero°:"+lis1.getNomHotel()+"Note:"+Qualite);
		 }
			
		});
			/*ShareButton sb = new ShareButton();
			sb.setText("Share Screenshot");
			f.add(sb);
			Image screenshot = Image.createImage(f.getWidth(), f.getHeight());
			f.revalidate();
			f.setVisible(true);
			f.paintComponent(screenshot.getGraphics(), true);
			String imageFile = FileSystemStorage.getInstance().getAppHomePath()
					+ "screenshot.png";
			try (OutputStream os
					= FileSystemStorage.getInstance().openOutputStream(imageFile)) {
				ImageIO.getImageIO().save(screenshot, os, ImageIO.FORMAT_PNG, 1);
			} catch (IOException err) {
				Log.e(err);
			}
			sb.setImageToShare(imageFile, "image/png");*/
			
			
			
			
			
		
		
		
		

	}

	public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}

	/* public TextField getTnom() {
        return CommentaireAvis;
    }

    public void setTnom(TextField CommentaireAvis) {
        this.CommentaireAvis = CommentaireAvis;
    }*/
	public TextField getNote() {
		return Note;
	}

	public void setNote(TextField Note) {
		this.Note = Note;
	}

}
