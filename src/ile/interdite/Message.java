/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile.interdite;

import Model.Aventuriers.Aventurier;
import java.util.ArrayList;

/**
 *
 * @author ferreijo
 */
public class Message {
    private TypeMessage typeMessage;
    private String champSaisieTxt;
    private String joueur;
    private ArrayList<String> joueurs;
    
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
    
    public TypeMessage getTypeMessage() {
        return typeMessage;
    }

    public String getChampSaisieTxt() {
        return champSaisieTxt;
    }

    public void setBtnCliquéTxt(TypeMessage btnCliquéTxt) {
        this.setTypeMessage(btnCliquéTxt);
    }

    public void setChampSaisieTxt(String champSaisieTxt) {
        this.champSaisieTxt = champSaisieTxt;
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

    /**
     * @param typeMessage the typeMessage to set
     */
    public void setTypeMessage(TypeMessage typeMessage) {
        this.typeMessage = typeMessage;
    }

    /**
     * @return the joueurs
     */
    public ArrayList<String> getJoueurs() {
        return joueurs;
    }

    /**
     * @param joueurs the joueurs to set
     */
    public void setJoueurs(ArrayList<String> joueurs) {
        this.joueurs = joueurs;
    }
}
