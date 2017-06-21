/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import Util.Utils.Pion;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.util.ArrayList;
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
    
    private ArrayList<JPanel> panels;

    public PanelJoueurs() { 
        this.setLayout(new GridLayout(5,5));
        
        
        panels = new ArrayList<>();
      
        panelHG = new JPanel();
        panels.add(panelHG);
        panelHD = new JPanel();
        panels.add(panelHD);
        panelBG = new JPanel();
        panels.add(panelBG);
        panelBD = new JPanel();
        panels.add(panelBD);
        
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

    public void afficherJoueurs(ArrayList<Pion> pionAAfficher) {
        int i = 0;
        
        for(Pion p : pionAAfficher){
            if(p != null){
                panels.get(i).setBackground(p.getCouleur());
                i++;
            }
            
        }
        while (i<4) {            
            panels.get(i).setBackground(null);
            i++;
        }
    }
    

    
    
    
    
    
}
