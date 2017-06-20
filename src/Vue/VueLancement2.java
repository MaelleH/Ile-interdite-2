/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import ile.interdite.Observateur;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author heyrendm
 */
public class VueLancement2 {
    private Observateur controleur;
    private JFrame main;
    
    private JPanel mainP;
    private JPanel mainPBas;
    private JLabel mainPHaut;
    
    private JPanel welcomeP;
    private JPanel choixP;
    
    
    private JPanel joueurP;
    private JPanel nomJoueurP;
    
    private ArrayList<String> nomJ = new ArrayList<>();;
    
    private final String[] nbj = new String[]{"2","3","4"};
    private final String[] nivDif= new String[]{"Novice","Normal","Expert","Légendaire"};
    
    private final JComboBox listeNiv = new JComboBox(nbj);
    private final JComboBox listeDif = new JComboBox(nivDif);
   

    public VueLancement2(Observateur obs) {
        controleur= obs;
        main = new JFrame();
        //Creation du panel principal avec l'entete et le pied en image
        mainP = new JPanel(new BorderLayout());
        
        PanelTresorBas tresorBas = new PanelTresorBas();
        mainP.add(tresorBas,SOUTH);
        
        PanelIleHaut ileHaut = new PanelIleHaut();
        mainP.add(ileHaut,NORTH);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        welcomeP = new JPanel(new BorderLayout());
        choixP = new JPanel(new BorderLayout());
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(main,"Voulez vous vraiment quitter?", "Quitter?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    main.dispose();
                }
            }
            
        });
        
        
        
        //Fenetre principale
        main.setTitle("Bienvenue sur l'île interdite!");
        main.setSize(500, 500);
        
        main.add(mainP);      
        main.setVisible(true);
    }

    public class PanelTresorBas extends JPanel{
        
        public PanelTresorBas(){
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

            try {
                Image image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/tresors.jpg"));
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException ex) {
                Logger.getLogger(PanelCarteTrophee.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }}
     public class PanelIleHaut extends JPanel{
        
        public PanelIleHaut(){
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.

            try {
                Image image = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/ile.jpg"));
                g.drawImage(image, 0, 0, this.getWidth(), this.getHeight(), this);
            } catch (IOException ex) {
                Logger.getLogger(PanelCarteTrophee.class.getName()).log(Level.SEVERE, null, ex);
            }
        
    }  
    
     }}


