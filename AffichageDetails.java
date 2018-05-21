/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServcePartageHotel;
import com.mycompany.Entite.PartageHotel;
import java.util.ArrayList;

/**
 *
 * @author Omar
 */
public class AffichageDetails {
	 Form f;
    SpanLabel lb;
  private Resources theme;
  Container C=new Container(BoxLayout.y());
    public AffichageDetails(String Nom,String Commentaire,String NoteS,String NoteC,String NoteR,String NoteP) {       
      theme = UIManager.initFirstTheme("/theme_1");
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
		Label Num=new Label(Nom);
		Label Comment=new Label(Commentaire);
		Label Note1=new Label(NoteS);
		Label Note2=new Label(NoteR);
		Label Note3=new Label(NoteC);
		Label Note4=new Label(NoteP);
       
		
		C.add(Num);
		C.add(Comment);
		C.add(Note1);
		C.add(Note2);
		C.add(Note3);
		C.add(Note4);
		
   		f.add(C);
		
	    
		 
		 
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{AffichagePartage h=new AffichagePartage();
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
