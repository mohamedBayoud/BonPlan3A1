/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import java.util.Calendar;

/**
 *
 * @author Omar
 */
public class Menu {
	    Form f;
        Button Partage,Promotion,Evenement;
		
		public Menu() 
		{
			        
		f = new Form("Menu");
	    Partage = new Button("Partage");
        Promotion=new Button("Promotion");
		Evenement=new Button("Evenement");
		f.add(Partage);
		f.add(Promotion);
                f.add(Evenement);
	
		Partage.addActionListener((e)->{
        Partage a=new Partage();
        a.getF().show();
        });
		
	    Promotion.addActionListener((e)->{
        PromotionAcceuil a=new PromotionAcceuil();
        a.getF().show();
        });
             Evenement.addActionListener((e)->{
    //    EvenementGui a=new EvenementGui();
//        a.getF().show();
        });
           
		}
		  public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
