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
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public class PanelJoueurs extends JPanel{
    
    private JPanel panelHG;
    private JPanel panelHD;
    private JPanel panelBG;
    private JPanel panelBD;

    public PanelJoueurs() { 
        this.setLayout(new GridLayout(5,5));
        
      
        panelHG = new JPanel();
        panelHD = new JPanel();
        panelBG = new JPanel();
        panelBD = new JPanel();
        
        for(int i = 0;i<5;i++){
            add(new JLabel(""));
        }
        add(new JLabel(""));
        add(panelHG);
        add(new JLabel(""));
        add(panelHD);
        add(new JLabel(""));
        for(int i = 0;i<5;i++){
            add(new JLabel(""));
        }
        add(new JLabel(""));
        add(panelBG);
        add(new JLabel(""));
        add(panelBD);
        add(new JLabel(""));
        for(int i = 0;i<5;i++){
            add(new JLabel(""));
        }
        
        
        
    }

    protected void afficherJoueurs() {
        panelHG.setBackground(Color.black);
        panelHD.setBackground(Color.black);
        panelBG.setBackground(Color.black);
        panelBD.setBackground(Color.black);
    }

    
    
    
    
    
}
