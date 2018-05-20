/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

/**
 *
 * @author Asus Pc
 */
public class participerevent {
     private int idEvent;
    private int idPersonne;

    public participerevent(int idEvent, int idPersonne) {
        this.idEvent = idEvent;
        this.idPersonne = idPersonne;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public int getIdPersonne() {
        return idPersonne;
    }

    public void setIdPersonne(int idPersonne) {
        this.idPersonne = idPersonne;
    }
   
    
}
