/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Util.Couleur;
import static Util.TypeMessage.QUITTER;
import static Util.TypeMessage.REJOUER;
import static Util.TypeMessage.RELANCERJEU;
import ile.interdite.Message;
import ile.interdite.Observateur;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.ImageIcon;
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
public class VueWin  extends JFrame{
    private Observateur controleur;
    private JFrame main;
    private JPanel mainP;

    public VueWin(Observateur obs) {
        controleur= obs;
        
        // CReation de la fenetre
        main = new JFrame();
        main.setTitle("Vous avez gagnés!");
        main.setSize(600, 250);
        main.setBackground(Color.WHITE);
        
        Font fMain= new Font("Arial", 15, 15);
        main.setFont(fMain);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(main,"Quitter le jeu?", "VOulez vous quitter?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    main.dispose();
                    Message m = new Message();
                    m.setTypeMessage(QUITTER);
                    controleur.traiterMessage(m);
                }
            }

            
        });
        
        
        Toolkit tk = Toolkit.getDefaultToolkit();

        Image img; 
        try {
            img=tk.getImage(getClass().getResource("/Vue/Imagewin/heli.gif"));
        } catch (Exception e) {
            img=tk.getImage(getClass().getResource("/Vue/Imagewin/heli.gif"));
        }
        Cursor monCurseur = tk.createCustomCursor(img, new Point(0, 0), "heli");
        main.setCursor(monCurseur);
        
        
        
        //panel principal
        mainP= new JPanel(new GridLayout(2,1));
        main.add(mainP);
        //Panel haut
        JPanel hautP= new JPanel(new GridLayout(1,3));
        mainP.add(hautP);
        
            JLabel firework2 = new JLabel(new ImageIcon(getClass().getResource("/Vue/Imagewin/firework.gif" )));
            hautP.add(firework2);
            
            //Titre Bravo
            JPanel titreP= new JPanel(new GridLayout(2, 1));
            hautP.add(titreP);
            
            JLabel titreA = new JLabel("Bravo!",SwingConstants.CENTER);
            JLabel titreG = new JLabel("Vous avez gagné!",SwingConstants.CENTER);
            Font f= new Font("Arial", 20, 20);
            titreA.setFont(f);
            titreA.setForeground(Couleur.VIOLET_FONCE.getColor());
            
            titreG.setFont(f);
            titreG.setForeground(Couleur.VIOLET_FONCE.getColor());
            
            titreP.add(titreA);
            titreP.add(titreG);
            
            JLabel firework1 = new JLabel(new ImageIcon(getClass().getResource("/Vue/Imagewin/firework.gif" )));
            
            hautP.add(firework1);
            
        
        
        
        
        
        
        //Panel bas
        JPanel basP= new JPanel(new GridLayout(2, 1));
        mainP.add(basP);
        
        //Titre panel bas
            JLabel titreB = new JLabel("Voulez vous rejouer?",SwingConstants.CENTER);
            titreB.setFont(f);
            titreB.setForeground(Couleur.VIOLET_FONCE.getColor());
            basP.add(titreB);
            
        
        //Panel bouton
        JPanel bouP= new JPanel(new GridLayout(1, 3));
        basP.add(bouP);
        
        //BOUTON rejouer
        JButton rej = new JButton("Rejouer");
            rej.addActionListener((ActionEvent e) -> {
                Message m = new Message();
                m.setTypeMessage(REJOUER);
                controleur.traiterMessage(m);
                main.dispose();
            }); 
        bouP.add(rej);

        //Bouton Quitter
            JButton quitter = new JButton("Quitter");
            quitter.addActionListener((ActionEvent e) -> {
                main.dispose();
                Message m = new Message();
                m.setTypeMessage(QUITTER);
                controleur.traiterMessage(m);
            });
            bouP.add(quitter);
        //Bouton pour menu principal
            JButton mp = new JButton("Menu Principal");
            mp.addActionListener((ActionEvent e) -> {
                main.dispose();
                Message m = new Message();
                m.setTypeMessage(RELANCERJEU);
                controleur.traiterMessage(m);

            });
            bouP.add(mp);
        main.setVisible(true);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        
        
        
        
        
        
        
    }  
    

}
