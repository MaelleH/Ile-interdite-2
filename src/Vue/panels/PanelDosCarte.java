/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public class PanelDosCarte extends JPanel{
    Image dosDeCarte;

    public PanelDosCarte(Image dosDeCarte) {
        this.dosDeCarte = dosDeCarte;
        this.setBorder(BorderFactory.createEmptyBorder(2, 2, 2 ,2));
    }
    
    
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        int borderWidth = this.getInsets().left+this.getInsets().right;
        int borderHeight = this.getInsets().top+this.getInsets().bottom;
        g.drawImage(dosDeCarte,(int) this.getLocation().getX()+(borderWidth/2),(int) this.getLocation().getY()+(borderHeight/2),(int) this.getSize().getWidth()-borderWidth*2, (int) this.getSize().getHeight()-borderHeight, this);
        revalidate();
    }
}
