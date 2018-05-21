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
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.mycompagny.Service.ServiceTask;
import com.mycompagny.Service.ServcePartageHotel;
import com.mycompany.Entite.PartageHotel;

import com.mycompany.Entite.Task;
import java.util.ArrayList;

/**
 *
 * @author sana
 */
public class AffichagePartage {

    Form f;
    SpanLabel lb;
  	private Resources theme;
    Container C=new Container();
    public AffichagePartage() {   
		theme = UIManager.initFirstTheme("/theme_1");
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
       // ServiceTask serviceTask=new ServiceTask();
		ServcePartageHotel SP=new ServcePartageHotel();

        ArrayList<PartageHotel> lis=SP.getList2();
       // lb.setText(lis.toString());
		
		 for (PartageHotel lis1 : lis) {
                System.out.println();
                Label Commeentaire = new Label("Commeentaire  : " + lis1.getCommentaireAvisH());
                Label NoteS = new Label("Note Service  : " + lis1.getNoteServiceH());
                Label NoteR = new Label("Note Rapport :" + lis1.getNoteRapportH());
				Label NoteC = new Label("Note Confort :" + lis1.getNoteConfortH());
                Label NoteP = new Label("Note Personnel :" + lis1.getNotePersonnelH());
				Label NomHotel = new Label("Num° de l'Hotel :" + lis1.getNomHotel());


               /* f.add(Commeentaire);
               f.add(NoteS);
                f.add(NoteR);
                f.add(NoteC);
				f.add(NoteP);*/
                f.add(NomHotel);
				
			   NomHotel.addPointerPressedListener((e) -> {
              	
                     AffichageDetails a=new AffichageDetails(NomHotel.getText(),NoteS.getText(),NoteR.getText(),NoteC.getText(),NoteP.getText(),Commeentaire.getText());
					 a.getF().show();
					
			   });

		 }
		 
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{Partage h=new Partage();
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
