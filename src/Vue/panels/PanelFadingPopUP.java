/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import javax.swing.JPanel;
import javax.swing.JLabel;

/**
 *
 * @author ferreijo
 */
public class PanelFadingPopUP extends JPanel{
    private JLabel message;
    
    public PanelFadingPopUP(String message){
        this.message = new JLabel(message);
        this.add(this.message);
    }
}
