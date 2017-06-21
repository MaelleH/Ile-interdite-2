package Vue.panels;


import Model.cartesTresor.Activable;
import Model.cartesTresor.CarteTrésor;
import Model.cartesTresor.CarteTrésorTrophée;
import Util.TypeCarteTresor;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import ile.interdite.Message;
import ile.interdite.Observateur;
import Util.TypeMessage;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

 
public class PanelAventurier  extends JPanel implements Observateur{
     
    private final String nomAventurier;
    private final String nomJoueur;
    private Color couleur;
    
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

    
    
    
    
    public PanelAventurier (String nomJoueur, String nomAventurier, Color couleur, ArrayList<CarteTrésor> cartes, Observateur obs){

        this.controleur=obs;
        this.nomAventurier = nomAventurier;
        this.nomJoueur = nomJoueur;
        listeCarteTresor = new ArrayList<>();
        this.couleur = couleur;
        
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
        initPanelCarte();
        
        
        
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
        setListeCarteTresor(cartes);
    }  

    /*@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        
        initPanelCarte();
    }*/

    
    
    
    public void setListeCarteTresor(ArrayList<CarteTrésor> cartes){
        listeCarteTresor.clear();
        PanelCarteActivable carteActi;
        PanelCarteTrophee carteTrophee;
        for(CarteTrésor carte : cartes){
            if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Activable)){
                carteActi = new PanelCarteActivable(1,((Activable) carte),this);
                listeCarteTresor.add(carteActi);
            }else if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                carteTrophee = new PanelCarteTrophee(1,(CarteTrésorTrophée) carte);
                listeCarteTresor.add(carteTrophee);
            }
        }
        initPanelCarte();
    }
    
    public void initPanelCarte(){
        panelCartes.removeAll();
        for(PanelCarteTresor panelCartTres : listeCarteTresor){
            panelCartes.add(panelCartTres);
        }
        if(listeCarteTresor.size()<5){
            for(int i = listeCarteTresor.size();i<5;i++){
                panelCartes.add(new JPanel());
            }
        }
        revalidate();
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
        this.setBorder(BorderFactory.createLineBorder(Color.black, 3));
    }
    public void setInactive(){
        btnAller.setEnabled(false);
        btnAssecher.setEnabled(false);
        btnDonnerCarte.setEnabled(false);
        btnPrendreTresor.setEnabled(false);
        btnTerminerTour.setEnabled(false);
        this.setBorder(BorderFactory.createLineBorder(couleur, 3));
    }

    public String getNomAventurier() {
        return nomAventurier;
    }

    @Override
    public void traiterMessage(Message msg) {
        switch(msg.getTypeMessage()){
            case PROPOSER_ASSECHEMENT_SAC :
                msg.setpA(this);
                controleur.traiterMessage(msg);
                break;

            case PROPOSER_DEPLACEMENT_HELICO:
                msg.setpA(this);
                controleur.traiterMessage(msg);
                break;
            
        }
        
    }
    
    
}

 


