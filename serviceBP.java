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
import com.codename1.ui.Label;
import com.codename1.ui.events.ActionListener;
import com.mycompany.Entite.BP;
import com.mycompany.Entite.Evenement;
import com.mycompany.Entite.Reservation;
import com.mycompany.Entite.Transport;
import com.sun.mail.smtp.SMTPTransport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author ASUS
 */
public class serviceBP {

    public ArrayList<BP> affichageBP() {
        ArrayList<BP> listeBP = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/bp/afficher");
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
                        BP bp = new BP();
						float id=Float.parseFloat(obj.get("idbonplan").toString());
						float prix = Float.parseFloat(obj.get("prixnuit").toString());
						bp.setPhoto(obj.get("photo").toString());
                        bp.setIdBonPlan((int)id);
						bp.setPrixNuit((int)prix);
						bp.setNom(obj.get("nom").toString());
						bp.setPhoto(obj.get("photo").toString());
						bp.setDescription(obj.get("description").toString());
						bp.setAdresse(obj.get("adresse").toString());
						bp.setVille(obj.get("ville").toString());
						bp.setType(obj.get("type").toString());
						


                        listeBP.add(bp);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeBP;
    }
      public void ajoutBP(BP bp) {
        ConnectionRequest con = new ConnectionRequest();
		bp.setValide(0);
		bp.setType("sejour");
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/bp/Bpajouter/" + bp.getNom()+ "/" + bp.getPhoto()+ "/" + bp.getDescription()+ "/" + bp.getAdresse()+ "/" + bp.getVille()+ "/" + bp.getNbreChambreDispo()+ "/" + bp.getPrixNuit();
        con.setUrl(Url);

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
			Dialog.show("Succes", "Bon Plan a été ajouté avec succés ! ", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
	  
	  public ArrayList<BP> chercherBP(BP bp) {
        ArrayList<BP> listeBP = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/bp/chercher/" +bp.getVille();
        con.setUrl(Url);
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
                        BP bp = new BP();
                        bp.setPhoto(obj.get("photo").toString());
                        float ide=Float.parseFloat(obj.get("idbonplan").toString());
						float price = Float.parseFloat(obj.get("prixnuit").toString());
						bp.setPrixNuit((int)price);
						bp.setPhoto(obj.get("photo").toString());
                        bp.setIdBonPlan((int)ide);
						bp.setNom(obj.get("nom").toString());
						bp.setPhoto(obj.get("photo").toString());
						bp.setDescription(obj.get("description").toString());
						bp.setAdresse(obj.get("adresse").toString());
						bp.setVille(obj.get("ville").toString());
						bp.setType(obj.get("type").toString());
						
						


                        listeBP.add(bp);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeBP;
    }
     
    public void supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/bp/supprimer/" + id ;
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
//            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
public ArrayList<BP> chercheridBP(BP bp) {
        ArrayList<BP> listeBP = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/bp/cherchernom/" +bp.getNom();
        con.setUrl(Url);
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
                        BP bp = new BP();
                        float id=Float.parseFloat(obj.get("idbonplan").toString());
						bp.setPhoto(obj.get("photo").toString());
                        bp.setIdBonPlan((int)id);
						bp.setNom(obj.get("nom").toString());
						bp.setPhoto(obj.get("photo").toString());
						bp.setDescription(obj.get("description").toString());
						bp.setAdresse(obj.get("adresse").toString());
						bp.setVille(obj.get("ville").toString());
						bp.setType(obj.get("type").toString());
						


                        listeBP.add(bp);

                    }
                } catch (IOException ex) {
                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeBP;
    }

public BP DetailBp(int id, BP task) {

        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/bp/detail/" + id;
        con.setUrl(Url);
        con.addResponseListener((NetworkEvent e) -> {
            JSONParser jsonp = new JSONParser();
            try {
                Map<String, Object> obj = jsonp.parseJSON(new CharArrayReader(new String(con.getResponseData()).toCharArray()));
                BP bp = new BP();
				        task.setPhoto(obj.get("photo").toString());
                        float ide=Float.parseFloat(obj.get("idbonplan").toString());
						bp.setPhoto(obj.get("photo").toString());
                        bp.setIdBonPlan((int)id);
						bp.setNom(obj.get("nom").toString());
						bp.setPhoto(obj.get("photo").toString());
						bp.setDescription(obj.get("description").toString());
						bp.setAdresse(obj.get("adresse").toString());
						bp.setVille(obj.get("ville").toString());
						bp.setType(obj.get("type").toString());
						

            } catch (IOException ex) {
                System.out.println("error sql");
            }
            String str = new String(con.getResponseData());
            System.out.println(str);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return null;
    }
public void modifierBP(int id , BP r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/bp/modifier/" +id + "/" + r.getNom()+ "/" + r.getDescription()
                + "/" + r.getAdresse()+ "/" + r.getVille();
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
			Dialog.show("Succes", "Bon Plan a été modifié avec succés ! ", "ok", null);
//            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
public void sendMail()
    {
        try {
                System.out.println("d5al");
                Properties props = new java.util.Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");
                Session session = Session.getInstance(props, null);
               
                MimeMessage msg = new MimeMessage(session);
                
                msg.setFrom(new InternetAddress("Mot de passe <my_email@myDomain.com>"));
                msg.setRecipients(RecipientType.TO, "sami.mefteh@esprit.tn");
                msg.setSubject("ESPRIT Cupcake: "+"Votre commande est comfirmÈ");
                msg.setSentDate(new Date(System.currentTimeMillis()));
                msg.setText("Commande passÈ");
                
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com","sami.mefteh@esprit.tn","28823951");
                st.sendMessage(msg, msg.getAllRecipients());
                System.out.println("ServerResponse : " + st.getLastServerResponse());
                System.out.println("kamel");
          
            } catch (MessagingException ex) {
            }
                            
    }
}
