/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.SpanLabel;
import com.codename1.ui.Form;
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
  
    public AffichagePartage() {        
        f = new Form();
        lb = new SpanLabel("");
        f.add(lb);
       // ServiceTask serviceTask=new ServiceTask();
		ServcePartageHotel SP=new ServcePartageHotel();

        ArrayList<PartageHotel> lis=SP.getList2();
        lb.setText(lis.toString());
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
