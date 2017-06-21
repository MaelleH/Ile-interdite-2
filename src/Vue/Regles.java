/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import static Util.TypeMessage.REGLES;
import static Util.TypeMessage.VAL2;
import ile.interdite.Message;
import ile.interdite.Observateur;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author heyrendm
 */
public class Regles extends JFrame{
    private JFrame main;
    private JPanel mainP;
    
    private JPanel p1;
    private JPanel p2;
    private JPanel p3;
    
    private JButton suivant;
    private JButton pred;
    
    public Regles(){
        
        //Fenetre regles
        main = new JFrame();
        main.setTitle("Règles");
        main.setSize(400, 400);
        
        Font fMain= new Font("Arial", 15, 15);
        main.setFont(fMain);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(main,"Sortir des règles?", "Retour au menu",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    main.dispose();
                }
            }
            
        });       
        
        
        //Panel principal
        mainP = new JPanel(new BorderLayout());
        main.add(mainP);
            //Creation du label Titre du panel central
            JLabel titreR = new JLabel("Règles du jeu",SwingConstants.CENTER);
            Font f= new Font("Arial", 18, 18);
            titreR.setFont(f);
            mainP.add(titreR,NORTH);
            
            //Creation du panel central 1
            p1 = new JPanel(new GridLayout(1,5));
            
            
        
        
        
        
            
            
            
            
            
            //Creation du menu de bouton
            
            JPanel pMenuB= new JPanel(new GridLayout(1, 3));
                        mainP.add(pMenuB,SOUTH);
                            //BOUTON Pred
                            pred = new JButton("Précedent");
                            pred.setEnabled(false);
                                pred.addActionListener((ActionEvent e) -> {
                                  if(p2.isEnabled()){
                                      p2.remove(mainP);
                                      p1.add(mainP); 
                                      pred.setEnabled(false);
                                  }
                                  else if(p3.isEnabled()){
                                      p3.remove(mainP);
                                      p2.add(mainP);
                                  }
                                }); 
                            pMenuB.add(pred);
                        
                            //Bouton RETOUR
                                JButton retour = new JButton("Fermer la fenêtre");
                                retour.addActionListener((ActionEvent e) -> {
                                    main.dispose();
                                });
                                pMenuB.add(retour);
                            //Bouton Suivant
                                suivant = new JButton("Suivant");
                                suivant.addActionListener((ActionEvent e) -> {
                                    if(p2.isEnabled()){
                                      p2.remove(mainP);
                                      p3.add(mainP);
                                      suivant.setEnabled(false);
                                  }
                                    else if(p1.isEnabled()){
                                      p1.remove(mainP);
                                      p2.add(mainP);
                                      pred.setEnabled(true);
                                  }
        });
                                pMenuB.add(suivant);
    }
    
    @Override
    public void setVisible(boolean b){
          main.setVisible(b);
    }
}
