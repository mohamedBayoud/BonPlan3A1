/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import Entities.Transport;
import java.sql.Date;

/**
 *
 * @author esprit
 */
public class Train extends Transport{
   private int id_admin;
   private float prixPersonne;

    public Train(int id_transport, String villeDepart, 
            String villeArrive, int nbrPlaceDispo, int heureDepart,
            int heureArrive,Date dateDepart,int validation,int signalement,int id_admin,float prixPersonne) {
        super(id_transport, villeDepart, villeArrive, nbrPlaceDispo, heureDepart, heureArrive, dateDepart,validation,signalement);
        this.id_admin=id_admin;
        this.prixPersonne=prixPersonne;
    }
    public Train(){}

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public float getPrixPersonne() {
        return prixPersonne;
    }

    public void setPrixPersonne(float prixPersonne) {
        this.prixPersonne = prixPersonne;
    }
    

    @Override
    public String toString() {
        return super.toString();
    }
    
   
}
