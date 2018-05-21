/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
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
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServicePromotion;
import com.mycompany.Entite.Promotion;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class AffichagePromotion {

	Form f;
	SpanLabel lb;
	private Resources theme;
	String url = "http://localhost/BonPlan1/web/Uploads/images";
	EncodedImage enc;
	URLImage uRLImage;
	Button Search;
	TextField Titre;
  Container C=new Container(BoxLayout.y());
	public AffichagePromotion() {
		f = new Form();
		lb = new SpanLabel("");
		theme = UIManager.initFirstTheme("/theme_1");
		Titre=new TextField();
		Search=new Button("Search");

		f.add(lb);
		// ServiceTask serviceTask=new ServiceTask();
		ServicePromotion SP = new ServicePromotion();

		ArrayList<Promotion> lis = SP.getList2();
		for (Promotion lis1 : lis) {
			ImageViewer imgV = new ImageViewer();
			Image placeholder = Image.createImage(180, 150);
			EncodedImage enc = EncodedImage.createFromImage(placeholder, false);
			URLImage urlim = URLImage.createToStorage(enc, url + "/" + lis1.getImage(), url + "/" + lis1.getImage());
			imgV.setImage(urlim);
			System.out.println();
			System.out.println(url + "/" + lis1.getImage());
			Label aa = new Label(lis1.getTitre());
			Label desc = new Label("Description  : " + lis1.getDescription());
			Label prixx = new Label("Lieu :" + lis1.getLieu());
			Label nbpl = new Label("Prix :" + lis1.getPrix());

			C.add(imgV);
			C.add(aa);
			//f.add(imgV);
			//f.add(aa);
			/*f.add(prixx);
			f.add(nbpl);
			f.add(desc);*/
			
			aa.addPointerPressedListener(new ActionListener() {
			@Override
            public void actionPerformed(ActionEvent evt) {
			DetailsPromotion DP=new DetailsPromotion(aa.getText(),desc.getText(),prixx.getText(),nbpl.getText(),imgV.getImage());
		    DP.getF().show();
			}
			});
			}
			
		
		f.getToolbar().addCommandToLeftBar("back", null, (ev) -> {
			PromotionAcceuil h = new PromotionAcceuil();
			h.getF().show();
		});
		
		f.getToolbar().addCommandToOverflowMenu("Ajout", null, (ev)-> {

			PromotionAcceuil P=new PromotionAcceuil();
			P.getF().show();
		});
		f.getToolbar().addCommandToOverflowMenu("Logout", null, (ev)->{f.showBack();}); 

		C.add(Titre);
		C.add(Search);
		//f.add(Titre);
		//f.add(Search);
		f.add(C);
		Search.addActionListener((e) -> {
			Promotion P=new Promotion();
			 Form F2 = new Form(BoxLayout.y());
            ArrayList<Promotion> liche = SP.ChercherTitre(Titre.getText());
            for (Promotion lis1 : liche) {
                System.out.println();
                Label aa = new Label("titre  : "+ lis1.getTitre());
                Label desc = new Label("description  : " + lis1.getDescription());
                Label prixx = new Label("Lieu :" + lis1.getLieu());
                Label nbpl = new Label("Prix :" + lis1.getPrix());
                F2.add(aa);
                F2.add(prixx);
                F2.add(nbpl);
                F2.add(desc);
           F2.getToolbar().addCommandToRightBar("back", null, (ev)->{PromotionAcceuil h=new PromotionAcceuil();
          h.getF().show();
          });
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
}
