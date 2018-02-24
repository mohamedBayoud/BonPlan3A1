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
   private float classLuxe;
   private float classEco;
   private int id_admin;
    

    public Train(int id_transport, String villeDepart, 
            String villeArrive, int nbrPlaceDispo, int heureDepart,
            int heureArrive,Date dateDepart,
    float classLuxe, float classEco ,int id_admin) {
        super(id_transport, villeDepart, villeArrive, nbrPlaceDispo, heureDepart, heureArrive, dateDepart);
        this.classLuxe = classLuxe;
        this.classEco = classEco;
        this.id_admin=id_admin;
    }
    public Train(){}
    public float getclassLuxe() {
        return classLuxe;
    }

    public float getclassEco() {
        return classEco;
    }

    

    public void setclassLuxe(float prix1ereClasse) {
        this.classLuxe = classLuxe;
    }

    public void setclassEco(float prix2emeClasse) {
        this.classEco = classEco;
    }

    

    @Override
    public String toString() {
        return super.toString()+"Train{" + "prix classe luxe=" + classLuxe + '}';
    }
    
   
}
