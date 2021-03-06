/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Util.Couleur;
import Util.Curseurs;
import Util.Images;
import Util.TypeMessage;
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
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
public class VueLoose  extends JFrame implements Curseurs{
    private Observateur controleur;
    private JFrame main;
    private JPanel mainP;

    public VueLoose(Observateur obs) {
        controleur= obs;
        
        // CReation de la fenetre
        main = new JFrame();
        main.setTitle("Vous avez perdu ... ");
        main.setSize(600, 300);
        main.setBackground(Color.WHITE);
        
        Font fMain= new Font("Arial", 15, 15);
        main.setFont(fMain);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(main,"Quitter le jeu?", "Voulez vous quitter?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    main.dispose();
                }
            }

            
        });
        
        //cursor
        
        Cursor heli =createCurs(Images.heli.getChemin());
        Cursor goutte =createCurs(Images.goutte.getChemin());
        Cursor carte =createCurs(Images.carte.getChemin());
       
        //panel principal
        mainP= new JPanel(new GridLayout(2,1));
        main.add(mainP);
        //Panel haut
        JPanel hautP= new JPanel(new GridLayout(1,3));
        mainP.add(hautP);
        
        
            JLabel firework2 = new JLabel(new ImageIcon(getClass().getResource("/Vue/Imagewin/perduGoutte.gif" )));
            hautP.add(firework2);
            
            //Titre Bravo
            JPanel titreP= new JPanel(new GridLayout(2, 1));
            hautP.add(titreP);
            
            JLabel titreA = new JLabel("Dommage ...",SwingConstants.CENTER);
            JLabel titreG = new JLabel("Vous avez perdu ...",SwingConstants.CENTER);
            Font f= new Font("Arial", 20, 20);
            titreA.setFont(f);
            titreA.setForeground(Couleur.VIOLET_FONCE.getColor());
            titreG.setFont(f);
            titreG.setForeground(Couleur.VIOLET_FONCE.getColor());
            
            titreP.add(titreA);
            titreP.add(titreG);
            
            JLabel firework1 = new JLabel(new ImageIcon(getClass().getResource("/Vue/Imagewin/perduGoutte.gif" )));
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
            rej.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                 Message m = new Message();
                m.setTypeMessage(REJOUER);
                controleur.traiterMessage(m);
                main.dispose();
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                main.setCursor(heli);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                main.setCursor(Cursor.DEFAULT_CURSOR);
            }
        });
               
        bouP.add(rej);

        //Bouton Quitter
            JButton quitter = new JButton("Quitter");
            quitter.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.dispose();
                Message m = new Message();
                m.setTypeMessage(TypeMessage.QUITTER);
                controleur.traiterMessage(m);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                main.setCursor(goutte);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                main.setCursor(Cursor.DEFAULT_CURSOR);
            }
        });
            bouP.add(quitter);
        //Bouton pour menu principal
            JButton mp = new JButton("Menu Principal");
            mp.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                main.dispose();
                Message m = new Message();
                m.setTypeMessage(RELANCERJEU);
                controleur.traiterMessage(m);
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                main.setCursor(carte);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                main.setCursor(Cursor.DEFAULT_CURSOR);
            }
        });
                
        bouP.add(mp);
        main.setVisible(true);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        
        
        
        
        
        
        
    }

    @Override
    public Cursor createCurs(String chemin) {
            Toolkit tk = Toolkit.getDefaultToolkit();

            Image img; 
            try {
                img=tk.getImage(getClass().getResource(chemin));
            } catch (Exception e) {
                img=tk.getImage(getClass().getResource(chemin));
            }
            Cursor c = tk.createCustomCursor(img, new Point(0, 0), chemin);
            return c;
    }
    

    
    
    

}
