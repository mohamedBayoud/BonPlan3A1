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
import com.mycompany.Entite.Transport;
import com.mycompany.Entite.User;
import com.sun.mail.smtp.SMTPTransport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.Message;

/**
 *
 * @author esprit
 */


public class serviceTransport {
    public ArrayList<Transport> affichageTransport() {
        ArrayList<Transport> listeTransport = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/Transport/affichage");
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
                        Transport tran = new Transport();
                        User p = new User();
                        Map<String, Object> datee = (Map<String, Object>) obj.get("datedepart");
                        Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                        String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
                        float idtransport = Float.parseFloat(obj.get("idtransport").toString());
                        float prixPersonne = Float.parseFloat(obj .get("prixpersonne").toString());
                        float heureDepart = Float.parseFloat(obj.get("heuredepart").toString());
                        float heureArrive = Float.parseFloat(obj.get("heurearrivee").toString());
                      //  float Validation = Float.parseFloat(obj.get("validation").toString());
                        float nbrPlaceDispo = Float.parseFloat(obj.get("nbrplacedispo").toString());
                      //  float Signalement = Float.parseFloat(obj.get("signalement").toString());
                        //float idpersonne = Float.parseFloat(obj.get("idper").toString());
                        //p.setIdPersonne((int)idpersonne);
                        tran.setDateDeparts(formatedDateCom);
                        tran.setIdPersonne(p.getIdPersonne());
                        tran.setId_transport((int)idtransport);
                        tran.setHeureDepart((int) heureDepart);
                        tran.setHeureArrive((int) heureArrive);
                        tran.setNbrPlaceDispo((int) nbrPlaceDispo);
                        tran.setValidation(0);
                        tran.setSignalement(0);
                        tran.setVilleArrive(obj.get("villearrive").toString());
                        tran.setVilleDepart(obj.get("villedepart").toString());
                        listeTransport.add(tran);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeTransport;
    }
     public void ajoutTransport(Transport r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Transport/ajout/" +r.getIdPersonne()+"/" + r.getDateDepart()+ "/" +r.getVilleDepart()+ "/" + r.getVilleArrive()
                + "/" + r.getHeureArrive()+ "/" + r.getHeureDepart()+ "/" + r.getNbrPlaceDispo()+ "/" + r.getPrixPersonne() ;
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
			Dialog.show("Succes", "Votre transport a été ajouté avec succés ! ", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     public ArrayList<Transport> chercherTransport(Transport r) {
        ArrayList<Transport> listeTransport = new ArrayList<>();
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Transport/chercher/" +r.getVilleDepart();
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
                        Transport tran = new Transport();
                        Map<String, Object> datee = (Map<String, Object>) obj.get("datedepart");
                        Date dateC = new Date((new Double((double) datee.get("timestamp"))).longValue() * 1000);
                        String formatedDateCom = new SimpleDateFormat("dd-MM-yy ").format(dateC);
                        float idtransport = Float.parseFloat(obj.get("idtransport").toString());
                        float prixPersonne = Float.parseFloat(obj.get("prixpersonne").toString());
                        float heureDepart = Float.parseFloat(obj.get("heuredepart").toString());
                        float heureArrive = Float.parseFloat(obj.get("heurearrivee").toString());
                      //  float Validation = Float.parseFloat(obj.get("validation").toString());
                        float nbrPlaceDispo = Float.parseFloat(obj.get("nbrplacedispo").toString());
                      //  float Signalement = Float.parseFloat(obj.get("signalement").toString());
                       // tran.setDateDepart((Date)obj.get("datedepart"));
                        tran.setId_transport((int)idtransport);
                        tran.setHeureDepart((int) heureDepart);
                        tran.setHeureArrive((int) heureArrive);
                        tran.setNbrPlaceDispo((int) nbrPlaceDispo);
                        tran.setDateDeparts(formatedDateCom);
                        tran.setValidation(0);
                        tran.setSignalement(0);
                        tran.setPrixPersonne((int)prixPersonne);
                        tran.setVilleArrive(obj.get("villearrive").toString());
                        tran.setVilleDepart(obj.get("villedepart").toString());
                        Label ss = new Label("sss");
                        listeTransport.add(tran);
                    }
                } catch (IOException ex) {
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listeTransport;
    }
     public void Participer(int id,int idper) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Transport/participer/" + idper +"/"+id;
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
          // sendMail();
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void supprimer(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Transport/supprimer/" + id ;
        con.setUrl(Url);
        
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
			Dialog.show("Succes", "Votre transport a été supprimé  ! ", "ok", null);

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void modifierTransport(int id , Transport r) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/BonPlan1/web/app_dev.php/api/Transport/modifier/" +id + "/" + r.getDateDepart()+ "/" +r.getVilleDepart()+ "/" + r.getVilleArrive()
                + "/" + r.getHeureArrive()+ "/" + r.getHeureDepart()+ "/" + r.getNbrPlaceDispo()+ "/" + r.getPrixPersonne() ;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);
			Dialog.show("Succes", "Votre transport a été modifié avec succés ! ", "ok", null);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    public void sendMail()
    {
        try {
                Properties props = new java.util.Properties();
                props.put("mail.transport.protocol", "smtp");
                props.put("mail.smtps.host", "smtp.gmail.com");
                props.put("mail.smtps.auth", "true");
                Session session = Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                msg.setFrom(new InternetAddress("Mot de passe <my_email@myDomain.com>"));
                msg.setRecipients(Message.RecipientType.TO, "bougatef92@gmail.com");
                msg.setSubject("ESPRIT:");
                msg.setSentDate(new Date(System.currentTimeMillis()));
                msg.setText("votre participation a été prise en compte");
                SMTPTransport st = (SMTPTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com","manifessto7@gmail.com","19201920a");
                st.sendMessage(msg, msg.getAllRecipients());
                System.out.println("ServerResponse : " + st.getLastServerResponse());
            } catch (MessagingException ex) {
            }
    }
   

}
