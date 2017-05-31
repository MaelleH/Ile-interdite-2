/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
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
        
        for(int i = 0; i<=1;i++){
            for(int j = 0; j<=1;j++){
                int centreDisqueX = ((j*2)+1)* (int) this.getSize().getWidth()/4;
                int centreDisqueY = ((i*2)+1)* (int) this.getSize().getHeight()/4;
                int rayon = (int) (Math.min(centreDisqueX+(centreDisqueX),centreDisqueY+((centreDisqueX)))*0.9);

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
        disqueHG.draw(g);
        disqueHD.draw(g);
        disqueBG.draw(g);
        disqueBD.draw(g);
    }
    
    
    
}
