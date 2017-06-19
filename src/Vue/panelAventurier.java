package Vue;


import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import static javax.swing.SwingConstants.CENTER;
import javax.swing.border.MatteBorder;
import Util.Utils.Pion;
import ile.interdite.Controleur;
import ile.interdite.Message;
import ile.interdite.Observateur;
import static ile.interdite.TypeMessage.ALLER;
import static ile.interdite.TypeMessage.ASSECHER;
import static ile.interdite.TypeMessage.AUTREACTION;
import static ile.interdite.TypeMessage.TERMINERTOUR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.border.Border;

 
public class panelAventurier  extends JPanel{
     
    private final String nomAventurier;
    private final String nomJoueur;
    
    
    private final JPanel panelHaut;
    private final JPanel panelCartes;
    private final JPanel panelNomAventurier;
    
    private final JPanel panelBas;
    private final JPanel panelBoutons;
    private final JButton btnAller;
    private final JButton btnAssecher;
    private final JButton btnDonnerCarte;
    private final JButton btnPrendreTresor;
    private final JButton btnTerminerTour;
    
    private Observateur controleur;
    
    public panelAventurier (String nomJoueur, String nomAventurier, Color couleur, Observateur obs,int x,int y){

        this.controleur=obs;
        this.nomAventurier = nomAventurier;
        this.nomJoueur = nomJoueur;
        
        
        this.setLayout(new GridLayout(2, 1));
        this.setSize(x, y);
        

        this.setBackground(new Color(230, 230, 230));
        
        // ====================================Panel Haut===================================
        panelHaut = new JPanel(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(couleur, 10));
        this.add(panelHaut);
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion
        panelNomAventurier = new JPanel();
        panelHaut.add(panelNomAventurier);
        panelNomAventurier.setBackground(couleur);
        panelNomAventurier.add(new JLabel(nomAventurier,SwingConstants.CENTER ),BorderLayout.NORTH);
        // CENTRE : 1 ligne pour position courante
        panelCartes = new JPanel();
        panelHaut.add(panelCartes);
        // ==================================================================================

        // ====================================Panel Bas====================================
        panelBas = new JPanel(new GridLayout(1,2));
        this.add(panelBas);
        // ouest : les boutons
        this.panelBoutons = new JPanel(new GridLayout(3,1));
        panelBas.add(panelBoutons);
        this.panelBoutons.setOpaque(false);
        
        JPanel panel1 = new JPanel(new GridLayout(1, 2));
        this.btnAller = new JButton("Aller") ;
        this.btnAssecher = new JButton("Assecher");
        panel1.add(btnAller);
        panel1.add(btnAssecher);
        panelBoutons.add(panel1);
        
        JPanel panel2 = new JPanel(new GridLayout(1, 2));
        this.btnDonnerCarte = new JButton("Donner Carte") ;
        this.btnPrendreTresor = new JButton("Prendre Trésor") ;
        panel2.add(btnDonnerCarte);
        panel2.add(btnPrendreTresor);
        panelBoutons.add(panel2);
        
        this.btnTerminerTour = new JButton("Terminer Tour") ;
        panelBoutons.add(btnTerminerTour);
        
        
        // ==================================================================================
        
        
        btnAller.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                //controleur.traiterMessage(new Message(ALLER,position.getText(),nomAventurier));
                
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        
        
        btnAssecher.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                           //controleur.traiterMessage(new Message(ASSECHER,position.getText(),nomAventurier)); 
                                        }
                                    }
        );
        
        btnDonnerCarte.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //controleur.traiterMessage(new Message(AUTREACTION,position.getText(),nomAventurier));
                                        }
                                    }
        );
        
        btnPrendreTresor.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //controleur.traiterMessage(new Message(AUTREACTION,position.getText(),nomAventurier));
                                        }
                                    }
        );
        
        btnTerminerTour.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            //controleur.traiterMessage(new Message(TERMINERTOUR,position.getText(),nomAventurier));
                                        }
                                    }
        );
        
    }  

    public String getNomAventurier(){
        return nomAventurier;
    }
    
     

    public void setPosition(String pos) {
        //this.position.setText(pos);
    }

    

    
     
}

 


