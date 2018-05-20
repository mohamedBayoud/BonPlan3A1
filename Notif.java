/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.util.Objects;

/**
 *
 * @author esprit
 */
public class Notif {
    private int idNotif;
    private String email_client;
    private String ville;

    public Notif(int idNotif, String email_client, String ville) {
        this.idNotif = idNotif;
        this.email_client = email_client;
        this.ville = ville;
    }

    public Notif() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getIdNotif() {
        return idNotif;
    }

    public String getEmail_client() {
        return email_client;
    }

    public void setIdNotif(int idNotif) {
        this.idNotif = idNotif;
    }

    public void setEmail_client(String email_client) {
        this.email_client = email_client;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getVille() {
        return ville;
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
        final Notif other = (Notif) obj;
        if (this.idNotif != other.idNotif) {
            return false;
        }
        if (!Objects.equals(this.email_client, other.email_client)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Notif{" + "idNotif=" + idNotif + ", email_client=" + email_client + ", ville=" + ville + '}';
    }
    
}
