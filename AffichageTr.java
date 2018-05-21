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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

import com.mycompagny.Service.serviceReservation;
import com.mycompagny.Service.serviceTransport;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.Transport;
import com.mycompany.myapp.MyApplication;
import static com.mycompany.myapp.MyApplication.theme;
import com.sun.mail.smtp.SMTPTransport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javafx.scene.image.ImageView;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;


/**
 *
 * @author sana
 */
public class AffichageTr {
    private Resources theme;
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
    
    public AffichageTr(int idp)  {  
        try {
            db = Database.openOrCreate("transport.db");
            db.execute("create table if not exists prefere (villedepart TEXT, villearrivee TEXT,datedepart TEXT);\"");
        } catch (IOException ex) {
        }
        theme = UIManager.initFirstTheme("/theme_1");
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
        Label liste = new Label("La liste des transports");
        f.add(liste);
        //f.add(image);
        champs.setHint("Taper votre recherche");  champs.getUnselectedStyle().setFgColor(000000);
        serviceTransport sr=new serviceTransport();
        ArrayList<Transport> lis=sr.affichageTransport();
                for (Transport li : lis) {
                    Container c3 = new Container(BoxLayout.y());
                Container c2 = new Container(BoxLayout.x());
                Label aa = new Label();
                ImageViewer image = new ImageViewer(theme.getImage("voiture.png").fill(50, 50));
                c2.add(li.getVilleDepart());
                c2.add(image);
                c2.add(li.getVilleArrive());
                c3.add(c2);
                f.add(c3);
             String s=li.getVilleDepart();
             f.getToolbar().addCommandToLeftBar("+", null, (j) -> {
                    HomeFormTr h = new HomeFormTr(idp);
                    h.getF().show();

                  });
            /***********************detail*********************/
            
            c2.addPointerPressedListener((l) -> {
            serviceTransport ser = new serviceTransport();
            Transport tr = new Transport();
            tr.setVilleDepart(li.getVilleDepart());
            ArrayList<Transport> liss=ser.chercherTransport(tr);
            Form f = new Form(BoxLayout.y());
             Container a = new Container(BoxLayout.y());
                Container a1 = new Container(BoxLayout.x());
                ImageViewer image1 = new ImageViewer(theme.getImage("voiture.png").fill(50, 50));
                a1.add("                   "+li.getVilleDepart()).getUnselectedStyle().setFgColor(000000);
                a1.add(image1);
                a1.add(li.getVilleArrive()).getUnselectedStyle().setFgColor(000000);
                a.add(a1);
                f.add(a);
           
            Label hDepart = new Label("heure de Depart :" + li.getHeureDepart()+"h");
			hDepart.getUnselectedStyle().setFgColor(000000);
            Label hArrivee = new Label("heure d'arrivee :" + li.getHeureArrive()+"h");
			hArrivee.getUnselectedStyle().setFgColor(000000);
            Label nbrp = new Label("nombre de place dispo :" + li.getNbrPlaceDispo());
            nbrp.getUnselectedStyle().setFgColor(000000);
			Label prix = new Label("prix place :" + li.getPrixPersonne());
			prix.getUnselectedStyle().setFgColor(000000);
            Label date = new Label("date depart :" + li.getDateDeparts());
			date.getUnselectedStyle().setFgColor(000000);
            int x=li.getNbrPlaceDispo();
            if (li.getIdPersonne()==idp)
            {
                participer.setVisible(false);
            }
            else if (li.getIdPersonne()!=idp){
                participer.setVisible(true);
                supprimer.setVisible(false);
                modifier.setVisible(false);
                if (x==0)
            {
                participer.setVisible(false);
            }
                 }
            
            //f.add(Depart);
            //f.add(Arrivee);
            f.add(hDepart);
            f.add(hArrivee);
            f.add(nbrp);
            f.add(prix);
            f.add(date);
            f.add(supprimer);
            f.add(participer);
            f.add(modifier);
            
              f.getToolbar().addCommandToLeftBar("retour", null, (j) -> {
                    AffichageTr h = new AffichageTr(idp);
                    h.getF().show();

                });
              
                f.getToolbar().addCommandToOverflowMenu("Ajouter à ma liste", null, (ee) -> {
                    try {
                        db.execute("insert into prefere (villearrivee, villedepart , datedepart) values ('" + li.getVilleDepart()+ "', '" + li.getVilleArrive()+ "', '" + li.getDateDeparts()+ "');");
                        System.out.println("element inser");
                    } catch (IOException ex) {
                        System.out.println(ex);
                    }
                });
                f.getToolbar().addCommandToOverflowMenu("Ma liste", null, (ee) -> {
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
                    AffichageTr h = new AffichageTr(idp);
                    h.getF().show();
                    });
                /**************partciper**************/
                participer.addActionListener((e) -> {
                    serviceTransport ser1 = new serviceTransport();
                    ser1.sendMail();
                    ser.Participer(li.getId_transport(),idp);
                    
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
                    AffichageTr h = new AffichageTr(idp);
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
        f.add(lb);
        //lb.setText(li.toString());
		ArrayList<Transport> l=ser.chercherTransport(tr);
                for (Transport li : l) {

           Label aa = new Label();
		 
           Container c3 = new Container(BoxLayout.y());
                Container c2 = new Container(BoxLayout.x());
                ImageViewer image = new ImageViewer(theme.getImage("voiture.png").fill(50, 50));
                c2.add(li.getVilleDepart());
                c2.add(image);
                c2.add(li.getVilleArrive());
                c3.add(c2);
                f.add(c3);
                
        f.getToolbar().addCommandToRightBar("back", null, (j) -> {
                    AffichageTr h = new AffichageTr(idp);
                    h.getF().show();

                });
		f.getToolbar().addCommandToLeftBar("+", null, (j) -> {
                    HomeFormTr h = new HomeFormTr(idp);
					System.out.println(idp);
					System.out.println("frrr");
                    h.getF().show();

                  });
		
                f.show();}
        });
	f.getToolbar().addCommandToRightBar("retour", null, (r) -> {
            ProfileForm p = new ProfileForm(MyApplication.theme, idp, "");
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
