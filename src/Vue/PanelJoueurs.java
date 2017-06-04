/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public class PanelJoueurs extends JPanel{
    
    private Disque disqueHG;
    private Disque disqueHD;
    private Disque disqueBG;
    private Disque disqueBD;

    public PanelJoueurs() {
        int centreDisqueX;
        int centreDisqueY;
        int rayon;
                
        this.setBackground(Color.YELLOW);
        
        centreDisqueX =  (int)   (this.getSize().getWidth()/4);
        centreDisqueY = (int)  (this.getSize().getHeight()/4);
        rayon = (int) (Math.min(this.getSize().getWidth()/2,this.getSize().getHeight()/2)*0.9);

                
        disqueHG = new Disque(centreDisqueX, centreDisqueY, rayon, Color.black);
        for(int i = 0; i<=1;i++){
            for(int j = 0; j<=1;j++){
                centreDisqueX =  (int) (((j*2)+1)*  (this.getSize().getWidth()/4));
                centreDisqueY = (int) (((i*2)+1)*  (this.getSize().getHeight()/4));
                rayon = (int) (Double.min(this.getSize().getWidth(),this.getSize().getHeight())*0.45);
                
                if(i==0 && j==0){
                    disqueHG = new Disque(centreDisqueX, centreDisqueY, rayon, Color.black);
                }else if(i==1 && j==0){
                    disqueHD = new Disque(centreDisqueX, centreDisqueY, rayon, Color.black);
                }else if(i==0 && j==1){
                    disqueBG = new Disque(centreDisqueX, centreDisqueY, rayon, Color.black);
                }else if(i==1 && j==1){
                    disqueBD = new Disque(centreDisqueX, centreDisqueY, rayon, Color.black);
                }
            }
        }
        
    }

    
    public void afficherDisques(Graphics g) {
        System.out.println("oui");
        disqueHG.draw(g);
        disqueHD.draw(g);
        disqueBG.draw(g);
        disqueBD.draw(g);
        
    }
    
    
    
}
