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
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
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
public class DetailsPromotion {
	Form f;
	SpanLabel lb;
	private Resources theme;
	String url = "http://localhost/BonPlan1/web/Uploads/images";
	EncodedImage enc;
	URLImage uRLImage;
	Button Supp=new Button("Supprimer");
	Container C=new Container(BoxLayout.y());
	public DetailsPromotion(String titre,String desc,String lieu,String prix,Image image)
	{
		
		f = new Form(titre);
        lb = new SpanLabel("");
        f.add(lb);
		theme = UIManager.initFirstTheme("/theme_1");

		// ServiceTask serviceTask=new ServiceTask();
		
		//Label l1=new Label(titre);
		Label l2=new Label(desc);
		Label l3=new Label(lieu);
		Label l4=new Label(prix);
		Label l5=new Label(image);
		
		C.add(l2);
		C.add(l3);
		C.add(l4);
		C.add(l5);
		C.add(Supp);

		//f.add(l1);
	   f.add(C);
		Supp.addActionListener((e)->{
          ServicePromotion ser = new ServicePromotion();
			/*String a = idSupp.getText();
			int id = Integer.parseInt(a);*/
			ser.Supprimer(titre);
			AffichagePromotion h = new AffichagePromotion();
			h.getF().show();
			
        });
		
		
	f.getToolbar().addCommandToRightBar("back", null, (ev) -> {
			AffichagePromotion h = new AffichagePromotion();
			h.getF().show();
		});
	}
	
		public Form getF() {
		return f;
	}

	public void setF(Form f) {
		this.f = f;
	}
	
	}
	

