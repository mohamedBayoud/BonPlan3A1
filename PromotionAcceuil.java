/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServcePartageHotel;
import com.mycompagny.Service.ServicePromotion;
import com.mycompany.Entite.Promotion;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Omar
 */
public class PromotionAcceuil {
	 Form f;
	Label l1,l2,l3,l4,l5;
   TextField titre,description,lieu,Prix,idSupp;
   Date dateFin;
       Picker date;
	
	   TextField Titre;

    Button btnajout,btnaff,btnDelete;
	Button Search;
	
	
			    private Resources theme;

	
        public PromotionAcceuil() {
        f = new Form("Promotion");
					           theme = UIManager.initFirstTheme("/theme_1");

	    l1 = new Label("Titre");
	    l2 = new Label("Description");
	    l3 = new Label("Lieu");
	    l4 = new Label("Date");
	    l5 = new Label("Prix");
        titre = new TextField();
        description = new TextField();
		lieu = new TextField();
        Prix = new TextField();
		date = new Picker();
		Titre=new TextField();
		
		date.getUnselectedStyle().setFgColor(000000);
        l1.getUnselectedStyle().setFgColor(2171464);
		
		idSupp = new TextField();		
        btnajout = new Button("ajouter");
        btnaff=new Button("Affichage");
		btnDelete = new Button("Supprimer");
		Search=new Button("Search");
		f.add(l1);
        f.add(titre);
		f.add(l2);
        f.add(description);
	    f.add(l3);
		f.add(lieu);
		f.add(l4);
		f.add(date);
        f.add(l5);
		f.add(Prix);
        f.add(btnajout);
        f.add(btnaff);
		f.add(idSupp);
		f.add(btnDelete);
        f.add(Titre);
		f.add(Search);
        btnajout.addActionListener((e) -> {
            ServicePromotion ser = new ServicePromotion();
			String P=Prix.getText();
			int a=Integer.parseInt(P);
			 DateFormat dateFormat = new SimpleDateFormat("dd/MM/yy");
                Calendar cal = Calendar.getInstance();
				                System.out.println(dateFormat.format(cal.getTime()));

			Promotion t = new Promotion(titre.getText(),description.getText(),lieu.getText(),"aaaa",date.getDate(),a);
            java.util.Date date1 = new java.util.Date();
             System.out.println("Current Date Time : " + dateFormat.format(date1));
			
					ser.ajoutPromotion(t);
		     Dialog.show("Succees", "Succes","OK", null);

				
            

        });
		
		btnaff.addActionListener((e)->{
        AffichagePromotion a=new AffichagePromotion();
        a.getF().show();
        });
		btnDelete.addActionListener((e) -> {
			ServicePromotion ser = new ServicePromotion();
			String a = idSupp.getText();
			int id = Integer.parseInt(a);
			ser.Supprimer(id);
		});
		
		
		
		f.getToolbar().addCommandToRightBar("", theme.getImage("code.png"), e -> {
//            ScanCode scs = new ScanCode();
            Promotion promo = new Promotion();
           // promo = scs.ScanBarCode();
            
            f.add(createRankWidget(promo));
            f.show();
		
		});
       
		Search.addActionListener((e) -> {
			ServicePromotion SP=new ServicePromotion();
			Promotion P=new Promotion();
			 Form F2 = new Form(BoxLayout.y());
            ArrayList<Promotion> liche = SP.ChercherTitre(Titre.getText());
            for (Promotion lis : liche) {
                System.out.println();
                Label aa = new Label("titre  : " + lis.getTitre());
                Label desc = new Label("description  : " + lis.getDescription());
                Label prixx = new Label("Lieu :" + lis.getLieu());
                Label nbpl = new Label("Prix :" + lis.getPrix());
                F2.add(aa);
                F2.add(prixx);
                F2.add(nbpl);
                F2.add(desc);
                
               /* F2.getToolbar().addCommandToLeftBar("Back", theme.getImage("code.png"),n->{
					ProfileForm p=new ProfileForm(theme);
					p.getF().show();
				});*/
                F2.show();
                
            }

		});
		
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }	

	private Component createRankWidget(Promotion promo) {
           return null;
	}
	
	public Date toDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
        } catch (ParseException ex) {
        }
        return null;
    }

}
