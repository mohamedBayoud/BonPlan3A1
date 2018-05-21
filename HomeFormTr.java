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
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompagny.Service.serviceReservation;
import com.mycompagny.Service.serviceTransport;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.Transport;
import java.util.Date;

/**
 *
 * @author sana
 */
public class HomeFormTr extends SideMenuBaseForm {

    Validator val = new Validator();

    String testtitre = "^\\(?([a-z])\\)?";
    String testprix = "^\\(?([1-9])\\)?";
    Form f;
    Picker dateDepart;
    TextField prixPersonne;
    TextField heureDepart;
    TextField heureArrive;
    TextField nbrPlaceDispo;
    TextField villeArrive;
    TextField villeDepart;
    Button btnajout, btnaff, chercher;

    public HomeFormTr(int x) {
        f = new Form("Transport");
        Container c = new Container(BoxLayout.y());
        dateDepart = new Picker();
        prixPersonne = new TextField();
        heureDepart = new TextField();
        heureArrive = new TextField();
        heureDepart = new TextField();
        nbrPlaceDispo = new TextField();
        villeArrive = new TextField();
        villeDepart = new TextField();
        btnajout = new Button("ajouter");
        btnaff = new Button("Affichage");
        dateDepart.getUnselectedStyle().setFgColor(000000);
        prixPersonne.getUnselectedStyle().setFgColor(000000);
        val.addConstraint(prixPersonne, new RegexConstraint(testprix, "3abbi"));

        prixPersonne.setHint("prix personne");
        heureDepart.getUnselectedStyle().setFgColor(000000);
        heureDepart.setHint("heure de depart");
        val.addConstraint(heureDepart, new RegexConstraint(testprix, "3abbi"));

        heureArrive.getUnselectedStyle().setFgColor(000000);
        heureArrive.setHint("heure d'arrivee");
        val.addConstraint(heureArrive, new RegexConstraint(testprix, "3abbi"));

        nbrPlaceDispo.getUnselectedStyle().setFgColor(000000);
        nbrPlaceDispo.setHint("nombre de places dispo");
        val.addConstraint(nbrPlaceDispo, new RegexConstraint(testprix, "3abbi"));

        villeArrive.getUnselectedStyle().setFgColor(000000);
        villeArrive.setHint("ville d'arrivee");
        val.addConstraint(villeArrive, new RegexConstraint(testtitre, "3abbi"));

        villeDepart.getUnselectedStyle().setFgColor(000000);
        val.addConstraint(villeDepart, new RegexConstraint(testtitre, "3abbi"));

        villeDepart.setHint("ville depart");
        c.add(dateDepart);
        c.add(prixPersonne);
        c.add(heureDepart);
        c.add(heureArrive);
        c.add(nbrPlaceDispo);
        c.add(villeArrive);
        c.add(villeDepart);
        c.add(btnajout);
        c.add(btnaff);
        f.add(c);

        btnajout.addActionListener((e) -> {
            serviceTransport ser = new serviceTransport();
            float prixpersonne = Float.parseFloat(prixPersonne.getText());
            float heuredepart = Float.parseFloat(heureDepart.getText());
            float heurearrive = Float.parseFloat(heureArrive.getText());
            float nbrplaceDispo = Float.parseFloat(nbrPlaceDispo.getText());
            Transport t = new Transport(villeDepart.getText(), villeArrive.getText(), (int) nbrplaceDispo,
                    (int) heuredepart, (int) heurearrive, (Date) dateDepart.getDate(), 7776, (int) prixpersonne, 0, 0);
             if (val.isValid() && villeArrive.getText().trim().length() > 0 || villeDepart.getText().trim().length() > 0 || heureArrive.getText().trim().length() > 0 || heureDepart.getText().trim().length() > 0 || nbrPlaceDispo.getText().trim().length() > 0) {
   ser.ajoutTransport(t);
                   
                } else {
                    Dialog.show("error", "Verifiez vos champs", "ok", null);
                }
         
        });
        btnaff.addActionListener((e) -> {
            AffichageTr a = new AffichageTr(x);
            a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

    public Picker getdateDepart() {
        return dateDepart;
    }

    public void setdateDepart(Picker dateDepart) {
        this.dateDepart = dateDepart;
    }

    @Override
    protected void showOtherForm(Resources res) {
    }

}
