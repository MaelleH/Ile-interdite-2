/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.cartesTresor.CarteTrésor;
import ile.interdite.Message;
import ile.interdite.Observateur;
import static Util.TypeMessage.REGLES;
import static Util.TypeMessage.VAL2;
import Vue.panels.PanelCarteTrophee;
import java.awt.BorderLayout;
import static java.awt.BorderLayout.CENTER;
import static java.awt.BorderLayout.NORTH;
import static java.awt.BorderLayout.SOUTH;
import static java.awt.Component.CENTER_ALIGNMENT;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import Util.Couleur;
import javax.swing.SwingConstants;

/**
 *
 * @author heyrendm
 */
public class VueLancement {
    private Observateur controleur;
    private JFrame main;
    
    private Regles regle;

    
    private JPanel welcomeP;
    private JPanel choixP;
    
    
    private JPanel joueurP;
    private JPanel nomJoueurP;
    
    private JPanel j1,j2,j3,j4;
    
    
    
    private ArrayList<String> nomJ = new ArrayList<>();;
    
    private final String[] nbj = new String[]{"2","3","4"};
    private final String[] nivDif= new String[]{"Novice","Normal","Expert","Légendaire"};
    
    private final JComboBox listeNiv = new JComboBox(nbj);
    private final JComboBox listeDif = new JComboBox(nivDif);
   

    public VueLancement(Observateur obs) {
        controleur= obs;
                        
        //Fenetre principale
        main = new JFrame();
        main.setTitle("Bienvenue sur l'île interdite!");
        main.setSize(400, 400);
        
        Font fMain= new Font("Arial", 15, 15);
        main.setFont(fMain);
        
        regle = new Regles();
            //Le Fond
                PanelIleHaut ileHaut = new PanelIleHaut();
                main.setContentPane(ileHaut);   
                

        //Creation du panel principal
            welcomeP = new JPanel(new BorderLayout());
            welcomeP.setAlignmentX(CENTER_ALIGNMENT);
            welcomeP.setAlignmentY(CENTER_ALIGNMENT);
            main.add(welcomeP);
            
            //Creation du label Titre du panel central
            Label titreW = new Label("Bienvenue sur l'île interdite!",SwingConstants.CENTER);
            Font f= new Font("Arial", 18, 18);
            titreW.setFont(f);
            welcomeP.add(titreW,NORTH);
            
            //Creation du panel bouton du panel central
                JPanel valRe= new JPanel(new GridLayout(1, 2));
                welcomeP.add(valRe,SOUTH);    
                //Creation des boutons du panel bouton du panel central
                    //Bouton regles
                        JButton regles = new JButton("Règles");
                        regles.addActionListener((ActionEvent e) -> {
                            regle.setVisible(true);
                        }); 
                        valRe.add(regles);
                        
                    //bouton valider
                        JButton val1 = new JButton("Valider"); 
                        valRe.add(val1); 
                        
                        val1.addActionListener((ActionEvent e) -> {
                            main.remove(welcomeP);
                            main.add(joueurP);
                            j1.setVisible(true);j2.setVisible(true);j4.setVisible(false);j3.setVisible(false);
                            if(nbj[listeNiv.getSelectedIndex()].equals("3")){
                                j3.setVisible(true);j4.setVisible(false);
                            }
                            else if(nbj[listeNiv.getSelectedIndex()].equals("4")){
                                j3.setVisible(true);j4.setVisible(true);
                            }
                            main.repaint();

                        });
                        
                        
            //Creation du panel choix du panel central             
                choixP = new JPanel(new GridLayout(2,1));
                welcomeP.add(choixP);
                //Choix nbj
                    JPanel choixnbj = new JPanel();
                    choixP.add(choixnbj);
                    choixnbj.add(listeNiv);
                    choixnbj.setBorder(BorderFactory.createTitledBorder("Nombre de joueurs :"));
                //Difficulté
                    JPanel choixDif = new JPanel();
                    choixP.add(choixDif);
                    choixDif.setBorder(BorderFactory.createTitledBorder("Difficulté"));
                    // Déclaration de la combobox
                    choixDif.add(listeDif);        
                        
        
        
        
        
        
        
        
        
        
        
        
        
                //Panneau choix du nom des joueurs
                    joueurP = new JPanel(new BorderLayout());
                    
                    //Creation du label Titre du panel joueur
                        Label titreJ = new Label("Choisissez vos noms d'aventuriers : ",SwingConstants.CENTER);
                        titreJ.setFont(f);
                        joueurP.add(titreJ,NORTH);
                    
                    
                    //Creation du panel central
                    nomJoueurP = new JPanel(new GridLayout(2,2));
                    joueurP.add(nomJoueurP,CENTER);
                    
                    //Creation des zones joueurs
                        j1= new JPanel(new GridLayout(1, 2));j1.setVisible(false);
                        j2= new JPanel(new GridLayout(1, 2));j2.setVisible(false);
                        j3= new JPanel(new GridLayout(1, 2));j3.setVisible(false);
                        j4= new JPanel(new GridLayout(1, 2));j4.setVisible(false);

                        nomJoueurP.add(j1);nomJoueurP.add(j2);nomJoueurP.add(j3);nomJoueurP.add(j4);

                        j1.add(new JLabel("Joueur 1 : "));TextField nj1 = new TextField("Joaquim");j1.add(nj1);
                        j2.add(new JLabel("Joueur 2 : "));TextField nj2 = new TextField("Arnaud");j2.add(nj2);
                        j3.add(new JLabel("Joueur 3 : "));TextField nj3 = new TextField("Maëlle");j3.add(nj3);
                        j4.add(new JLabel("Joueur 4 : "));TextField nj4 = new TextField("Killian");j4.add(nj4);
        
        
                    
        
                    //creation d'un panel bouton
                        JPanel valRe2= new JPanel(new GridLayout(1, 3));
                        joueurP.add(valRe2,SOUTH);
                            //BOUTON REGLES
                            JButton regles2 = new JButton("Règles");
                                regles2.addActionListener((ActionEvent e) -> {
                                    regle.setVisible(true);
                                }); 
                            valRe2.add(regles2);
                        
                            //Bouton RETOUR
                                JButton retour = new JButton("retour");
                                retour.addActionListener((ActionEvent e) -> {
                                    main.remove(joueurP);
                                    main.add(welcomeP);
                                    main.repaint();

                                });
                                valRe2.add(retour);
                            //Bouton pour valider les noms des joueurs et lancer le jeu
                                JButton val2 = new JButton("Valider");
                                val2.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        Message m = new Message();

                                        switch (nbj[listeNiv.getSelectedIndex()]) {
                                            case "2":
                                                nomJ.add(nj1.getText());
                                                nomJ.add(nj2.getText());
                                                break;
                                            case "3":
                                                nomJ.add(nj1.getText());
                                                nomJ.add(nj2.getText());
                                                nomJ.add(nj3.getText());
                                                break;
                                            case "4":
                                                nomJ.add(nj1.getText());
                                                nomJ.add(nj2.getText());
                                                nomJ.add(nj3.getText());
                                                nomJ.add(nj4.getText());
                                                break;
                                            default:
                                                break;
                                        }

                                        m.setNivDif(nivDif[listeDif.getSelectedIndex()]);
                                        m.setJoueurs(nomJ);
                                        m.setJoueur(nbj[listeNiv.getSelectedIndex()]);
                                        m.setTypeMessage(VAL2);

                                        controleur.traiterMessage(m);
                                        regle.dispose();
                                        main.dispose();
                                    }
                                });
                                valRe2.add(val2);
                
        //Operation lorsqu'on ferme le main            
        
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        main.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                int reponse = JOptionPane.showConfirmDialog(main,"Voulez vous vraiment quitter?", "Quitter?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                if (reponse==JOptionPane.YES_OPTION){
                    main.dispose();
                    regle.dispose();
                }
            }
            
        });
        
        
    
        main.setVisible(true);
    }

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


