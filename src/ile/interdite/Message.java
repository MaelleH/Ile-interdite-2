/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile.interdite;

import Model.Aventuriers.Aventurier;
import Model.Coordonnees;
import Vue.PanelCase;

/**
 *
 * @author ferreijo
 */
public class Message {
    private TypeMessage typeMessage;
    private PanelCase pC;
    
    private Coordonnees coord;
    private String joueur;

     

    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    

    public void setBtnCliquéTxt(TypeMessage btnCliquéTxt) {
        this.setTypeMessage(btnCliquéTxt);
    }

    
    /**
     * @return the joueur
     */
    public String getJoueur() {
        return joueur;
    }

    /**
     * @param joueur the joueur to set
     */
    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }

    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    public Coordonnees getCoord() {
        return coord;
    }

    public void setCoord(Coordonnees coord) {
        this.coord = coord;
    }

    public PanelCase getpC() {
        return pC;
    }

    public void setpC(PanelCase pC) {
        this.pC = pC;
    }

    
}
