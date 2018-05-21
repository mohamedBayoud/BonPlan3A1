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
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import com.mycompagny.Service.serviceReservation;
import com.mycompagny.Service.serviceBP;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.BP;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.Transport;
import com.mycompany.myapp.MyApplication;
import com.mycompany.myapp.MyApplication1;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author sana
 */
public class AffichageBP {

    Form f, F1, F3, F4, F5;
    TextField titrecherche;
    SpanLabel lb, lb2;
    Picker dateE;
    TextField nbPlace;
    TextField titre, prix, lieu, type, image, image2;
    TextField description, champs, Nom, adresse, nbr, villeb;
    TextField nom2, desc2, ville2, adresse2, nbr2, prixN2, photo2;
    Label descl, ville;
    Database db;
    String url = "http://localhost/BonPlan1/web/Uploads";
    EncodedImage enc, enc2, enc3;
    URLImage uRLImage, uRLImage2, uRLImage3;
    Button chercher, supprimer, modifier, modifier1, maps;
    AffichMap m = new AffichMap();

    public AffichageBP() {
        f = new Form();
        lb = new SpanLabel("");
        champs = new TextField();
        champs.setHint("Taper votre recherche");
        champs.getUnselectedStyle().setFgColor(000000);
        chercher = new Button("Chercher");
        supprimer = new Button("Supprimer");
        modifier = new Button("Modifier");
        modifier1 = new Button("Enregistrer");

        maps = new Button("Map");
        ville = new Label();
        f.add(ville);
        f.add(champs);;
        f.add(chercher);
        //f.add(lb);

        try {
            db = Database.openOrCreate("revv.db");
            db.execute("create table if not exists Favoris (nom TEXT, decription TEXT);\"");
        } catch (IOException ex) {
        }

        serviceBP sr = new serviceBP();
        ArrayList<BP> lis = sr.affichageBP();
        for (BP li : lis) {

            /*Label aa = new Label();
           aa.setText(" Hôtel: "+li.getNom()+ " ville: "+li.getVille() + " prix: "+li.getPrixNuit());
           String s=li.getNom();
            f.add(aa);*/
            ImageViewer imgV = new ImageViewer();
            //Image placeholder = Image.createImage(250, 170);
            Image placeholder = Image.createImage(100, 100);
            EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
            URLImage urlim = URLImage.createToStorage(enc, url + "/" + li.getPhoto(), url + "/" + li.getPhoto());
            imgV.setImage(urlim);
            System.out.println();
            // System.out.println(url + "/" + li.getPhoto()+li.getNom());
            Label aa = new Label(li.getNom() + "                     " + li.getPrixNuit() + " dt/nuit" + "                                     " + li.getPhoto());
            //Label desc = new Label(" " + li.getPrixNuit()+"dt");
            //desc.getUnselectedStyle().setFgColor(000000);
            aa.getUnselectedStyle().setFgColor(000000);

            f.add(aa);
            f.add(imgV);

            //f.add(desc);
            /**
             * **************detail*************************
             */
            aa.addPointerPressedListener((l) -> {
                serviceBP ser = new serviceBP();

                BP bp = new BP();
                //bp.setNom(li.getNom());
                ArrayList<BP> liss = ser.chercheridBP(bp);
                f = new Form(BoxLayout.y());
                ImageViewer imgV2 = new ImageViewer();
                Image placeholder2 = Image.createImage(250, 220);
                EncodedImage enc2 = EncodedImage.createFromImage(placeholder2, false);
                URLImage urlim2 = URLImage.createToStorage(enc2, url + "/" + li.getPhoto(), url + "/" + li.getPhoto());
                imgV2.setImage(urlim2);
                System.out.println();
                Label a = new Label("                           " + li.getNom());
                System.out.println(url + "/" + li.getPhoto());
                Label villee = new Label("" + li.getAdresse() + "-  " + li.getVille());
                Label prixx = new Label(" Prix :" + li.getPrixNuit() + "dt");
                Label descrip = new Label(" " + li.getDescription());
                descrip.getUnselectedStyle().setFgColor(000000);
                a.getUnselectedStyle().setFgColor(000000);
                prixx.getUnselectedStyle().setFgColor(000000);
                villee.getUnselectedStyle().setFgColor(000000);
                f.add(a);
                f.add(imgV2);
                f.add(villee);
                f.add(prixx);
                f.add(descrip);
                // f.add(lb);
                f.add(modifier);
                f.add(supprimer);
                Button reserver = new Button("reserver");
                f.add(reserver);
                f.add(maps);

                lb.setText(liss.toString());

                f.getToolbar().addCommandToLeftBar(" Retour ", null, (j) -> {
                    AffichageBP h = new AffichageBP();
                    h.getF().show();

                });

                maps.addActionListener((e) -> {
                    AffichMap m = new AffichMap();
                    m.start(li.getVille());
                    ser.sendMail();

                });
             
                reserver.addActionListener((e) -> {

             HomeForm h = new HomeForm();
             h.getF().show();

                });

                f.show();

                /**
                 * ************supprimer*************
                 */
                supprimer.addActionListener((e) -> {

                    ser.supprimer(li.getIdBonPlan());
                    AffichageBP ab = new AffichageBP();
                    ab.getF().show();
                });

                /**
                 * **************modifier**********
                 */
                modifier.addActionListener((e) -> {
                    Form f1 = new Form(BoxLayout.y());
                    Container c = new Container(BoxLayout.y());
                    ImageViewer imgV3 = new ImageViewer();
                    Image placeholder3 = Image.createImage(250, 220);
                    EncodedImage enc3 = EncodedImage.createFromImage(placeholder3, false);
                    URLImage urlim3 = URLImage.createToStorage(enc3, url + "/" + li.getPhoto(), url + "/" + li.getPhoto());
                    imgV3.setImage(urlim3);
                    nom2 = new TextField();
                    nom2.setText(li.getNom());
                    nom2.getUnselectedStyle().setFgColor(000000);
                    photo2 = new TextField();
                    photo2.setText(li.getPhoto());
                    photo2.getUnselectedStyle().setFgColor(000000);
                    desc2 = new TextField();
                    desc2.setText(li.getDescription());
                    desc2.getUnselectedStyle().setFgColor(000000);
                    adresse2 = new TextField();
                    adresse2.setText(li.getAdresse());
                    adresse2.getUnselectedStyle().setFgColor(000000);
                    ville2 = new TextField();
                    ville2.setText(li.getVille());
                    ville2.getUnselectedStyle().setFgColor(000000);
                    

                    
                    String t = "sejour";
                    int v = 0;
                    c.add(imgV3);
                    c.add(nom2);
                    //c.add(photo2);
                    c.add(desc2);
                    c.add(adresse2);
                    c.add(ville2);
                    //c.add(nbr2);
                    
                    f1.add(c);
                    f1.add(modifier1);
                    modifier1.addActionListener((e1) -> {
                        float prix = 0;
                        float nbrN = 0;

                        BP b = new BP(li.getIdBonPlan(), nom2.getText(),"", desc2.getText(), adresse2.getText(), ville2.getText(),
                                t, v, (int) nbrN, (int) prix);

                        ser.modifierBP(li.getIdBonPlan(), b);
                    });
                    f1.getToolbar().addCommandToLeftBar("Retour", null, (j) -> {
                        f.show();
                    });
                    f1.show();
                });

                /**
                 * ****************favoris************
                 */
                f.getToolbar().addCommandToOverflowMenu("Ajouter à Favoris ", null, (ee) -> {
                    try {
                        db.execute("insert into Favoris (Titre, decription) values ('" + li.getNom() + "', '" + li.getPrixNuit() + "');");
                        System.out.println("element inser");
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                });
                f.getToolbar().addCommandToOverflowMenu("Liste Favoris ", null, (ee) -> {
                    F5 = new Form(BoxLayout.y());
                    try {
                        Cursor cur = db.executeQuery("select * from Favoris");
                        while (cur.next()) {
                            Row row = cur.getRow();
                            Label lb2 = new Label("( Retirer )");
                            lb2.getUnselectedStyle().setFgColor(800080);
                            Label lb = new Label("Nom: " + row.getString(0) + "              prix  :" + row.getString(1) + "dt/nuit");
                            lb.getUnselectedStyle().setFgColor(000000);

                            F5.add(lb);
                            F5.add(lb2);
                            lb2.addPointerPressedListener((lp) -> {
                                try {
                                    db.execute("Delete from Favoris Where Titre='" + li.getNom() + "'");
                                    System.out.println("element supprimer ");
                                    AffichageBP h = new AffichageBP();
                                    h.getF().show();

                                    //F5.add(lb);
                                } catch (IOException ex) {
                                    System.out.println(ex);
                                }
                            });

                        }

                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                    F5.getToolbar().addCommandToRightBar("Retour", null, (j) -> {
                        f.show();

                    });

                    F5.show();

                });

            });
            
        }

        /**
         * *************chercher***************
         */
        chercher.addActionListener((e) -> {
            Form f = new Form(BoxLayout.y());
            serviceBP ser = new serviceBP();
            BP bp = new BP();
            bp.setVille(champs.getText());
            f.add(lb);

            ArrayList<BP> lise = ser.chercherBP(bp);
            //lb.setText(li.toString());
            for (BP l : lise) {

                ImageViewer imgV = new ImageViewer();
                //Image placeholder = Image.createImage(250, 170);
                Image placeholder = Image.createImage(100, 100);
                EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
                URLImage urlim = URLImage.createToStorage(enc, url + "/" + l.getPhoto(), url + "/" + l.getPhoto());
                imgV.setImage(urlim);
                System.out.println();
                // System.out.println(url + "/" + li.getPhoto()+li.getNom());
                Label aa = new Label(l.getNom() + " - " + l.getVille() + "                    " + l.getPrixNuit() + " dt/nuit" + "                                                       " + l.getPhoto());
                //Label desc = new Label(" " + li.getPrixNuit()+"dt");
                //desc.getUnselectedStyle().setFgColor(000000);
                aa.getUnselectedStyle().setFgColor(000000);

                f.add(aa);
                f.add(imgV);

                f.getToolbar().addCommandToLeftBar(" Retour ", null, (j) -> {
                    AffichageBP h = new AffichageBP();
                    h.getF().show();

                });
                f.show();
            }
        });
        f.getToolbar().addCommandToLeftBar(" + ", null, (e) -> {
            HomeFormBP h = new HomeFormBP();
            h.getF().show();

        });

        
       f.getToolbar().addCommandToRightBar("retour", null, (r) -> {
            ProfileForm p = new ProfileForm(MyApplication.theme, 0, "");
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
