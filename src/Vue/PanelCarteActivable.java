/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.Activable;
import Model.CarteTrésorTrophée;
import Model.TypeCarteActivable;
import Model.TypeCarteTresor;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

/**
 *
 * @author ferreijo
 */
public class PanelCarteActivable extends PanelCarteTresor{
    Image image ;
    public PanelCarteActivable(Activable activable){
        super(activable);
        try {
            image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/ImagesCartesTresor/"+getTypeActivable().toString()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(PanelCarteActivable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clicked = !clicked;
                repaint();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            
        int borderWidth = this.getInsets().left+this.getInsets().right;
        int borderHeight = this.getInsets().top+this.getInsets().bottom;
        g.drawImage(image, borderWidth/2, borderHeight/2, this.getWidth()-borderWidth, this.getHeight()-borderHeight, this);
            
        if(clicked){
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.gray,2)));
        }else{
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));                
        }
               
    }

    public TypeCarteActivable getTypeActivable() {
        return ((Activable)super.getCarteTrésor()).getTypeCarteActivable();
    }
    
}
