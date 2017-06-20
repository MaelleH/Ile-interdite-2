/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import Util.Parameters;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import javax.swing.JOptionPane;
import Model.Aventuriers.Aventurier;
import Model.CarteInondation;
import Model.CarteTrésor;

/**
 *
 * @author Eric
 */
public class Utils {
 
    public static enum EtatTuile {
        ASSECHEE("Asséchée"), 
        INONDEE("Inondée"),
        COULEE("Coulée");

        final String libelle ;
        
        EtatTuile(String libelle) {
            this.libelle = libelle ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }
        
        public boolean equals(EtatTuile t){
            return this.libelle.equals(t.libelle);
        }
    }

    public static enum Pion {
        ROUGE("Rouge", new Color(255, 0, 0)),
        VERT("Vert", new Color(0, 195, 0)),
        BLEU("Bleu", new Color(55,194,198)),
        ORANGE("Orange", new Color(255, 148, 0)),
        VIOLET("Violet", new Color(204, 94, 255)),
        JAUNE("Jaune", new Color(255, 255, 0)) ;    

        private final String libelle ;
        private final Color couleur ;


        Pion (String libelle, Color couleur) {
            this.libelle = libelle ;
            this.couleur = couleur ;
        }

        @Override
        public String toString() {
            return this.libelle ;
        }

        public Color getCouleur() {
            return this.couleur ;
        }

        static Pion getFromName(String name) {
            if (ROUGE.name().equals(name)) return ROUGE ;
            if (VERT.name().equals(name)) return VERT ;
            if (BLEU.name().equals(name)) return BLEU ;
            if (ORANGE.name().equals(name)) return ORANGE ;
            if (VIOLET.name().equals(name)) return VIOLET ;
            if (JAUNE.name().equals(name)) return JAUNE ;
            return null ;
        }
        public Pion getFromAventName(NomAventurier nomA) {
            if (nomA.toString().equals(NomAventurier.Explorateur.toString())) return ROUGE ;
            if (nomA.toString().equals(NomAventurier.Ingenieur.toString())) return VERT ;
            if (nomA.toString().equals(NomAventurier.Pilote.toString())) return BLEU ;
            if (nomA.toString().equals(NomAventurier.Messager.toString())) return ORANGE ;
            if (nomA.toString().equals(NomAventurier.Plongeur.toString())) return VIOLET ;
            if (nomA.toString().equals(NomAventurier.Navigateur.toString())) return JAUNE ;
            return null ;
        }
    }
    public static enum NomAventurier {
        Aventurier("Aventurier"),
        Explorateur("Explorateur"),
        Pilote("Pilote"),
        Navigateur("Navigateur"),
        Ingenieur("Ingenieur"),
        Plongeur("Plongeur"),
        Messager("Messager");
        
        private String nom;

        private NomAventurier(String nom) {
            this.nom = nom;
        }
        @Override
        public String toString() {
            return this.nom ;
        }
        
    }    

    public static ArrayList<Aventurier> melangerAventuriers(ArrayList<Aventurier> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }
    public static ArrayList<CarteTrésor> melangerCT(ArrayList<CarteTrésor> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }
    public static ArrayList<CarteInondation> melangerCI(ArrayList<CarteInondation> arrayList) {
        if (Parameters.ALEAS) {
            Collections.shuffle(arrayList);
        }
        return arrayList ;
    }
    
    /**
     * Permet de poser une question à laquelle l'utilisateur répond par oui ou non
     * @param question texte à afficher
     * @return true si l'utilisateur répond oui, false sinon
     */
    public static Boolean poserQuestion(String question) {
        System.out.println("Divers.poserQuestion(" + question + ")");
        int reponse = JOptionPane.showConfirmDialog (null, question, "", JOptionPane.YES_NO_OPTION) ;
        System.out.println("\tréponse : " + (reponse == JOptionPane.YES_OPTION ? "Oui" : "Non"));
        return reponse == JOptionPane.YES_OPTION;
    }    
    
    /**
     * Permet d'afficher un message d'information avec un bouton OK
     * @param message Message à afficher 
     */
    public static void afficherInformation(String message) {
        JOptionPane.showMessageDialog(null, message, "Information", JOptionPane.OK_OPTION);
    }
}
