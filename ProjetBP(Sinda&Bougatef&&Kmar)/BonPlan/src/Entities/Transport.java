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
public class Transport {
    protected int id_transport;
    protected String villeDepart;
    protected String villeArrive;
    protected int nbrPlaceDispo;
    protected int heureDepart;
    protected int heureArrive;
    protected Date dateDepart;

    public Transport(int id_transport, String villeDepart, String villeArrive, int nbrPlaceDispo,
            int heureDepart, int heureArrive, Date dateDepart) {
        this.id_transport = id_transport;
        this.villeDepart = villeDepart;
        this.villeArrive = villeArrive;
        this.nbrPlaceDispo = nbrPlaceDispo;
        this.heureDepart = heureDepart;
        this.heureArrive = heureArrive;
        this.dateDepart = dateDepart;
    }
    public Transport(){}

    public Date getDateDepart() {
        return dateDepart;
    }

    public void setDateDepart(Date dateDepart) {
        this.dateDepart = dateDepart;
    }
    

    public int getId_transport() {
        return id_transport;
    }

    public String getVilleDepart() {
        return villeDepart;
    }

    public String getVilleArrive() {
        return villeArrive;
    }

    public int getNbrPlaceDispo() {
        return nbrPlaceDispo;
    }

    public int getHeureDepart() {
        return heureDepart;
    }

    public int getHeureArrive() {
        return heureArrive;
    }
    
    public void setId_transport(int id_transport) {
        this.id_transport = id_transport;
    }

    public void setVilleDepart(String villeDepart) {
        this.villeDepart = villeDepart;
    }

    public void setVilleArrive(String villeArrive) {
        this.villeArrive = villeArrive;
    }

    public void setNbrPlaceDispo(int nbrPlaceDispo) {
        this.nbrPlaceDispo = nbrPlaceDispo;
    }

    public void setHeureDepart(int heureDepart) {
        this.heureDepart = heureDepart;
    }

    public void setHeureArrive(int heureArrive) {
        this.heureArrive = heureArrive;
    }
   

    @Override
    public String toString() {
        return "Transport{" + "id_transport=" + id_transport + ", villeDepart=" + villeDepart + ", villeArrive=" + villeArrive + ", nbrPlaceDispo=" + nbrPlaceDispo + ", heureDepart=" + heureDepart + ", heureArrive=" + heureArrive + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Transport other = (Transport) obj;
        if (this.id_transport != other.id_transport) {
            return false;
        }
        return true;
    }

   public float getprixPersonne() {
       return 0;
    }

    

   
}
