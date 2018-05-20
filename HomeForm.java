/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.LengthConstraint;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

import com.mycompagny.Service.serviceReservation;
import com.mycompany.Entite.Reservation;

/**
 *
 * @author sana
 */
public class HomeForm extends SideMenuBaseForm {

    Form f;
    Validator val = new Validator();
    Picker dateEntree;
    Picker dateSortie;
    TextField nbrChambres;

    TextField type;

    Button btnajout, btnaff;

    public HomeForm() {

        f = new Form("Réservation");
        Container c = new Container(BoxLayout.y());
        Label de = new Label("date d'entrée");
        de.getUnselectedStyle().setFgColor(2171464);
        dateEntree = new Picker();
        dateEntree.getUnselectedStyle().setFgColor(000000);

        String testtitre = "^\\(?([a-z])\\)?";
        String testprix = "^\\(?([1-9])\\)?";
        Label ds = new Label("date de sortie");
//                val.addConstraint(type, new LengthConstraint(5));

        ds.getUnselectedStyle().setFgColor(2171464);
        dateSortie = new Picker();
        dateSortie.getUnselectedStyle().setFgColor(000000);

        nbrChambres = new TextField();
        nbrChambres.setHint("nombre de chambres");
        val.addConstraint(nbrChambres, new RegexConstraint(testprix, "3abbi"));

        nbrChambres.getUnselectedStyle().setFgColor(000000);

        type = new TextField();
        type.setHint("Type de réservation");
        val.addConstraint(type, new RegexConstraint(testtitre, "3abbi"));
        type.getUnselectedStyle().setFgColor(000000);
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");

        c.add(de);
        c.add(dateEntree);
        c.add(ds);
        c.add(dateSortie);
        c.add(nbrChambres);
        c.add(type);

        c.add(btnajout);
        //   c.add(btnaff);
        f.add(c);
        f.getToolbar().addCommandToLeftBar("back", null, (j) -> {
            //     AffichageEvent h = new AffichageEvent();
            //     h.getF().show();

        });
        btnajout.addActionListener((ActionEvent e) -> {

            serviceReservation ser = new serviceReservation();

            float nbrChambre = Float.parseFloat(nbrChambres.getText());

            Reservation t = new Reservation(dateEntree.getDate(), dateSortie.getDate(), (int) nbrChambre, type.getText());
            if (val.isValid() && type.getText().trim().length() > 0 || val.isValid() && nbrChambres.getText().trim().length() > 0) {
                ser.ajoutReservation(t);
            } else 
                Dialog.show("error", "Verifiez vos champs", "ok", null);
           
            Affichage a = new Affichage();
            a.getF().show();

        });
        btnaff.addActionListener((x) -> {
            Affichage a = new Affichage();
        });

    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Picker getDateEntree() {
        return dateEntree;
    }

    public void setDateEntree(Picker dateEntree) {
        this.dateEntree = dateEntree;
    }

    public Picker getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Picker dateSortie) {
        this.dateSortie = dateSortie;
    }

    public TextField getNbrChambres() {
        return nbrChambres;
    }

    public void setNbrChambres(TextField nbrChambres) {
        this.nbrChambres = nbrChambres;
    }

    @Override
    protected void showOtherForm(Resources res) {
    }

}
