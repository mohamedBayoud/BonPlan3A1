/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompagny.Service;

/**
 *
 * @author Omar
 */
	
import com.mycompany.Entite.Promotion;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author bouyo
 */
public class ServiceStat {

    public ArrayList<Promotion> getListvote(String json) {

        ArrayList<Promotion> listProd = new ArrayList<>();

        try {
            //System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> pr = j.parseJSON(new CharArrayReader(json.toCharArray()));
            //System.err.println(pr.values());

            List<Map<String, Object>> list = (List<Map<String, Object>>) pr.get("root");

            for (Map<String, Object> obj : list) {

                //System.err.println(obj.get("1"));
                //listProd.add(new ProdVote((int) Double.parseDouble(obj.get("1").toString()), obj.get("nomp").toString()));
                listProd.add(new Promotion((int) Double.parseDouble(obj.get("1").toString())));

            }

        } catch (IOException ex) {
        }

        return listProd;

    }

    ArrayList<Promotion> listP = new ArrayList<Promotion>();

    public ArrayList<Promotion> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/symphonie/web/app_dev.php/Evenement/NombrAime");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
              //  StatProduit serSt = new StatProduit();
                listP = getListvote(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        //System.out.println(listP);
        return listP;
    }
}
