package Vue;


import Model.NomTrésor;
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
import ile.interdite.TypeMessage;
import static ile.interdite.TypeMessage.ALLER;
import static ile.interdite.TypeMessage.ASSECHER;
import static ile.interdite.TypeMessage.AUTREACTION;
import static ile.interdite.TypeMessage.TERMINERTOUR;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.border.Border;

 
public class PanelAventurier  extends JPanel{
     
    private final String nomAventurier;
    private final String nomJoueur;
    
    
    private final JPanel panelHaut;
    private final JPanel panelCartes;
    private ArrayList<PanelCarteTresor> listeCarteTresor;
    private final JPanel panelNomAventurier;
    
    private final JPanel panelBas;
    private final JPanel panelBoutons;
    private final JButton btnAller;
    private final JButton btnAssecher;
    private final JButton btnDonnerCarte;
    private final JButton btnPrendreTresor;
    private final JButton btnTerminerTour;
    
    private Observateur controleur;

    
    
    
    
    public PanelAventurier (String nomJoueur, String nomAventurier, Color couleur, Observateur obs){

        this.controleur=obs;
        this.nomAventurier = nomAventurier;
        this.nomJoueur = nomJoueur;
        listeCarteTresor = new ArrayList<>();
        listeCarteTresor.add(new PanelCarteTrophee(NomTrésor.Cristal));
        
        
        this.setLayout(new GridLayout(2, 1));
        

        this.setBackground(new Color(230, 230, 230));
        this.setBorder(BorderFactory.createLineBorder(couleur, 3));
        
        // ====================================Panel Haut===================================
        panelHaut = new JPanel(new BorderLayout());
        panelHaut.setBorder(BorderFactory.createLineBorder(couleur, 3));
        this.add(panelHaut);
        // NORD : le titre = nom de l'aventurier + nom du joueur sur la couleurActive du pion
        panelNomAventurier = new JPanel();
        panelNomAventurier.setBackground(couleur);
        panelNomAventurier.add(new JLabel(nomJoueur+" ("+nomAventurier+")",SwingConstants.CENTER ));
        panelHaut.add(panelNomAventurier,BorderLayout.NORTH);
        // CENTRE : 1 ligne pour position courante
        panelCartes = new JPanel(new GridLayout(1, 9));
        panelCartes.setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
        
        for(PanelCarteTresor panelCartTres : listeCarteTresor){
            panelCartes.add(panelCartTres);
        }
        for(int i = listeCarteTresor.size();i<9;i++){
            panelCartes.add(new JPanel());
        }
        
        panelHaut.add(panelCartes,BorderLayout.CENTER);
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
        
        
        btnAller.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Message m = new Message();
                                            m.setTypeMessage(TypeMessage.PROPOSER_DEPLACEMENT);
                                            controleur.traiterMessage(m);
                                        }
                                    }
        );
        
        
        btnAssecher.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                           Message m = new Message();
                                            m.setTypeMessage(TypeMessage.PROPOSER_ASSECHEMENT);
                                            controleur.traiterMessage(m);
                                        }
                                    }
        );
        
        btnDonnerCarte.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Message m = new Message();
                                            m.setTypeMessage(TypeMessage.PROPOSER_DONATION_CARTE);
                                            controleur.traiterMessage(m);
                                        }
                                    }
        );
        
        btnPrendreTresor.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Message m = new Message();
                                            m.setTypeMessage(TypeMessage.PRENDRETRESOR);
                                            m.setJoueur(nomAventurier);
                                            controleur.traiterMessage(m);
                                        }
                                    }
        );
        
        btnTerminerTour.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            Message m = new Message();
                                            m.setTypeMessage(TypeMessage.TERMINERTOUR);
                                            controleur.traiterMessage(m);
                                        }
                                    }
        );
        
    }  

    
     

    public void setPosition(String pos) {
        //this.position.setText(pos);
    }

    public void setActive(){
        btnAller.setEnabled(true);
        btnAssecher.setEnabled(true);
        btnDonnerCarte.setEnabled(true);
        btnPrendreTresor.setEnabled(true);
        btnTerminerTour.setEnabled(true);
        
    }
    public void setInactive(){
        btnAller.setEnabled(false);
        btnAssecher.setEnabled(false);
        btnDonnerCarte.setEnabled(false);
        btnPrendreTresor.setEnabled(false);
        btnTerminerTour.setEnabled(false);
        
    }

    public String getNomAventurier() {
        return nomAventurier;
    }
    
     
}

 


