/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import static Util.Images.heli;
import java.lang.reflect.InvocationTargetException;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author heyrendm
 */
public class PopUpGif extends JWindow{
    
    public PopUpGif(String nom) {
        JLabel firework1 = new JLabel(new ImageIcon(getClass().getResource(heli.getChemin())));     
        add(firework1);
        setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
        setVisible(true);
        firework1.setVisible(true);
        
        try{
            Thread.sleep(5000);
        }catch(InterruptedException e){
            
        }
            dispose();
    }
    




}

   

   
