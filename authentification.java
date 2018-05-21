/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.SpanButton;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.User;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author rafed
 */
public class authentification {
    TextField username;
    TextField password;
    Form f;
    public static User connectedUser;
    Container icon;
    Container data;
    
    Button login;
    Integer userId;

    public authentification(Resources theme) {
       
        f = new Form(BoxLayout.y());
        icon = new Container();
        data = new Container();
        username = new TextField();
        username.getUnselectedStyle().setFgColor(000000);
        password = new TextField();
        password.getUnselectedStyle().setFgColor(000000);
        password.setConstraint(TextField.PASSWORD);
        login = new Button("LOGIN");
        //email.setDisabledStyle(TextField.);
        data.add(username);
        data.add(password);
        
                login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                
                ConnectionRequest con = new ConnectionRequest();
                String name = username.getText();
                String pswd = password.getText();
                if (username.getText()=="") {
                    Dialog.show("champ obligatoire", "vous devez taper votre username!!", "OK", "");

                }
                if (password.getText()=="") {
                    Dialog.show("champ obligatoire", "vous devez taper votre password!!", "OK", "");

                }
                con.setUrl("http://localhost/symphonie/web/app_dev.php/Evenement/login?login=" + name + "&mdp=" + pswd);

                con.addResponseListener(new ActionListener<NetworkEvent>() {
                    @Override
                    public void actionPerformed(NetworkEvent evt) {

                        try {
                            String json = new String(con.getResponseData());
                            JSONParser j = new JSONParser();
                            System.out.println(json);
                            Map<String, Object> users = j.parseJSON(new CharArrayReader(json.toCharArray()));

                            System.out.println("+++++++++++++++++++" + users.get("username"));
                            if (users.get("mdp").equals(0)) {
                                Dialog.show("Erreur d'authentification", "Verifier votre Nom d'utilisateur ou mot de passe!!", "OK", "Annuler");

                            } else {
                                connectedUser = new User();
                                connectedUser.setIdPersonne(Integer.parseInt(users.get("idpersonne").toString().substring(0,1)));
                                connectedUser.setLogin(users.get("login").toString());
                                connectedUser.setMdp(users.get("mdp").toString());
                                connectedUser.setRole(users.get("role").toString());
                                System.out.println(connectedUser.toString() + "+++++++++////+++++++++"+connectedUser.getIdPersonne());
                                userId = (int) Float.parseFloat(users.get("idpersonne").toString());
                                int x = (int)users.get("idpersonne");
                                new ProfileForm(theme,x,"").show();

                            }

                        } catch (IOException ex) {
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(con);

            }
        });
        data.add(login);
        f.add(data);
        
        //  data.add(reset);

       
    }

    public Form getF() {
        return f;
    }

    public void setF(Form f) {
        this.f = f;
    }
}
