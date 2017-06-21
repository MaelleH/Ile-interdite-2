/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import java.awt.Color;

/**
 *
 * @author bracherk
 */
public enum Couleur {
    ROUGE(new Color(255, 0, 0)),
    TOMATE(new Color(255,81,21)),
    MAUVE(new Color(89,79,108)),
    VERT(new Color(0, 195, 0)),
    BLEU(new Color(55,194,198)),
    BLEU_CLAIR(new Color(0, 100, 250)),
    BLEU_FONCE(new Color(10, 10, 200)),
    BLEU_CALICE(new Color(60,130,140)),
    ORANGE(new Color(255, 148, 0)),
    VIOLET(new Color(204, 94, 255)),
    JAUNE(new Color(255, 255, 0)) ,
    JAUNE_GOLDE (new Color(215,169,77)),
    NOIR(new Color(1,13,31)),
    GRIS(new Color(230,230,230)),
    GRIS_CLAIR(new Color(230, 230, 230)),
    DARK_ROUGE(new Color(150,20,20)),
    BLEU_ASSECHEMENT(new Color(66,243,219)),
    DEEP_ROSE(new Color (255,11,117));
    private Color color;
    Couleur(Color c){
               this.color=c;
        }
    
    public Color getColor(){
        return color;
    }
}
