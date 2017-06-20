/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import Util.Utils.NomAventurier;
import java.awt.Color;

/**
 *
 * @author ferreijo
 */
public class KitPanelAventurier {
    private String nomJoueur;
    private NomAventurier nomAventurier;
    private Color couleurAventurier;

    public KitPanelAventurier(String nomJoueur, NomAventurier nomAventurier, Color couleurAventurier) {
        this.nomJoueur = nomJoueur;
        this.nomAventurier = nomAventurier;
        this.couleurAventurier = couleurAventurier;
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
    
    
}
