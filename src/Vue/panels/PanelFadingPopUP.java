/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import java.awt.Color;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.TimeUnit;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

/**
 *
 * @author ferreijo
 */
public class PanelFadingPopUP extends JFrame{
    private JLabel message;
    private Timer timer;
    public PanelFadingPopUP(String message,int x,int y){
        this.message = new JLabel(message,SwingConstants.CENTER);
        this.add(this.message);
        
        this.setSize(200, 50);
        this.setLocation(x-(getHeight()/2), y-(getWidth()/2));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        
        this.setVisible(true);
        int delay = 2000;
        Timer timer = new Timer( delay, new ActionListener(){
          @Override
          public void actionPerformed( ActionEvent e ){
            dispose();
          }
        });
        timer.setRepeats( false );
        timer.start();
        
    }
}
