/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.spinner.Picker;
import com.mycompagny.Service.ServiceEvenement;
import com.mycompany.Entite.Evenement;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Asus Pc
 */
public class ModifierEvent {

    Form f;
    Label l1, l2, l3, l4, l5;

    Picker dateE;
    TextField nbPlace;
    TextField titre, prix, lieu, type, image;
    TextField description;
    Button btnajout, btnaff;
    ComboBox<String> combo = new ComboBox<>(
            "Rendonne",
            "Loisir",
            "Sortie",
            "Repas"
    );

    public ModifierEvent() {
        f = new Form("Evenement");
        f.getStyle().setBgColor(0xE8DCB5);
        titre = new TextField();
        description = new TextField();
        description.setHint("description");
        lieu = new TextField();
        dateE = new Picker();
        prix = new TextField();
        type = new TextField();
        nbPlace = new TextField();
        image = new TextField();
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");
        f.add(titre);
        f.add(description);
        f.add(lieu);
        f.add(dateE);
        f.add(prix);
        f.add(type);
        f.add(nbPlace);
        f.add(image);
        f.add(btnajout);
        f.add(btnaff);
        ServiceEvenement ser = new ServiceEvenement();
        btnajout.addActionListener((ActionEvent e) -> {
            FileUploader fc = new FileUploader("localhost/symphonie/web/evenement");
            String fileNameInServer;
            try {
                fileNameInServer = fc.upload(image.getText());
                String desc = description.getText();
                int idp = 8;
                String tit = titre.getText();
                String nbPl = nbPlace.getText();
                int nbp = Integer.parseInt(nbPl);
                String c = prix.getText();

                float pri = Float.parseFloat(c);
                int v = 0;
                Evenement t = new Evenement(idp, desc, nbp, tit, pri, lieu.getText(), fileNameInServer, type.getText(), dateE.getDate(), v);

                ser.ModifierEvent(t.getIdEvent(),t);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });
        btnaff.addActionListener((e) -> {
           // AffichageEvent a = new AffichageEvent();
           // a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
