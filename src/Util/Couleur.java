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
    VERT(new Color(0, 195, 0)),
    BLEU(new Color(55,194,198)),
    ORANGE(new Color(255, 148, 0)),
    VIOLET(new Color(204, 94, 255)),
    JAUNE(new Color(255, 255, 0)) ; 
    
    private Color color;
    Couleur(Color c){
               this.color=c;
        }
}
