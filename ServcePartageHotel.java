/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.PartageHotel;
import com.mycompany.Entite.Task;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Omar
 */
public class ServcePartageHotel {

	 public void ajoutAvisHotel(PartageHotel ta) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Partage/new?commentaire_avis_h=" +ta.getCommentaireAvisH()+"&note_service_h="+ta.getNoteServiceH()+"&note_rapport_h="+ta.getNoteRapportH()+"&note_confort_h="+ta.getNoteConfortH()+"&note_personnel_h="+ta.getNotePersonnelH();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
	
	public ArrayList<PartageHotel> getList2() {
        ArrayList<PartageHotel> listAvis = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Partage/all");
		
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

                        PartageHotel task = new PartageHotel();

                        float id = Float.parseFloat(obj.get("idPartageH").toString());
                       float NoteServiceH = Float.parseFloat(obj.get("NoteServiceH").toString());
						float NoteRapporitH = Float.parseFloat(obj.get("NoteRapportH").toString());
                        float NoteConfortH = Float.parseFloat(obj.get("NoteConfortH").toString());
                        float NotePersonnelH = Float.parseFloat(obj.get("NotePersonnelH").toString());
				       float NomHotel = Float.parseFloat(obj.get("NomHotel").toString());

                       task.setIdPartageH((int)id);
					   task.setCommentaireAvisH(obj.get("commentaireAvisH").toString());
					  task.setNoteServiceH((int)NoteServiceH);
                       task.setNoteRapportH((int)NoteRapporitH);
                       task.setNoteConfortH((int)NoteConfortH);
                       task.setNotePersonnelH((int)NotePersonnelH);
					   task.setNomHotel((int)NomHotel);

                     Map<String, Object> datee = (Map<String, Object>) obj.get("DateCommentaireH");
                    Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                    String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
					                   task.setDate11(formatedDateCom);
					 
					 task.setDateCommentaireH(toDate(obj.get("DateCommentaireH").toString()));
                     
					   listAvis.add(task);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAvis;
    }
	public void Supprimer(int NoteS) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Partage/supprimer/"+NoteS;
        con.setUrl(Url);
        con.removeResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
                   
            //Dialog.show("Succes", "Avis Supprimer avec Succes", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
	
	public ArrayList<PartageHotel> ChercherCommentaire(String d) {
        ArrayList<PartageHotel> listPartage = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Partage/Filtrer/" +d);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    PartageHotel task = new PartageHotel();
                    float id = Float.parseFloat(obj.get("idPartageH").toString());
                    float NoteServiceH = Float.parseFloat(obj.get("NoteServiceH").toString());
						float NoteRapporitH = Float.parseFloat(obj.get("NoteRapportH").toString());
                        float NoteConfortH = Float.parseFloat(obj.get("NoteConfortH").toString());
                        float NotePersonnelH = Float.parseFloat(obj.get("NotePersonnelH").toString());
						                    task.setIdPartageH((int) id);

					   task.setCommentaireAvisH(obj.get("commentaireAvisH").toString());
					  task.setNoteServiceH((int)NoteServiceH);
                       task.setNoteRapportH((int)NoteRapporitH);
                       task.setNoteConfortH((int)NoteConfortH);
                       task.setNotePersonnelH((int)NotePersonnelH);
                      Map<String, Object> datee = (Map<String, Object>) obj.get("DateCommentaireH");
                    Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                    String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
					                    task.setDate11(formatedDateCom);

                      
					   listPartage.add(task);

                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPartage;
    }
	
	
	public ArrayList<PartageHotel> Details(String d) {
        ArrayList<PartageHotel> listPartage = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Partage/Details/"+d);
        con.addResponseListener((NetworkEvent evt) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> tasks = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                System.out.println(tasks);
                List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
                for (Map<String, Object> obj : list) {
                    PartageHotel task = new PartageHotel();
                    //float id = Float.parseFloat(obj.get("idPartageH").toString());
                    float NoteServiceH = Float.parseFloat(obj.get("NoteServiceH").toString());
						float NoteRapporitH = Float.parseFloat(obj.get("NoteRapportH").toString());
                        float NoteConfortH = Float.parseFloat(obj.get("NoteConfortH").toString());
                        float NotePersonnelH = Float.parseFloat(obj.get("NotePersonnelH").toString());
					
						//task.setIdPartageH((int) id);
					   task.setCommentaireAvisH(obj.get("commentaireAvisH").toString());
					  task.setNoteServiceH((int)NoteServiceH);
                       task.setNoteRapportH((int)NoteRapporitH);
                       task.setNoteConfortH((int)NoteConfortH);
                       task.setNotePersonnelH((int)NotePersonnelH);
                      Map<String, Object> datee = (Map<String, Object>) obj.get("DateCommentaireH");
                    Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                    String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
					                    task.setDate11(formatedDateCom);

                      
					   listPartage.add(task);

                }
            } catch (IOException ex) {
            }

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPartage;
    }
	

	
	public Date toDate(String date) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            return sdf.parse(date);
        } catch (ParseException ex) {
        }
        return null;
    }
	private String toDateTime(Date date) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }
}
