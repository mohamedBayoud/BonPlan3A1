/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;
import java.util.Objects;

/**
 *
 * @author ASUS
 */
public class bonplan {
    private String nomH;
    private int prix;

    public bonplan(String nomH,int prix) {
        this.nomH = nomH;
        this.prix = prix;
    }

    public String getNomH() {
        return nomH;
    }

    public void setNomH(String nomH) {
        this.nomH = nomH;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }
    
    
}
