/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import Model.cartesTresor.Activable;
import Model.cartesTresor.CarteTrésorTrophée;
import Util.TypeCarteActivable;
import Util.TypeCarteTresor;
import Util.TypeMessage;
import ile.interdite.Message;
import ile.interdite.Observateur;
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
    Observateur controleur;
    Image image ;
    public PanelCarteActivable(int type,Activable activable,Observateur obs){
        super(type,activable);
        controleur = obs;
        try {
            image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/ImagesCartesTresor/"+getTypeActivable().toString()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(PanelCarteActivable.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setClicked(!getClicked()); 
                if(getType()==1){
                    Message m = new Message();
                    if(getTypeActivable().equals(TypeCarteActivable.Helicoptere)){
                        m = new Message();
                        m.setTypeMessage(TypeMessage.PROPOSER_DEPLACEMENT_HELICO);
                        m.setpCA((PanelCarteActivable)e.getSource());
                        controleur.traiterMessage(m);
                    }else if(getTypeActivable().equals(TypeCarteActivable.SacsDeSable)){
                        m = new Message();
                        m.setTypeMessage(TypeMessage.PROPOSER_ASSECHEMENT_SAC);
                        m.setpCA((PanelCarteActivable)e.getSource());
                        controleur.traiterMessage(m);
                    }
                }
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
            
        if(getClicked()){
            setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.black,2)));
        }else{
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));                
        }
        revalidate();
    }

    public TypeCarteActivable getTypeActivable() {
        return ((Activable)super.getCarteTrésor()).getTypeCarteActivable();
    }
    
    
   
}
