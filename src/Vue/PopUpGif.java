/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 *
 * @author heyrendm
 */
public class PopUpGif extends JWindow{
    
    public PopUpGif(String nom) {
        JLabel firework1 = new JLabel(new ImageIcon(getClass().getResource(nom)));     
        this.add(firework1);
        this.setSize(300, 200);//On lui donne une taille pour qu'on puisse la voir
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }       
}

   

   
