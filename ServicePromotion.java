/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.components.ToastBar;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.PartageHotel;
import com.mycompany.Entite.Promotion;
import com.twilio.converter.Promoter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Omar
 */
public class ServicePromotion {
	
	 public void ajoutPromotion(Promotion ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Promotion/new/" +ta.getTitre()+"/"+ta.getDescription()+"/"+ta.getLieu()+"/"+ta.getImage()+"/"+ta.getDate()+"/"+ta.getPrix();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
	 
	 public ArrayList<Promotion> getList2() {
        ArrayList<Promotion> listPromotions = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Promotion/allPromotion");
		
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(tasks);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                    for (Map<String, Object> obj : list) {

                        Promotion task = new Promotion();

                        float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
						
                       task.setId((int)id);
					   task.setTitre(obj.get("titre").toString());
				       task.setDescription(obj.get("description").toString());
					   task.setLieu(obj.get("lieu").toString());
					   task.setImage(obj.get("image").toString());
					   task.setPrix((int)prix);
                       
                        listPromotions.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotions;
    }
	 public void Supprimer(String titre) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Promotion/supprimer/"+titre;
        con.setUrl(Url);
        con.removeResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

          Dialog.show("Succes", "Promotion Supprimer avec Succes", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
	 
	 public ArrayList<Promotion> ChercherTitre(String d) {
        ArrayList<Promotion> listPromotion = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Promotion/FiltrerTitre/" +d);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Promotion task = new Promotion();
                    float id = Float.parseFloat(obj.get("id").toString());
                        float prix = Float.parseFloat(obj.get("prix").toString());
						
                       task.setId((int)id);
					   task.setTitre(obj.get("titre").toString());
				       task.setDescription(obj.get("description").toString());
					   task.setLieu(obj.get("lieu").toString());
					   task.setPrix((int)prix);
                       
                      
					   listPromotion.add(task);

                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPromotion;
    }

	
	 
	
	
}
