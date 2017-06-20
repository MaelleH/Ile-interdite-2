/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.NomTrésor;
import Model.TypeCarteTresor;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ferreijo
 */
public class PanelCarteTrophee extends PanelCarteTresor{
    private NomTrésor nomTrésor;
    public PanelCarteTrophee(NomTrésor nomTrésor){
        super(TypeCarteTresor.Tresor);
        this.nomTrésor = nomTrésor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        try {
            Image image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/ImagesCartesTresor/"+nomTrésor+".png"));
            g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
        } catch (IOException ex) {
            Logger.getLogger(PanelCarteTrophee.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    
}
