/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author ferreijo
 */
public class Disque {
    private int centreX, centreY, rayon;
    private Color couleur;
    
    public Disque(int centreX, int centreY, int rayon, Color couleur) {
        this.centreX = centreX;
        this.centreY = centreY;
        this.rayon = rayon;
        this.couleur = couleur;
    }
    
    public void draw(Graphics g) {
        g.setColor(couleur);
        g.fillOval(centreX - rayon, centreY - rayon, 2 * rayon, 2 * rayon);
    }
    
    public void setCouleur(Color color){
        this.couleur = color;
    }
}
