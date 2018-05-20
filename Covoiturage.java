/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author esprit
 */
public class Covoiturage extends Transport{
    private int id_client;
    private float prixPersonne;
    public Covoiturage(int id_transport, String villeDepart, String villeArrive, int nbrPlaceDispo, int heureDepart
            , int heureArrive,Date dateDepart,int validation,int signalement,int id_client,float prixPersonne) {
        super(id_transport, villeDepart, villeArrive, nbrPlaceDispo, heureDepart, heureArrive,dateDepart,validation,signalement);
        this.id_client=id_client;
        this.prixPersonne=prixPersonne;
    }

    

    public int getId_client() {
        return id_client;
    }
    public void setId_client(int id_client) {
        this.id_client = id_client;
    }
    /*public float getprixPersonne() {
        return prixPersonne;
    }*/
    public void setprixPersonne(float prixPersonne) {
        this.prixPersonne = prixPersonne;
    }

    
    @Override
    public String toString() {
        return super.toString()+"Covoiturage{" + "id_client=" + id_client + ", prixPersonne=" + prixPersonne + '}';
    }
    @Override
    public float getprixPersonne() 
    {
        
       return super.getprixPersonne()+prixPersonne; 
    }
    
    
    
    
}
