/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import Model.CarteTrésor;
import Util.Utils.NomAventurier;
import java.awt.Color;
import java.util.ArrayList;

/**
 *
 * @author ferreijo
 */
public class KitPanelAventurier {
    private String nomJoueur;
    private NomAventurier nomAventurier;
    private Color couleurAventurier;
    private ArrayList<CarteTrésor> cartes;

    public KitPanelAventurier(String nomJoueur, NomAventurier nomAventurier,ArrayList<CarteTrésor> cartes, Color couleurAventurier) {
        this.nomJoueur = nomJoueur;
        this.nomAventurier = nomAventurier;
        this.couleurAventurier = couleurAventurier;
        this.cartes=cartes;
    }

    public String getNomJoueur() {
        return nomJoueur;
    }

    public NomAventurier getNomAventurier() {
        return nomAventurier;
    }

    public Color getCouleurAventurier() {
        return couleurAventurier;
    }

    public ArrayList<CarteTrésor> getCartes() {
        return cartes;
    }
    
    
}
