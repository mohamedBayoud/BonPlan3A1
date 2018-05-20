/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompagny.Service.serviceBP;
import com.mycompagny.Service.serviceReservation;
import com.mycompany.Entite.BP;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;

/**
 *
 * @author sana
 */
public class HomeFormBP {

    Form f;
    
    TextField nom ;
	TextField photo ;
	TextField description ; 
	TextField adresse ; 
	TextField ville ; 
	TextField nbreChambreDispo ; 
	TextField prixNuit ; 
		 
    Button btnajout,btnaff;

    public HomeFormBP() {
        f = new Form("home");
        Container c =new Container(BoxLayout.y());
        nom = new TextField();
        photo = new TextField();
		description = new TextField();
        adresse = new TextField();
        ville = new TextField();
		nbreChambreDispo = new TextField();
        prixNuit = new TextField();
	
        btnajout = new Button("Ajouter");
        btnaff=new Button("Afficher");
        c.add(nom);
        c.add(photo);
        c.add(description);
        c.add(adresse);
        c.add(ville);
		c.add(nbreChambreDispo);
		c.add(prixNuit);
        c.add(btnajout);
		c.add(btnaff);
        f.add(c);
      
        btnajout.addActionListener((ActionEvent e) -> {
             serviceBP sbp = new serviceBP();
             float nbrChambreDispo = Float.parseFloat(nbreChambreDispo.getText());
             float prix = Float.parseFloat(prixNuit.getText());

            BP b = new BP(nom.getText(), photo.getText(), description.getText(), adresse.getText(), ville.getText(), "sejour", 0 ,(int)nbrChambreDispo , (int)prix);
			
			
            sbp.ajoutBP(b);
            

        });
        btnaff.addActionListener((e)->{
        AffichageBP a=new AffichageBP();
        a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

  
    public TextField getNom() {
        return nom;
    }

    public void setNom(TextField nom) {
        this.nom = nom;
    }

    public TextField getPhoto() {
        return photo;
    }

    public void setPhoto(TextField photo) {
        this.photo = photo;
    }
	
	public TextField getDescription() {
        return description;
    }

    public void setDescription(TextField description) {
        this.description = description;
    }
	
	public TextField getAdresse() {
        return adresse;
    }

    public void setAdresse(TextField adresse) {
        this.adresse = adresse;
    }
	
	public TextField getVille() {
        return ville;
    }

    public void getVille(TextField ville) {
        this.ville = ville;
    }
	public TextField getNbreChambreDispo() {
        return nbreChambreDispo;
    }

    public void setNbreChambreDispo(TextField nbreChambreDispo) {
        this.nbreChambreDispo = nbreChambreDispo;
    }
	
	public TextField getPrixNuit() {
        return prixNuit;
    }

    public void setPrixNuit(TextField prixNuit) {
        this.prixNuit = prixNuit;
    }
	
	

   
}
