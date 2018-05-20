/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author esprit
 */
public class Participation {
    private int idTransport;
    private int idPersonne;
    private String type;

    public Participation( int idTransport, int idPersonne, String type) {
        this.idTransport = idTransport;
        this.idPersonne = idPersonne;
        this.type = type;
    }

    

    public int getIdTransport() {
        return idTransport;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public String getType() {
        return type;
    }

    

    public void setIdTransport(int idTransport) {
        this.idTransport = idTransport;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }

    public void setType(String type) {
        this.type = type;
    }
    
}
