/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.mycompany.gui;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.Entite.User;
import static com.mycompany.gui.authentification.connectedUser;
import java.io.IOException;
import java.util.Map;

/**
 * The Login form
 *
 * @author Shai Almog
 */
public class LoginForm extends Form {
    public static User connectedUser;
    Container data;
    Integer userId;
    public LoginForm(Resources theme) {
        super(new BorderLayout(BorderLayout.CENTER_BEHAVIOR_CENTER_ABSOLUTE));
        setUIID("LoginForm");
        Container welcome = FlowLayout.encloseCenter(
                new Label("    Bienvenue ", "BienvenueWhite")
        );
        
        getTitleArea().setUIID("Container");
        
        Image profilePic = theme.getImage("bp.png");
        Image mask = theme.getImage("round-mask.png");
        profilePic = profilePic.fill(mask.getWidth(), mask.getHeight());
        Label profilePicLabel = new Label(profilePic, "ProfilePic");
        profilePicLabel.setMask(mask.createMask());
		
        TextField login = new TextField( TextField.EMAILADDR) ;
        TextField password = new TextField( TextField.PASSWORD) ;
        login.getAllStyles().setMargin(LEFT, 0);
        password.getAllStyles().setMargin(LEFT, 0);
        Label loginIcon = new Label("", "TextField");
        Label passwordIcon = new Label("", "TextField");
        loginIcon.getAllStyles().setMargin(RIGHT, 0);
        passwordIcon.getAllStyles().setMargin(RIGHT, 0);
        FontImage.setMaterialIcon(loginIcon, FontImage.MATERIAL_PERSON_OUTLINE, 3);
        FontImage.setMaterialIcon(passwordIcon, FontImage.MATERIAL_LOCK_OUTLINE, 3);
        
        Button loginButton = new Button("LOGIN");
        loginButton.setUIID("LoginButton");
        loginButton.addActionListener(e -> {
            
            
                ConnectionRequest con = new ConnectionRequest();
                String name = login.getText();
                String pswd = password.getText();
                if (login.getText()=="") {
                    Dialog.show("champ obligatoire", "vous devez taper votre username!!", "OK", "");

                }
                if (password.getText()=="") {
                    Dialog.show("champ obligatoire", "vous devez taper votre password!!", "OK", "");

                }
                con.setUrl("http://localhost/BonPlan1/web/app_dev.php/api/login?login=" + name + "&mdp=" + pswd);

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
                                new ProfileForm(theme,userId,connectedUser.getRole()).show();
                                
                            }

                        } catch (IOException ex) {
                            System.out.println("erreur");
                        }
                    }
                });
                NetworkManager.getInstance().addToQueue(con);

            
        });
        
        
        Button createNewAccount = new Button("");
        createNewAccount.setUIID("");
        
        // We remove the extra space for low resolution devices so things fit better
        Label spaceLabel;
        if(!Display.getInstance().isTablet() && Display.getInstance().getDeviceDensity() < Display.DENSITY_VERY_HIGH) {
            spaceLabel = new Label();
        } else {
            spaceLabel = new Label(" ");
        }
        
        
        Container by = BoxLayout.encloseY(
                welcome,
                profilePicLabel,
                spaceLabel,
                BorderLayout.center(login).
                        add(BorderLayout.WEST, loginIcon),
                BorderLayout.center(password).
                        add(BorderLayout.WEST, passwordIcon),
                loginButton,
                createNewAccount
        );
        add(BorderLayout.CENTER, by);
        
        // for low res and landscape devices
        by.setScrollableY(true);
        by.setScrollVisible(true);
    }
}
