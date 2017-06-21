/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Util.Couleur;
import static Util.Couleur.DESERT;
import static Util.TypeMessage.REGLES;
import static Util.TypeMessage.VAL2;
import Vue.panels.PanelCarteTrophee;
import ile.interdite.Message;
import ile.interdite.Observateur;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Color;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    private JButton suivant;
    private JButton pred;
    private String[] listeR = new String[]{"But","Tour","FinW","FinL"};
    
    public Regles(){
        
        //Fenetre regles
        main = new JFrame();
        main.setTitle("Règles");
        main.setSize(700, 800);
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
                p1 = new JPanel(new GridLayout(4,1));
                mainP.add(p1,CENTER);
                p1.setVisible(true);
                //On insere les images des règles
                    for(String i : listeR){
                        p1.add(new PanelBut(i));
                    }
                    

            //Creation du panel central 2
                p2 = new JPanel(new GridLayout(5,1));
                p2.setVisible(false);
                JLabel but2 = new JLabel("2");
                p2.add(but2);
                p2.setBackground(Couleur.DESERT.getColor());
                
            
            
                
                
                
                
                
                
                
                
                
                
            //Creation du panel central 3
                p3 = new JPanel(new GridLayout(5,1));
                p3.setVisible(false);
                JLabel but3 = new JLabel("3");
                p3.add(but3);
            
            
            
            
            
            
            
            
            
            //Creation du menu de bouton
            
            JPanel pMenuB= new JPanel(new GridLayout(1, 3));
                        mainP.add(pMenuB,SOUTH);
                            //BOUTON Pred
                                pred = new JButton("Précedent");
                                pred.setEnabled(false);
                                    pred.addActionListener((ActionEvent e) -> {
                                      if(p2.isVisible()){
                                          p2.setVisible(false);
                                          mainP.remove(p2);

                                          mainP.add(p1,CENTER);
                                          p1.setVisible(true);

                                          pred.setEnabled(false);
                                      }
                                      else if(p3.isVisible()){

                                          mainP.remove(p3);
                                          p3.setVisible(false);

                                          mainP.add(p2,CENTER);
                                          p2.setVisible(true);

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
                                    if(p2.isVisible()){
                                        
                                      p2.setVisible(false);
                                      mainP.remove(p2);
                                      
                                      p3.setVisible(true);
                                      mainP.add(p3,CENTER);
                                      
                                      suivant.setEnabled(false);
                                    }
                                    else if(p1.isVisible()){
                                        
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
    
    
    
    /*
    Messager : Le messager peut donner ses cartes trésor à un aventurier sans être sur la même tuile que celui-ci.
    
    Explorateur : L'explorateur peut se déplacer et assécher à la diagonale de la tuile sur laquelle il se trouve.
    
    Ingénieur : L'ingénieur n'utilise qu'une action si il assèche deux tuiles sans se déplacer entre temps.
    
    Navigateur : Le navigateur bénéficie de 4 actions par tour.
    
    Pilote : Le pilote peut, une fois par tour, se déplacer sur la tuile de son choix.
    
    Plongeur : Le plongeur peut traverser les tuiles inondées et celle coulées si elles rejoingnent une tuile asséchée.
    */
}  

