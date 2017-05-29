/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile.interdite;

import Model.Aventuriers.Aventurier;

/**
 *
 * @author ferreijo
 */
public class Message {
    private String btnCliquéTxt;
    private String champSaisieTxt;
    private String joueur;

    public Message(String btnCliquéTxt,String champSaisieTxt,String joueur) {
        this.btnCliquéTxt = btnCliquéTxt;
        this.champSaisieTxt = champSaisieTxt;
        this.joueur =joueur;
    }   

    public String getBtnCliquéTxt() {
        return btnCliquéTxt;
    }

    public String getChampSaisieTxt() {
        return champSaisieTxt;
    }

    public void setBtnCliquéTxt(String btnCliquéTxt) {
        this.btnCliquéTxt = btnCliquéTxt;
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
}
