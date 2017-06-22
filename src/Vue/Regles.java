/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Util.Couleur;
import Vue.panels.PanelCarteTrophee;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
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
    private JPanel p4;
    private JPanel p5;
    private JPanel p6;
    private JPanel p7;
    private JPanel p8;
    
    private JButton suivant;
    private JButton pred;
    private String regle1 = "Regle1";
    private String regle2 = "Regle2";
    private String regle3 = "Regle3";
    private String regle4 = "Regle4";
    private String regle5 = "Regle5";
    private String regle6 = "Regle6";
    private String regle7 = "Regle7";
    private String regle8 = "Regle8";
   
    
    public Regles(){
        
        //Fenetre regles
        main = new JFrame();
        main.setTitle("Règles");
        main.setSize(700, 950);
        main.setBackground(Color.WHITE);
        
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
        mainP.setBackground(Couleur.DESERT.getColor());
        
            //Creation du label Titre du panel central
            JLabel titreR = new JLabel("Règles du jeu",SwingConstants.CENTER);
            Font f= new Font("Arial", 20, 20);
            titreR.setFont(f);
            titreR.setForeground(Couleur.VIOLET_FONCE.getColor());
            mainP.add(titreR,NORTH);
            
            //Creation du panel central 1
                p1 = new JPanel(new GridLayout(1,1));
                mainP.add(p1,CENTER);
                p1.setVisible(true);
                p1.add(new PanelBut(regle1));
                    
            //Creation du panel central 2
                p2 = new JPanel(new GridLayout(1,1));
                p2.setVisible(false);
                p2.add(new PanelBut(regle2));
                
            //Creation du panel central 3
                p3 = new JPanel(new GridLayout(1,1));
                p3.setVisible(false);
                p3.add(new PanelBut(regle3));
            
            //Creation du panel central 4
                p4 = new JPanel(new GridLayout(1,1));
                p4.setVisible(false);
                p4.add(new PanelBut(regle4));
            
            //Creation du panel central 5
                p5 = new JPanel(new GridLayout(1,1));
                p5.setVisible(false);
                p5.add(new PanelBut(regle5));
            
            //Creation du panel central 6
                p6 = new JPanel(new GridLayout(1,1));
                p6.setVisible(false);
                p6.add(new PanelBut(regle6));
            
            //Creation du panel central 7
                p7 = new JPanel(new GridLayout(1,1));
                p7.setVisible(false);
                p7.add(new PanelBut(regle7));
            
            //Creation du panel central 8
                p8 = new JPanel(new GridLayout(1,1));
                p8.setVisible(false);
                p8.add(new PanelBut(regle8));
            
                
            //Creation du menu de bouton
            
            JPanel pMenuB= new JPanel(new GridLayout(1, 3));
                        mainP.add(pMenuB,SOUTH);
                            //BOUTON Precedent
                                pred = new JButton("Précedent");
                                pred.setEnabled(false);
                                    pred.addActionListener((ActionEvent e) -> {
                                      if(p2.isVisible()){
                                          p2.setVisible(false);
                                          mainP.remove(p2);

                                          mainP.add(p1,CENTER);
                                          p1.setVisible(true);

                                          pred.setEnabled(false);
                                      }else if(p3.isVisible()){

                                          mainP.remove(p3);
                                          p3.setVisible(false);

                                          mainP.add(p2,CENTER);
                                          p2.setVisible(true);

                                          suivant.setEnabled(true);
                                      }else if(p4.isVisible()){
                                          
                                          mainP.remove(p4);
                                          p4.setVisible(false);

                                          mainP.add(p3,CENTER);
                                          p3.setVisible(true);

                                          suivant.setEnabled(true);
                                      }else if(p5.isVisible()){
                                          
                                          mainP.remove(p5);
                                          p5.setVisible(false);

                                          mainP.add(p4,CENTER);
                                          p4.setVisible(true);

                                          suivant.setEnabled(true);
                                      }else if(p6.isVisible()){
                                          
                                          mainP.remove(p6);
                                          p6.setVisible(false);

                                          mainP.add(p5,CENTER);
                                          p5.setVisible(true);

                                          suivant.setEnabled(true);
                                      }else if(p7.isVisible()){
                                          
                                          mainP.remove(p7);
                                          p7.setVisible(false);

                                          mainP.add(p6,CENTER);
                                          p6.setVisible(true);

                                          suivant.setEnabled(true);
                                      }else if(p8.isVisible()){
                                          
                                          mainP.remove(p8);
                                          p8.setVisible(false);

                                          mainP.add(p7,CENTER);
                                          p7.setVisible(true);

                                          suivant.setEnabled(true);
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
                                    
                                    if(p7.isVisible()){
                                        
                                      p7.setVisible(false);
                                      mainP.remove(p7);
                                      
                                      p8.setVisible(true);
                                      mainP.add(p8,CENTER);
                                      
                                      suivant.setEnabled(false);
                                    }else if(p6.isVisible()){
                                        
                                      p6.setVisible(false);
                                      mainP.remove(p6);
                                      
                                      p7.setVisible(true);
                                      mainP.add(p7,CENTER);
                                      
                                      suivant.setEnabled(true);
                                    }else if(p5.isVisible()){
                                        
                                      p5.setVisible(false);
                                      mainP.remove(p5);
                                      
                                      p6.setVisible(true);
                                      mainP.add(p6,CENTER);
                                      
                                      suivant.setEnabled(true);
                                    }else if(p4.isVisible()){
                                        
                                      p4.setVisible(false);
                                      mainP.remove(p4);
                                      
                                      p5.setVisible(true);
                                      mainP.add(p5,CENTER);
                                      
                                      suivant.setEnabled(true);
                                    }else if(p3.isVisible()){
                                        
                                      p3.setVisible(false);
                                      mainP.remove(p3);
                                      
                                      p4.setVisible(true);
                                      mainP.add(p4,CENTER);
                                      
                                      suivant.setEnabled(true);
                                    }else if(p2.isVisible()){
                                        
                                      p2.setVisible(false);
                                      mainP.remove(p2);
                                      
                                      p3.setVisible(true);
                                      mainP.add(p3,CENTER);
                                      
                                      suivant.setEnabled(true);
                                    }else if(p1.isVisible()){
                                        
                                      p1.setVisible(false);
                                      mainP.remove(p1);
                                      
                                      p2.setVisible(true);
                                      mainP.add(p2,CENTER);
                                      
                                      pred.setEnabled(true);
                                  }
        });
                                pMenuB.add(suivant);
    }
    
    @Override
    public void setVisible(boolean b){
          main.setVisible(b);
          main.setLocationRelativeTo(null);
    }
    
    @Override
    public void dispose(){
          main.dispose();
    }    
    
    public class PanelBut extends JPanel{
        String nom;
        public PanelBut(String nom){
            this.nom=nom;
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

            try {
                Image image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/Imageregles/"+nom+".png"));
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException ex) {
                Logger.getLogger(PanelCarteTrophee.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }
    }
}  

