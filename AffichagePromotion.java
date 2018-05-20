/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
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
	 public AffichagePromotion() {        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
       // ServiceTask serviceTask=new ServiceTask();
		ServicePromotion SP=new ServicePromotion();

        ArrayList<Promotion> lis=SP.getList2();
		 for (Promotion lis1 : lis) {
                System.out.println();
                Label aa = new Label("Titre  : " + lis1.getTitre());
                Label desc = new Label("Description  : " + lis1.getDescription());
                Label prixx = new Label("Lieu :" + lis1.getLieu());
                Label nbpl = new Label("Prix :" + lis1.getPrix());
                f.add(aa);
                f.add(prixx);
                f.add(nbpl);
                f.add(desc);
		 }
          f.getToolbar().addCommandToRightBar("back", null, (ev)->{PromotionAcceuil h=new PromotionAcceuil();
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
