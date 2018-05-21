/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompagny.Service.serviceBP;
import com.mycompagny.Service.serviceReservation;
import com.mycompany.Entite.BP;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;
import java.io.IOException;

import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author sana
 */
public class HomeFormBP {

	Validator val = new Validator();
	String testtitre = "^\\(?([a-z])\\)?";
	String testprix = "^\\(?([1-9])\\)?";
	Form f;

	TextField nom;
	TextField photo;
	TextField description;
	TextField adresse;
	TextField ville;
	TextField nbreChambreDispo;
	TextField prixNuit;
	Label image = new Label();

	Button btnajout, btnaff;

	public HomeFormBP() {
		f = new Form("Ajouter votre Hôtel ");
		Container c = new Container(BoxLayout.y());
		nom = new TextField();
		nom.setHint("Nom");
		nom.getUnselectedStyle().setFgColor(000000);
		val.addConstraint(nom, new RegexConstraint(testtitre, "3abbi"));

		photo = new TextField();
		photo.setHint("Sélectionner une photo");
		photo.getUnselectedStyle().setFgColor(000000);
		description = new TextField();
		description.setHint("Description");
		description.getUnselectedStyle().setFgColor(000000);
		val.addConstraint(description, new RegexConstraint(testtitre, "3abbi"));

		adresse = new TextField();
		adresse.setHint("Adresse");
		adresse.getUnselectedStyle().setFgColor(000000);
		val.addConstraint(adresse, new RegexConstraint(testtitre, "3abbi"));

		ville = new TextField();
		ville.setHint("Ville");
		ville.getUnselectedStyle().setFgColor(000000);
	//	val.addConstraint(ville, new RegexConstraint(testtitre, "3abbi"));

		nbreChambreDispo = new TextField();
		nbreChambreDispo.setHint("Nombre des chambres");
		nbreChambreDispo.getUnselectedStyle().setFgColor(000000);
		val.addConstraint(nbreChambreDispo, new RegexConstraint(testprix, "3abbi"));

		prixNuit = new TextField();
		prixNuit.setHint("Prix nuitée");
		prixNuit.getUnselectedStyle().setFgColor(000000);
		val.addConstraint(prixNuit, new RegexConstraint(testprix, "3abbi"));

		btnajout = new Button("Ajouter");
		btnaff = new Button("Tous les hôtels");
        Button imgBtn = new Button("parcourir");

		c.add(nom);
		c.add(imgBtn);
		c.add(description);
		c.add(ville);
		c.add(adresse);
		c.add(nbreChambreDispo);
		c.add(prixNuit);
		c.add(btnajout);
		c.add(btnaff);
		f.add(c);
		//f.add(imgBtn);
		imgBtn.addActionListener(eok -> {
			Display.getInstance().openGallery(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent ev) {
					String path;
					if (ev != null && ev.getSource() != null) {
						path = (String) ev.getSource();
						System.out.println(path.substring(7));
						Image img = null;
						image.setText(path.substring(7));//image heya just label nsob feha fel path
						try {
							img = Image.createImage(FileSystemStorage.getInstance().openInputStream(path));
						} catch (IOException ex) {
							System.out.println("image 8altaa");
						}
						System.out.println(img);

					}
				}
			}, Display.GALLERY_IMAGE);
		});
		btnajout.addActionListener((ActionEvent e) -> {
			FileUploader fc = new FileUploader("localhost/BonPlan1/web/");

			String fileNameInServer;

			try {
				fileNameInServer = fc.upload(image.getText());

				serviceBP sbp = new serviceBP();
				float nbrChambreDispo = Float.parseFloat(nbreChambreDispo.getText());
				float prix = Float.parseFloat(prixNuit.getText());

				BP b = new BP(nom.getText(), fileNameInServer, description.getText(), adresse.getText(), ville.getText(), "sejour", 0, (int) nbrChambreDispo, (int) prix);
			//	if (val.isValid() && nom.getText().trim().length() > 0 || description.getText().trim().length() > 0 || prixNuit.getText().trim().length() > 0 || nbreChambreDispo.getText().trim().length() > 0 || adresse.getText().trim().length() > 0) {
					sbp.ajoutBP(b);
				//} else {
				//	Dialog.show("error", "Verifiez vos champs", "ok", null);
				//}

			} catch (Exception ex) {

			}

			/*} catch (Exception ex) {
                ex.printStackTrace();
            }*/
		});
		btnaff.addActionListener((e) -> {
			AffichageBP a = new AffichageBP();
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
