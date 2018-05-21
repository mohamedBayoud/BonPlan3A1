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
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.Reservation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class serviceReservation {

    public ArrayList<Reservation> affichageReservation() {
        ArrayList<Reservation> listeReservation = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/affichage");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                //listTasks = getListTask(new String(con.getResponseData()));
                JSONParser jsonp = new JSONParser();

                try {
                    Map<String, Object> re = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                    System.out.println(re);
                    //System.out.println(tasks);
                    List<Map<String, Object>> list = (List<Map<String, Object>>) re.get("root");
                    for (Map<String, Object> obj : list) {

                        Map<String, Object> datee = (Map<String, Object>) obj.get("dateentree");
                        Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                        String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);

                        Map<String, Object> dateCom = (Map<String, Object>) obj.get("datesortie");
                        Date dateS = new Date((new Double((double) dateCom.get("timestamp"))).longValue() * 1000);
                        String formatedDateS = new SimpleDateFormat("dd-MM-yy").format(dateS);

                        Reservation res = new Reservation();
                        float nbrChambres = Float.parseFloat(obj.get("nbrchambre").toString());
                        float nbrNuits = Float.parseFloat(obj.get("nbrnuit").toString());

                        res.setType(obj.get("type").toString());
                        res.setDateEntreef(formatedDateCom);
                        res.setDatesortief(formatedDateS);

                        res.setNbrChambres((int) nbrChambres);
                        res.setNbrNuits((int) nbrNuits);
                           float idR = Float.parseFloat(obj.get("idreservation").toString());
                        res.setIdReservation((int) idR);
                        listeReservation.add(res);
                        

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeReservation;
    }

    public void ajoutReservation(Reservation r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/ajout/" + r.getDateEntree() + "/" + r.getDateSortie() + "/" + r.getNbrChambres() + "/" + r.getType();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            if (str.trim().equalsIgnoreCase("OK")) {
//                f2.setTitle(tlogin.getText());
//             f2.show();
//            }
//            else{
//            Dialog.show("error", "login ou pwd invalid", "ok", null);
//            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
public void SupprimerReservation(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/SupprimerRes/" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

public void ModifierRes(int id, Reservation res) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/editRes/" + id + "/" + res.getDateEntree()+ "/" + res.getDateSortie() + "/" + res.getNbrChambres() + "/" + res.getType() ;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);

           // Dialog.show("Succes", "Evenement Modifier Avec succes", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
}
