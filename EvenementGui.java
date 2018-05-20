/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.FileSystemStorage;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.validation.RegexConstraint;
import com.codename1.ui.validation.Validator;
import com.mycompagny.Service.ServiceEvenement;
import com.mycompany.Entite.Evenement;
import static com.mycompany.gui.LoginForm.connectedUser;
import java.io.IOException;
import java.util.Calendar;
import rest.file.uploader.tn.FileUploader;

/**
 *
 * @author Asus Pc
 */
public class EvenementGui {

    Validator val = new Validator();

    Form f;
    Label l1, l2, l3, l4, l5;
    Label valide;
    Label valide1;
    Label valide3;
    Label valide2;
    Picker dateE;
    TextField nbPlace;
    TextField titre, prix, lieu, type;
    TextField description;
    Button btnajout, btnaff;

    public EvenementGui(String role) {
        Label image = new Label();
        valide1 = new Label();
        valide2 = new Label();
        valide3 = new Label();

        f = new Form("Evenement");
        titre = new TextField();
        titre.setHint("titre");

        String testtitre = "^\\(?([a-z])\\)?";
        String testprix = "^\\(?([1-9])\\)?";
        val.addConstraint(titre, new RegexConstraint(testtitre, "3abbi"));
        description = new TextField();
        description.setHint("description");

        val.addConstraint(description, new RegexConstraint(testtitre, "3abbi"));
        lieu = new TextField();
        lieu.setHint("lieu");
        val.addConstraint(lieu, new RegexConstraint(testtitre, "3abbi"));
        dateE = new Picker();
        prix = new TextField();
        prix.setHint("prix");
        val.addConstraint(prix, new RegexConstraint(testprix, "3abbi"));
        type = new TextField();
        type.setHint("type");
        val.addConstraint(type, new RegexConstraint(testtitre, "3abbi"));
        nbPlace = new TextField();
        nbPlace.setHint("nbPlace");
        val.addConstraint(nbPlace, new RegexConstraint(testprix, "3abbi"));
        description.getUnselectedStyle().setFgColor(000000);
        lieu.getUnselectedStyle().setFgColor(000000);
        prix.getUnselectedStyle().setFgColor(000000);
        type.getUnselectedStyle().setFgColor(000000);
        nbPlace.getUnselectedStyle().setFgColor(000000);
        titre.getUnselectedStyle().setFgColor(000000);
        dateE.getUnselectedStyle().setFgColor(000000);
        // image = new TextField();
        // image.setHint("image");
        btnajout = new Button("ajouter");
        //Button image = new Button("Image");
        btnaff = new Button("Affichage");
        f.add(titre);
        f.add(description);
        f.add(lieu);
        f.add(dateE);
        f.add(prix);
        f.add(type);
        f.add(nbPlace);
        //  f.add(image);
        f.add(btnajout);
         f.add(btnaff);
        Button imgBtn = new Button("parcourir");
        f.add(imgBtn);
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
                            //  Logger.getLogger(EvenementGui.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        System.out.println(img);

                    }
                }
            }, Display.GALLERY_IMAGE);
        });
        btnajout.addActionListener((ActionEvent e) -> {
            FileUploader fc = new FileUploader("localhost/symphonie/web");

            String fileNameInServer;
            try {

                fileNameInServer = fc.upload(image.getText());
                ServiceEvenement ser = new ServiceEvenement();
                String desc = description.getText();
                int idp = 8;

                String tit = titre.getText();
                String nbPl = nbPlace.getText();
                int nbp = Integer.parseInt(nbPl);
                String c = prix.getText();

                float pri = Float.parseFloat(c);
                int v = 0;
                Evenement t = new Evenement(connectedUser.getIdPersonne(), desc, nbp, tit, pri, lieu.getText(), fileNameInServer, type.getText(), dateE.getDate(), v);
                if (val.isValid() && titre.getText().trim().length() > 0 || description.getText().trim().length() > 0 || prix.getText().trim().length() > 0 || type.getText().trim().length() > 0 || nbPlace.getText().trim().length() > 0) {
                    ser.ajoutEvenement(t);
               //     AffichageEvent a = new AffichageEvent(role);
                 //   a.getF().show();
                } else {
                    Dialog.show("error", "Verifiez vos champs", "ok", null);
                }

            } catch (Exception ex) {
                System.out.println("ajouuuuu 8alet");

            }

        });
        btnaff.addActionListener((e) -> {
            AffichageEvent a = new AffichageEvent(role);
            a.getF().show();
        });
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
