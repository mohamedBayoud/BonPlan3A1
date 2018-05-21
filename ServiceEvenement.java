/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.mycompany.Entite.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 *
 * @author Asus Pc
 */
public class ServiceEvenement {

    public void ajoutEvenement(Evenement ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Event/ajouter/" + ev.getTitre() + "/" + ev.getPrix() + "/" + ev.getDescription() + "/" + ev.getNbPlace() + "/" + ev.getType() + "/" + ev.getLieu() + "/" + ev.getDate() + "/" + ev.getImage();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            Dialog.show("Succes", "Evenement Ajouter Avec succes", "ok", null);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<Evenement> ChercherEvnet(String d) {
        ArrayList<Evenement> listEvent = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Event/chercher/" + d);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Evenement task = new Evenement();
                    task.setImage(obj.get("image").toString());
                    float id = Float.parseFloat(obj.get("idevent").toString());
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    task.setIdEvent((int) id);
                    task.setPrix((float) prix);
                    task.setDescription(obj.get("description").toString());
                    task.setTitre(obj.get("titre").toString());
                    float nbp = Float.parseFloat(obj.get("nbplace").toString());
                    task.setNbPlace((int) nbp);
                    Map<String, Object> datee = (Map<String, Object>) obj.get("date");
                    Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                    String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
                    task.setDate1(formatedDateCom);
                    listEvent.add(task);
                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }

    public void SupprimerEvnet(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Event/delete/" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    
        
public void ModifierEvent(int id, Evenement ev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Event/edit/" + id + "/" + ev.getTitre() + "/" + ev.getPrix() + "/" + ev.getDescription() + "/" + ev.getNbPlace() + "/" + ev.getType() + "/" + ev.getLieu() + "/" + ev.getDate() + "/" + ev.getImage();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

            Dialog.show("Succes", "Evenement Modifier Avec succes", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public Evenement DetailEvnet(int id, Evenement task) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Event/detail/" + id;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                task.setImage(obj.get("image").toString());
                float ide = Float.parseFloat(obj.get("idevent").toString());
                float prix = Float.parseFloat(obj.get("prix").toString());
                task.setIdEvent((int) ide);
                task.setPrix((float) prix);
                task.setDescription(obj.get("type").toString());
                task.setDescription(obj.get("description").toString());
                task.setTitre(obj.get("titre").toString());
                float nbp = Float.parseFloat(obj.get("nbplace").toString());
                task.setNbPlace((int) nbp);
                Map<String, Object> datee = (Map<String, Object>) obj.get("date");
                Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
                task.setDate1(formatedDateCom);

            } catch (IOException ex) {
                System.out.println("error sql");
            }
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return null;
    }

    public ArrayList<Evenement> getList2() {
        ArrayList<Evenement> listEvent = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Event/afficherr");
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {

                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    Evenement task = new Evenement();
                    task.setImage(obj.get("image").toString());
                    float id = Float.parseFloat(obj.get("idevent").toString());
                    float nbp = Float.parseFloat(obj.get("nbplace").toString());
                    task.setNbPlace((int) nbp);
                    task.setTitre(obj.get("titre").toString());
                    float prix = Float.parseFloat(obj.get("prix").toString());
                    task.setIdEvent((int) id);
                    task.setPrix((float) prix);
                    task.setDescription(obj.get("description").toString());
                    Map<String, Object> datee = (Map<String, Object>) obj.get("date");
                    Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                    String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
                    task.setDate1(formatedDateCom);
                    listEvent.add(task);

                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvent;
    }

    public void participer(int idev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Event/participer/" + idev;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void annuler(int idev) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Event/annuler/" + idev;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}