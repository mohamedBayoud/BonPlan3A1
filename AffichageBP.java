/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;

import com.mycompagny.Service.serviceReservation;
import com.mycompagny.Service.serviceBP;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Task;
import com.mycompany.Entite.BP;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author sana
 */
public class AffichageBP {
    Form f;
    SpanLabel lb;
    Label ville;
    TextField champs;
    Button chercher,supprimer;
    public AffichageBP() {  
        
        f = new Form();
        lb = new SpanLabel("");
        champs = new TextField();
        chercher=new Button("chercher");
		supprimer = new Button("supprimer");
        ville = new Label();
        f.add(ville);
        //f.add(lb);
        f.add(champs);
        f.add(chercher);
        
        serviceBP sr=new serviceBP();
        ArrayList<BP> lis=sr.affichageBP();
        for(BP li : lis){
           Label aa = new Label();
           aa.setText(" Hôtel: "+li.getNom()+ " ville: "+li.getVille() + " prix: "+li.getPrixNuit());
           String s=li.getNom();
            f.add(aa);
			/****************detail**************************/
            aa.addPointerPressedListener((l) -> {
             serviceBP ser = new serviceBP();
            BP bp = new BP();
            bp.setNom(li.getNom());
            ArrayList<BP> liss=ser.chercheridBP(bp);
            Form f = new Form(BoxLayout.y());
            f.add(lb);
            f.add(supprimer);
            lb.setText(liss.toString());
            
              f.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    AffichageBP h = new AffichageBP();
                    h.getF().show();

                });
                f.show();
               /**************supprimer**************/
                supprimer.addActionListener((e) -> {
                    
                    ser.supprimer(li.getIdBonPlan());
                    });
                
                
               
             
        });
        }
        //lb.setText(lis.toString());
       
         chercher.addActionListener((e) -> {
			 Form f = new Form(BoxLayout.y());
            serviceBP ser = new serviceBP();
            BP bp = new BP();
            bp.setVille(champs.getText());
			f.add(lb);
        
        ArrayList<BP> li=ser.chercherBP(bp);
		lb.setText(li.toString());
		f.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    AffichageBP h = new AffichageBP();
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

}
