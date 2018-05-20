/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Font;

import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;

import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;

import com.mycompagny.Service.serviceReservation;
import com.mycompany.Entite.Reservation;

import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class Affichage {

    Picker dateEntree;
    Picker dateSortie;
    TextField nbrChambres;
    Validator val = new Validator();
    TextField typee;

    Button valider;
    Form f;
    SpanLabel type;
    Button supprimer, modifier;

    public Affichage() {
        f = new Form();

        int compteur = 0;
        Label titre = new Label("                Votre Réservation");
        titre.getUnselectedStyle().setAlignment(100);
        titre.getUnselectedStyle().setFgColor(0xFF1414);
        titre.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));

        Container c = new Container(BoxLayout.y());
        c.setScrollableY(true);
        String separation = new String("---------------------------");

        Container c2 = new Container(BoxLayout.y());

        serviceReservation sr = new serviceReservation();
        ArrayList<Reservation> lis = sr.affichageReservation();
        for (Reservation li : lis) {
            Container den = new Container(BoxLayout.x());
            Container dso = new Container(BoxLayout.x());
            Container t = new Container(BoxLayout.x());
            Container cham = new Container(BoxLayout.x());
            Container nuit = new Container(BoxLayout.x());

            Label type = new Label("type de la réservation : ");
            Label nC = new Label("nombre de Chambres : ");
            Label nN = new Label("nombre de nuits : ");
            Label dateE = new Label("Votre date d'entree : ");
            Label dateS = new Label("Votre date de sortie : ");

            String nbrChambre = String.valueOf(li.getNbrChambres());
            String nbrNuits = String.valueOf(li.getNbrNuits());

            compteur++;
            if (compteur == lis.size()) {
                System.out.println("dkhal");

                den.add(dateE);
                den.add(li.getDateEntreef());

                dso.add(dateS);
                dso.add(li.getDatesortief());

                t.add(type);
                t.add(li.getType().toString());

                cham.add(nC);
                cham.add(nbrChambre);

                nuit.add(nN);
                nuit.add(nbrNuits);

                c2.add(den);
                c2.add(dso);
                c2.add(t);
                c2.add(cham);
                c2.add(nuit);

                c.add(c2);
                c.add(separation);
                modifier = new Button("modifier Reservation");
                supprimer = new Button("Annuler Reservation");
                supprimer.addActionListener((e) -> {
                    Reservation r = new Reservation();
                    sr.SupprimerReservation(li.getIdReservation());
                    HomeForm af = new HomeForm();
                    af.getF().show();

                });

                modifier.addActionListener((eo) -> {
                    f = new Form("Réservation");
                    Container D = new Container(BoxLayout.y());
                    Label de = new Label("date d'entrée");
                    de.getUnselectedStyle().setFgColor(2171464);

                    dateEntree = new Picker();
                    dateEntree.getUnselectedStyle().setFgColor(000000);

                    String testtitre = "^\\(?([a-z])\\)?";
                    String testprix = "^\\(?([1-9])\\)?";
                    Label ds = new Label("date de sortie");
                    ds.getUnselectedStyle().setFgColor(2171464);

                    dateSortie = new Picker();
                    dateSortie.getUnselectedStyle().setFgColor(000000);

                    nbrChambres = new TextField();

                    val.addConstraint(nbrChambres, new RegexConstraint(testprix, "3abbi"));
                    String nbr = Float.toString(li.getNbrChambres());
                    nbrChambres.getUnselectedStyle().setFgColor(000000);
                    nbrChambres.setHint(nbr);

                    typee = new TextField();
                    typee.setHint(li.getType());

                    val.addConstraint(type, new RegexConstraint(testtitre, "3abbi"));
                    typee.getUnselectedStyle().setFgColor(000000);

                    valider = new Button("valider");

                    D.add(de);
                    D.add(dateEntree);
                    D.add(ds);
                    D.add(dateSortie);
                    D.add(nbrChambres);
                    D.add(typee);

                    D.add(valider);
                    valider.addActionListener((l) -> {
                        serviceReservation r = new serviceReservation();
                        String nbPl = nbrChambres.getText();
                        int nbpp = Integer.parseInt(nbPl);

                        Reservation te = new Reservation(dateEntree.getDate(), dateSortie.getDate(), nbpp, typee.getText());

                        r.ModifierRes(li.getIdReservation(), te);
                        Affichage a = new Affichage();
                        a.getF().show();
                    });
                    //   c.add(btnaff);
                    f.add(D);
                    f.show();
                });

            }
            System.out.println(compteur + "    " + lis.size());

        }
        f.add(titre);
        f.add(c);
        Button Pm = new Button("Passer au payment");
        Pm.addActionListener((e) -> {
            payment p = new payment();
        });

        f.add(modifier);
        f.add(supprimer);
        f.add(Pm);

        f.getToolbar().addCommandToLeftBar("back", null, (j) -> {
            HomeForm h = new HomeForm();
            h.getF().show();

        });

        f.show();

    }

//}
    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
