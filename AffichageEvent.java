/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.db.Cursor;
import com.codename1.db.Database;
import com.codename1.db.Row;
import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Button;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompagny.Service.ServiceEvenement;
import com.mycompany.Entite.Evenement;
import com.mycompany.myapp.MyApplication;
import java.io.IOException;
import java.util.ArrayList;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Asus Pc
 */
public class AffichageEvent {

    Validator val = new Validator();

    String testtitre = "^\\(?([a-z])\\)?";
    String testprix = "^\\(?([1-9])\\)?";
    Resources theme;
    Form f, F1, F3, F4, F5;
    TextField titrecherche;
    SpanLabel lb, lb2;
    Picker dateE;
    TextField nbPlace;
    TextField titre, prix, lieu, type;
    TextField description;
    Label descl;
    Database db;
    String url = "http://localhost/BonPlan1/web/Uploads";
    EncodedImage enc;
    URLImage uRLImage;
    Button btnchercher, supprimer, Modifier, PartciperButton, AnnulerButton;

    public AffichageEvent(String role) {
        try {
            db = Database.openOrCreate("revv.db");
            db.execute("create table if not exists Favoris (Titre TEXT, decription TEXT);\"");
        } catch (IOException ex) {
        }
        ServiceEvenement SP = new ServiceEvenement();
        Evenement ev = new Evenement();
        Label image = new Label();
        f = new Form(BoxLayout.y());
        titrecherche = new TextField();
        titrecherche.setHint("titre");
        titrecherche.getUnselectedStyle().setFgColor(000000);
        btnchercher = new Button("chercher");
        f.add(btnchercher);
        f.add(titrecherche);
//////////---------Chercher-----------------------------------

        btnchercher.addActionListener((e) -> {
            Form F2 = new Form(BoxLayout.y());
            String d = titrecherche.getText();
            ArrayList<Evenement> liche = SP.ChercherEvnet(d);
            for (Evenement lis : liche) {
                ImageViewer imgV = new ImageViewer();
                Image placeholder = Image.createImage(180, 150);
                EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
                URLImage urlim = URLImage.createToStorage(enc, url + "/" + lis.getImage(), url + "/" + lis.getImage());
                imgV.setImage(urlim);
                System.out.println();
                System.out.println(url + "/" + lis.getImage());
                Label aa = new Label("Titre  : " + lis.getTitre());
                Label desc = new Label("Description  : " + lis.getDescription());
                Label prixx = new Label("Prix :" + lis.getPrix());
                Label nbpl = new Label("Nbr Place :" + lis.getNbPlace());
                Label datee = new Label("Date :" + lis.getDate1());
                F2.add(aa);
                F2.add(datee);
                F2.add(imgV);
                F2.add(prixx);
                F2.add(nbpl);
                F2.add(desc);

                F2.getToolbar().addCommandToLeftBar("retour", null, (j) -> {
                    AffichageEvent h = new AffichageEvent(role);
                    h.getF().show();

                });
                F2.show();

            }

        });
///------------------------------------------------------------

// ------------------------AFFicher------------------------------------
        ArrayList<Evenement> lis = SP.getList2();
        for (Evenement li : lis) {

            ImageViewer imgV = new ImageViewer();
            Image placeholder = Image.createImage(180, 150);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, url + "/" + li.getImage(), url + "/" + li.getImage());
            imgV.setImage(urlim);
            System.out.println();
            System.out.println(url + "/" + li.getImage());
            Label aa = new Label("Titre  : " + li.getTitre());
            Label datea = new Label("Date " + li.getDate1());
            Label desc = new Label("Prix  : " + li.getPrix());
            f.add(aa);
            f.add(datea);
            f.add(desc);
            f.add(imgV);
////----------------Detail-------------------------------
            aa.addPointerPressedListener((l) -> {
                supprimer = new Button("supprimer");
                Modifier = new Button("Modifier");
                PartciperButton = new Button("Participer");
                AnnulerButton = new Button("Annuler");
                F3 = new Form(BoxLayout.y());
                SP.DetailEvnet(li.getIdEvent(), ev);
                ImageViewer imgV2 = new ImageViewer();
                Image placeholder2 = Image.createImage(150, 120);
                Label tirl = new Label("Titre :" + ev.getTitre());
                Label prixl = new Label("Prix :" + ev.getPrix());
                Label datee = new Label("Date :" + ev.getDate1());
                Label nbPlacee = new Label("Nb Place :" + ev.getNbPlace());
                descl = new Label("description :" + ev.getDescription());
                EncodedImage enc2 = EncodedImage.createFromImage(placeholder2, false);
                URLImage urlim2 = URLImage.createToStorage(enc2, url + "/" + ev.getImage(), url + "/" + ev.getImage());
                imgV2.setImage(urlim2);
                F3.add(tirl);

                F3.add(imgV2);
                F3.add(datee);
                F3.add(nbPlacee);
                F3.add(prixl);
                F3.add(descl);
                //F3.add(imgV2);
                F3.add(PartciperButton);
                //F3.add(AnnulerButton);

                F3.getToolbar().addCommandToLeftBar("retour", null, (kj) -> {
                    AffichageEvent h = new AffichageEvent(role);
                    h.getF().show();

                });
                //////------------Supprimer------------------
                F3.add(supprimer);
                F3.add(Modifier);
                if (role.equals("membre")) {
                    supprimer.setVisible(false);
                    Modifier.setVisible(false);
                }
                 if (role.equals("bonplaneur")) {
                    PartciperButton.setVisible(false);
                   
                }
                supprimer.addActionListener((e) -> {
                    SP.SupprimerEvnet(li.getIdEvent());
                    AffichageEvent af = new AffichageEvent(role);
                    af.getF().show();
                });
                ///---------------Participer--------
                int c1 = li.getNbPlace();
                PartciperButton.addActionListener((mp) -> {

                    if (c1 > 0) {
                        SP.participer(li.getIdEvent());
                        TwilioSms sms = new TwilioSms();
                       // sms.sendSms("Vous allez participer à cette Evenement  " + ev.getTitre() + ", Vous serez le bienvenu le " + ev.getDate1());
                    } else {
                        System.out.println("errrrrrrrrrrrrrrrrrrr nnnnnnn");
                    }
                });
                if (c1 == 0) {
                    PartciperButton.setVisible(false);
                }
                ///---------------Anuuler--------------
                AnnulerButton.addActionListener((anul) -> {
                    //lezemm user marra barkaa 
                    SP.annuler(li.getIdEvent());
                });
                ///---------------------------------------
                F3.getToolbar().addCommandToOverflowMenu("Ajouter Au Liste", null, (ee) -> {
                    try {
                        db.execute("insert into Favoris (Titre, decription) values ('" + ev.getTitre() + "', '" + ev.getPrix() + "');");
                        System.out.println("element inser");
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                });
                F3.getToolbar().addCommandToOverflowMenu("Liste Favoris", null, (ee) -> {
                    F5 = new Form(BoxLayout.y());
                    try {
                        Cursor cur = db.executeQuery("select * from Favoris");
                        while (cur.next()) {
                            Row row = cur.getRow();
                            Label lb2 = new Label("Supprimer " + row.getString(0));
                            Label lb = new Label("Titre  :" + row.getString(0) + "  Prix  :" + row.getString(1));
                            F5.add(lb);
                            F5.add(lb2);
                            lb2.addPointerPressedListener((lp) -> {
                                try {
                                    db.execute("Delete from Favoris Where Titre='" + li.getTitre() + "'");
                                    System.out.println("element supprimer ");
//                                    F5.add(lb);
                                } catch (IOException ex) {
                                    System.out.println(ex);
                                }
                            });

                        }

                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    F5.getToolbar().addCommandToLeftBar("retour", null, (j) -> {
                        F3.show();

                    });

                    F5.show();

                });

                ///-----------Modifier-----------------
                Modifier.addActionListener((e) -> {
                    /*ModifEvent m = new ModifEvent(li);
                    m.getF().show();*/
                    Form F4 = new Form(BoxLayout.y());
                    //F4.getStyle().setBgColor(0xE8DCB5);
                    titre = new TextField();
                    val.addConstraint(titre, new RegexConstraint(testtitre, "3abbi"));

                    description = new TextField();
                    val.addConstraint(description, new RegexConstraint(testtitre, "3abbi"));

                    lieu = new TextField();
                    val.addConstraint(lieu, new RegexConstraint(testtitre, "3abbi"));
                    dateE = new Picker();
                    prix = new TextField();
                    val.addConstraint(prix, new RegexConstraint(testprix, "3abbi"));
//                    type.setHint("type");
//                    nbPlace.setHint("nbPlace");
                    prix.setHint("prix");
                    lieu.setHint("lieu");
                    type = new TextField();
                    val.addConstraint(type, new RegexConstraint(testtitre, "3abbi"));

                    nbPlace = new TextField();
                    val.addConstraint(nbPlace, new RegexConstraint(testprix, "3abbi"));
                    description.getUnselectedStyle().setFgColor(000000);
                    lieu.getUnselectedStyle().setFgColor(000000);
                    prix.getUnselectedStyle().setFgColor(000000);
                    type.getUnselectedStyle().setFgColor(000000);
                    nbPlace.getUnselectedStyle().setFgColor(000000);
                    titre.getUnselectedStyle().setFgColor(000000);
                    dateE.getUnselectedStyle().setFgColor(000000);
                    titre.setText(ev.getTitre());
                    description.setText(ev.getDescription());
                    type.setText(ev.getType());
                    lieu.setText(ev.getLieu());
                    String prx = Float.toString(ev.getPrix());
                    String nbp = Integer.toString(ev.getNbPlace());
                    prix.setText(prx);
                    nbPlace.setText(nbp);
                    F4.add(titre);
                    F4.add(description);
                    F4.add(lieu);
                    F4.add(dateE);
                    F4.add(prix);
                    F4.add(type);
                    F4.add(nbPlace);

                    int v = 0;
                    int idp = 8;

                    Button valider = new Button("valider");

                    F4.add(valider);
                    F4.show();
                    valider.addActionListener((il) -> {
                        FileUploader fc = new FileUploader("localhost/symphonie/web");
                        String fileNameInServer;
                        try {

                            String descc = description.getText();
                            int idpp = 8;
                            String tit = titre.getText();
                            String nbPl = nbPlace.getText();
                            int nbpp = Integer.parseInt(nbPl);
                            String c = prix.getText();
                            float pri = Float.parseFloat(c);
                            int k = 0;

                            Evenement t = new Evenement(descc, nbpp, tit, pri, lieu.getText(), ev.getImage(), type.getText(), dateE.getDate());
                            System.out.println(li.getIdEvent());
                            if (val.isValid() && titre.getText().trim().length() > 0 || description.getText().trim().length() > 0 || prix.getText().trim().length() > 0 || nbPlace.getText().trim().length() > 0) {
                                SP.ModifierEvent(li.getIdEvent(), t);
                            } else {
                                Dialog.show("error", "Verifiez vos champs", "ok", null);
                            }

                        } catch (Exception ex) {
                            System.out.println("modifier 8alet");
                        }
                    });

                    ///---------------------------------------
                    F4.getToolbar().addCommandToLeftBar("retour", null, (j) -> {
                        F3.show();

                    });
                    ///---------------------------------------
                });
                F3.show();

            });

        }
        if (role.equals("bonplaneur")) {
            f.getToolbar().addCommandToLeftBar(" +", null, (e) -> {
                EvenementGui h = new EvenementGui(role);
                h.getF().show();

            });
        }
        f.getToolbar().addCommandToRightBar("retour", null, (e) -> {
            int x = 0;
            ProfileForm p = new ProfileForm(MyApplication.theme, x, role);

            p.show();
            // p.show();

        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }

}
