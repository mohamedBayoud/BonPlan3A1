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
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;

import com.mycompagny.Service.serviceReservation;
import com.mycompagny.Service.serviceTransport;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.Transport;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author sana
 */
public class AffichageTr {
    Form f,F5;
    SpanLabel lb;
    Label ville;
    TextField champs;
    Button chercher;
    Button participer,supprimer,modifier,modifier1;
    Picker dateDepart;
    TextField prixPersonne ;
    TextField heureDepart;
    TextField heureArrive;
    TextField nbrPlaceDispo;
    TextField villeArrive;
    TextField villeDepart;
    Database db;
    public AffichageTr()  {  
        try {
            db = Database.openOrCreate("transport.db");
            db.execute("create table if not exists prefere (villedepart TEXT, villearrivee TEXT,datedepart TEXT);\"");
        } catch (IOException ex) {
        }
        f = new Form();
        lb = new SpanLabel("");
        champs = new TextField();
        chercher=new Button("chercher");
        ville = new Label();
        participer=new Button("participer");
        supprimer = new Button("supprimer");
        modifier = new Button("modifier");
        modifier1 = new Button("modifier");
        f.add(ville);
        //f.add(lb);
        f.add(champs);
        f.add(chercher);
        serviceTransport sr=new serviceTransport();
        ArrayList<Transport> lis=sr.affichageTransport();
                for (Transport li : lis) {

           Label aa = new Label();
           aa.setText( li.getVilleDepart()+ " ------------------>"+li.getVilleArrive() );
           
           String s=li.getVilleDepart();
            f.add(aa);
            /***********************detail*********************/
            aa.addPointerPressedListener((l) -> {
            serviceTransport ser = new serviceTransport();
            Transport tr = new Transport();
            tr.setVilleDepart(li.getVilleDepart());
            ArrayList<Transport> liss=ser.chercherTransport(tr);
            Form f = new Form(BoxLayout.y());
            Label Depart = new Label("Depart :" + li.getVilleDepart());
            Label Arrivee = new Label("Arrivee :" + li.getVilleArrive());
            Label hDepart = new Label("heure de Depart :" + li.getHeureDepart());
            Label hArrivee = new Label("heure d'arrivee :" + li.getVilleArrive());
            Label nbrp = new Label("nombre de place dispo :" + li.getNbrPlaceDispo());
            Label prix = new Label("prix place :" + li.getPrixPersonne());
            Label date = new Label("date depart :" + li.getDateDeparts());
            f.add(Depart);
            f.add(Arrivee);
            f.add(hDepart);
            f.add(hArrivee);
            f.add(nbrp);
            f.add(prix);
            f.add(date);
            f.add(supprimer);
            f.add(participer);
            f.add(modifier);
            
              f.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    AffichageTr h = new AffichageTr();
                    h.getF().show();

                });
              
                f.getToolbar().addCommandToOverflowMenu("Ajouter Au Liste", null, (ee) -> {
                    try {
                        db.execute("insert into prefere (villearrivee, villedepart , datedepart) values ('" + li.getVilleDepart()+ "', '" + li.getVilleArrive()+ "', '" + li.getDateDeparts()+ "');");
                        System.out.println("element inser");
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }

                });
                f.getToolbar().addCommandToOverflowMenu("Liste des participation", null, (ee) -> {
                     F5 = new Form(BoxLayout.y());
                    try {
                        Cursor cur = db.executeQuery("select * from prefere");
                        while (cur.next()) {
                            Row row = cur.getRow();
                            Label lb2 = new Label("Supprimer " + row.getString(0));
                            Label lb = new Label( row.getString(0) + "----> " + row.getString(1)+" le" + row.getString(2));
                            F5.add(lb);
                            F5.add(lb2);
                            lb2.addPointerPressedListener((lp) -> {
                                try {
                                    db.execute("Delete from prefere Where villedepart='" + li.getVilleDepart()+ "'");
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
                    F5.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                        f.show();

                    });

                    F5.show();

                });
                f.show();
                
               /**************supprimer**************/
                supprimer.addActionListener((e) -> {
                    
                    ser.supprimer(li.getId_transport());
                    });
                /**************partciper**************/
                participer.addActionListener((e) -> {
                    ser.Participer(li.getId_transport());
                    System.out.println("bech  yod5el");
                    sendMail();
                    System.out.println("done");
                    });
                /**************modfier**************/
                modifier.addActionListener((e) -> {
                    Form f1 = new Form(BoxLayout.y());
                    Container c =new Container(BoxLayout.y());
                    dateDepart = new Picker();
                    prixPersonne = new TextField();
                    heureDepart = new TextField();
                    heureArrive = new TextField();
                    heureDepart = new TextField();
                    nbrPlaceDispo = new TextField();
                    villeArrive = new TextField();
                    villeDepart = new TextField();
                    dateDepart.getUnselectedStyle().setFgColor(000000);
                    prixPersonne.getUnselectedStyle().setFgColor(000000);
                    String pri = Float.toString(li.getPrixPersonne());
                    prixPersonne.setText(pri);
                    heureDepart.getUnselectedStyle().setFgColor(000000);
                    String dep = Float.toString(li.getHeureDepart());
                    heureDepart.setText(dep);
                    heureArrive.getUnselectedStyle().setFgColor(000000);
                    String arriv = Float.toString(li.getHeureArrive());
                    heureArrive.setText(arriv);
                    nbrPlaceDispo.getUnselectedStyle().setFgColor(000000);
                    String nbrplaceDispon = Float.toString(li.getNbrPlaceDispo());
                    nbrPlaceDispo.setText(nbrplaceDispon);
                    villeArrive.getUnselectedStyle().setFgColor(000000);
                    villeArrive.setText(li.getVilleArrive());
                    villeDepart.getUnselectedStyle().setFgColor(000000);
                    villeDepart.setText(li.getVilleDepart());
                    c.add(dateDepart);
                    c.add(prixPersonne);
                    c.add(heureDepart);
                    c.add(heureArrive);
                    c.add(nbrPlaceDispo);
                    c.add(villeArrive);
                    c.add(villeDepart);
                    f1.add(c);
                    f1.add(modifier1);
                 modifier1.addActionListener((e1) -> {
             float prixpersonne = Float.parseFloat(prixPersonne.getText());
             float heuredepart = Float.parseFloat(heureDepart.getText());
             float heurearrive = Float.parseFloat(heureArrive.getText());
             float nbrplaceDispo = Float.parseFloat(nbrPlaceDispo.getText());
            Transport t= new Transport(villeDepart.getText(),villeArrive.getText(),(int)nbrplaceDispo,
                    (int)heuredepart,(int)heurearrive, (Date) dateDepart.getDate(),0,(int)prixpersonne,0,0);
      ser.modifierTransport(li.getId_transport(), t);
                    });
                 f1.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    AffichageTr h = new AffichageTr();
                    h.getF().show();

                  });f1.show();});
                
               
                    
               
    });
        }
       /********************chercher******************************/         
         chercher.addActionListener((e) -> {
             Form f = new Form(BoxLayout.y());
            serviceTransport ser = new serviceTransport();
            Transport tr = new Transport();
            tr.setVilleDepart(champs.getText());
        ArrayList<Transport> li=ser.chercherTransport(tr);
        f.add(lb);
        lb.setText(li.toString());
        f.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    AffichageTr h = new AffichageTr();
                    h.getF().show();

                });
                f.show();
        });
         
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
    public void sendMail()
    {
        try {
               
                Properties props = new java.util.Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");
                Session session = Session.getInstance(props, null);
                
                MimeMessage msg = new MimeMessage(session);
                
                msg.setFrom(new InternetAddress("Mot de passe <my_email@myDomain.com>"));
                msg.setRecipients(RecipientType.TO, "medali.ayedi@esprit.tn");
                msg.setSubject("ESPRIT Cupcake: "+"Votre commande est comfirmé");
                msg.setSentDate(new Date(System.currentTimeMillis()));
                
                msg.setText("Commande passé");
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com","medali.ayedi@esprit.tn","Medalicss1231996");
                st.sendMessage(msg, msg.getAllRecipients());
                
                System.out.println("ServerResponse : " + st.getLastServerResponse());
          
            } catch (MessagingException ex) {
            }
                            
    }


}
