/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile.interdite;

import Model.Aventuriers.Aventurier;
import Model.Coordonnees;
import Vue.PanelCase;
import java.util.ArrayList;

/**
 *
 * @author ferreijo
 */
public class Message {
    private TypeMessage typeMessage;
    private PanelCase pC;
    
    private Coordonnees coord;
    private String joueur;
    private ArrayList<String> joueurs;
    private String champSaisieTxt;
    
    public Message(TypeMessage typeMessage,String champSaisieTxt,String joueur) {
        this.typeMessage = typeMessage;
        this.champSaisieTxt = champSaisieTxt;
        this.joueur =joueur;
    }   
    public Message(TypeMessage typeMessage,String champSaisieTxt,String joueur,ArrayList<String> joueurs) {
        this.typeMessage = typeMessage;
        this.champSaisieTxt = champSaisieTxt;
        this.joueurs =new ArrayList<>();
        this.joueurs= joueurs;
        this.joueur =joueur;
    }

    public Message() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public PanelCase getpC() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCoord(Coordonnees coordCase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
