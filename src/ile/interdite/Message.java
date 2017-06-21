/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ile.interdite;

import Util.TypeMessage;
import Model.Aventuriers.Aventurier;
import Model.cartesTresor.CarteTrésor;
import Util.Coordonnees;
import Vue.panels.PanelCarteTresor;
import Vue.panels.PanelCase;
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
    private ArrayList<CarteTrésor> listeCarteTresorADefausse;
       
    private ArrayList<String> joueurs;
    private String nivDif;
    
    public Message(){}
    
    
    public Message(TypeMessage typeMessage,String champSaisieTxt,String joueur) {
        this.typeMessage = typeMessage;
        this.joueur =joueur;
    }   
    public Message(TypeMessage typeMessage,String champSaisieTxt,String joueur,ArrayList<String> joueurs) {
        this.typeMessage = typeMessage;
        this.joueurs =new ArrayList<>();
        this.joueurs= joueurs;
        this.joueur =joueur;
    }

    
    public TypeMessage getTypeMessage() {
        return typeMessage;
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
     * @return the pC
     */
    public PanelCase getpC() {
        return pC;
    }

    /**
     * @param pC the pC to set
     */
    public void setpC(PanelCase pC) {
        this.pC = pC;
    }

    /**
     * @return the coord
     */
    public Coordonnees getCoord() {
        return coord;
    }

    /**
     * @param coord the coord to set
     */
    public void setCoord(Coordonnees coord) {
        this.coord = coord;
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

    /**
     * @return the nivDif
     */
    public String getNivDif() {
        return nivDif;
    }

    /**
     * @param nivDif the nivDif to set
     */
    public void setNivDif(String nivDif) {
        this.nivDif = nivDif;
    }

    public ArrayList<CarteTrésor> getListeCarteTresorADefausse() {
        return listeCarteTresorADefausse;
    }

    public void setListeCarteTresorADefausse(ArrayList<CarteTrésor> listeCarteTresorADefausse) {
        this.listeCarteTresorADefausse = listeCarteTresorADefausse;
    }

}
