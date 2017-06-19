/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Panel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author heyrendm
 */
public class VueLancement {
    private JFrame main;
    private JPanel mainP;
    
    private JPanel welcomeP;
    private JPanel nomJoueurP;
    

   

    public VueLancement() {
        main = new JFrame();
        mainP = new JPanel(new BorderLayout());
        welcomeP = new JPanel();
        nomJoueurP = new JPanel();
        
        //Titre 
        JLabel bv= new JLabel("WELCOME");
        bv.setSize(300,50);
        Font font;
        font = new Font("Arial",Font.BOLD,25);
        bv.setFont(font);
        
        
        
        

        main.setTitle("Préparation du jeu ...");
        main.setSize(300, 600);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        main.add(mainP);
        //Panneau de depart
        mainP.add(welcomeP,BorderLayout.CENTER);
        welcomeP.setBackground(Color.ORANGE);
        
        //Panneau choix du nom des joueurs
        mainP.add(nomJoueurP,BorderLayout.SOUTH);
        
        
        welcomeP.add(bv);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        main.setVisible(true);
    }
    
    
    
    
}
