/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.Service.ServicePaiement;
import com.stripe.exception.APIConnectionException;
import com.stripe.exception.APIException;
import com.stripe.exception.AuthenticationException;
import com.stripe.exception.CardException;
import com.stripe.exception.InvalidRequestException;

/**
 *
 * @author ASUS
 */
public class payment {
    public payment(){
        
        
        Label titre = new Label("                Payment");
        titre.getUnselectedStyle().setAlignment(100);
        titre.getUnselectedStyle().setFgColor(0xFF1414);
        titre.getUnselectedStyle().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_BOLD, Font.SIZE_LARGE));
     Form payer = new Form(BoxLayout.y());
        TextField carte = new TextField();
        carte.setHint("Numero de carte");
         carte.getUnselectedStyle().setFgColor(000000);
        TextField mois = new TextField();
        mois.setHint("Mois d'expiration");
         mois.getUnselectedStyle().setFgColor(000000);
        TextField annee = new TextField();
        annee.setHint("Année d'expiration");
         annee.getUnselectedStyle().setFgColor(000000);
        TextField amount = new TextField();
        amount.setHint("Montant à payer");
         amount.getUnselectedStyle().setFgColor(000000);
        TextField cvc = new TextField();
         cvc.getUnselectedStyle().setFgColor(000000);
        Button valider= new Button("Valider");
        valider.addActionListener((e)->{
            ServicePaiement sp = new ServicePaiement();
            boolean b= false;
            try {
                
                 b =sp.payer(carte.getText(), Integer.parseInt(mois.getText()), Integer.parseInt(annee.getText()), cvc.getText(), Integer.parseInt(amount.getText()), "hello Codename One");
                        } catch (AuthenticationException ex) {
                            System.out.println("authentification paiement impossible");
                        }
                         catch (InvalidRequestException ex)
                         {
                             System.out.println("Requete invalide");}
                        catch (APIConnectionException ex) {
                            
                        } catch (CardException ex) {
                            System.out.println("carte invalide");
                        } catch (APIException ex) {
                            System.out.println("Probleme d'API");
                        }
            System.out.println(b);
        });
        cvc.setHint("cvc");
        payer.add(titre);
        payer.add(carte);
        payer.add(mois);
        payer.add(annee);
        payer.add(amount);
        payer.add(cvc);
        payer.add(valider);
        payer.show();
           payer.getToolbar().addCommandToLeftBar("back", null, (j) -> {
                    Affichage h = new Affichage();
                    h.getF().show();

                });
    
}
}
