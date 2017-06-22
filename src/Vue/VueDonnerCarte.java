/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.Aventuriers.Aventurier;
import Model.Aventuriers.Explorateur;
import Model.Aventuriers.Pilote;
import Model.Aventuriers.Plongeur;
import Vue.panels.PanelCarteTresor;
import Vue.panels.PanelCarteTrophee;
import Vue.panels.PanelCarteActivable;
import Model.cartesTresor.CarteTrésor;
import Model.cartesTresor.CarteTrésorTrophée;
import Util.Coordonnees;
import Util.NomTrésor;
import Util.TypeCarteTresor;
import Util.TypeMessage;
import Util.Utils;
import ile.interdite.Message;
import ile.interdite.Observateur;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ferreijo
 */
public class VueDonnerCarte implements Observateur{
    private Observateur controleur;
    
    private JFrame window;
    private JPanel mainPanel;
    
    private JPanel panelIndications;
    private JLabel labelIndications;
    
    private JPanel panelCentre;
    private JPanel panelCartes;
    private JPanel panelJoueurs;
    private HashMap<Aventurier,JPanel> listesPanelsAventuriers;
    private ArrayList<PanelCarteTrophee> listeCarteTresor;
    private Aventurier joueurChoisi;

    private JPanel panelValidation;
    private JButton boutonValidation;
    private JButton boutonAnnulation;
    public VueDonnerCarte(ArrayList<CarteTrésor> cartes,ArrayList<Aventurier> aventuriers,int x,int y,Observateur obs) {
        listeCarteTresor = new ArrayList<>();
        listesPanelsAventuriers = new HashMap<>();
        controleur = obs;
        
        int height = 400;
        int width = 300;
        
        int posX = x-(width/2);
        int posY = y-(height/2);
        
        
        this.window = new JFrame();
        window.setLocation(posX, posY);
        window.setSize(width, height);
        window.setTitle("Donner Carte");
        
        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK,2));
        window.add(mainPanel);
        
        //Partie Nord = Instructions
        panelIndications = new JPanel();
        panelIndications.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.black));
        labelIndications = new JLabel("Séléctionnez la carte et son receveur!");
        
        
        panelIndications.add(labelIndications);
        mainPanel.add(panelIndications,BorderLayout.NORTH);
        
        
        
        //Partie Centre = Selection
        panelCentre = new JPanel(new BorderLayout());
        
        panelCartes = new JPanel(new GridLayout(3, 3));
        panelCartes.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.gray, 2)));
        //panelCartes.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        
        panelJoueurs = new JPanel(new GridLayout(1, 3));
        panelJoueurs.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3), BorderFactory.createLineBorder(Color.gray, 2)));
        
        panelCentre.add(panelJoueurs,BorderLayout.SOUTH);
        panelCentre.add(panelCartes,BorderLayout.CENTER);
        mainPanel.add(panelCentre,BorderLayout.CENTER);
        
        setListeCarteTresor(cartes);
        initPanelCarte();
        setListeAventuriers(aventuriers);
                
        //Partie Sud = VALIDATION
        panelValidation = new JPanel();
        panelValidation.setBorder(BorderFactory.createMatteBorder(2, 0, 0, 0, Color.black));
        mainPanel.add(panelValidation,BorderLayout.SOUTH);
        
        boutonValidation = new JButton("Donner");
        boutonAnnulation = new JButton("Annuler");
        panelValidation.add(boutonValidation);
        panelValidation.add(boutonAnnulation);
        boutonValidation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypeMessage.DONNERCARTE_Donner);
                m.setAventurierRecepteur(joueurChoisi);
                m.setCTTrophée((CarteTrésorTrophée) getCarteClicked());
                controleur.traiterMessage(m);
                window.dispose();
            }
        });
        boutonAnnulation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Message m = new Message();
                m.setTypeMessage(TypeMessage.DONNERCARTE_Annuler);
                
                controleur.traiterMessage(m);
                window.dispose();
            }
        });
        
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setUndecorated(true);
        window.setVisible(true);
    }
    public void dispose(){
        window.dispose();
    }
    
    public void setListeCarteTresor(ArrayList<CarteTrésor> cartes){
        listeCarteTresor.clear();
        PanelCarteActivable carteActi;
        PanelCarteTrophee carteTrophee;
        for(CarteTrésor carte : cartes){
            if(carte.getTypeCarteTresor().equals(TypeCarteTresor.Tresor)){
                carteTrophee = new PanelCarteTrophee(2,((CarteTrésorTrophée) carte),this);
                listeCarteTresor.add(carteTrophee);
            }
        }
    }
    
    public CarteTrésor getCarteClicked(){
        for(PanelCarteTrophee pct : listeCarteTresor){
            if(pct.getClicked()){
                return pct.getCarteTrésor();
            }
        }
        return null;
    }
    
    
    
    public void initPanelCarte(){
        panelCartes.removeAll();
        int j = 1;
        for(PanelCarteTresor panelCartTres : listeCarteTresor){
            if(j==1){
               panelCartTres.setClicked(true); 
               j=0;
            }
            panelCartes.add(panelCartTres);
            
        }
        if(listeCarteTresor.size()<5){
            for(int i = listeCarteTresor.size();i<5;i++){
                panelCartes.add(new JPanel());
            }
        }
    }
    
    public void setListeAventuriers(ArrayList<Aventurier> aventuriers){
        listesPanelsAventuriers.clear();
        JPanel panelAvent;
        
        int i = 1;
        for(Aventurier avent : aventuriers){
            panelAvent = new JPanel();
            if(i==1){
                setJoueurChoisi(avent);
                i=0;
                panelAvent.setBorder(BorderFactory.createLineBorder(Color.black,2));
            }else{
                panelAvent.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            }
            
            panelAvent.setBackground(Utils.Pion.JAUNE.getFromAventName(avent.getNom()).getCouleur());
            panelAvent.add(new JLabel(avent.getNom().toString(),SwingConstants.CENTER));
            
            panelAvent.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    joueurChoisi = getJoueurFromPanel((JPanel)e.getSource());
                    for (Aventurier avent : listesPanelsAventuriers.keySet()) {
                        if(joueurChoisi==avent){
                            getPanelFromJoueur(avent).setBorder(BorderFactory.createLineBorder(Color.black,2));
                        }else{
                            getPanelFromJoueur(avent).setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                        }
                    }
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
            
            
            
            listesPanelsAventuriers.put(avent, panelAvent);
            panelJoueurs.add(panelAvent);
        }
    }

    
    
    public void setJoueurChoisi(Aventurier joueurChoisi) {
        this.joueurChoisi = joueurChoisi;
    }
    
    public Aventurier getJoueurFromPanel(JPanel panel){
        for (Aventurier e : listesPanelsAventuriers.keySet()) {
            if(listesPanelsAventuriers.get(e).equals(panel)){
                return e;
            }
        }
        return null;
    }
    
    public JPanel getPanelFromJoueur(Aventurier avent){
        return listesPanelsAventuriers.get(avent);
    }
    
    
    //Méthode principale (pour les tests)
    /*public static void main(String [] args) {
        // Instanciation de la fenêtre 
        ArrayList<CarteTrésor> listeCartes = new ArrayList<>();
        CarteTrésorTrophée carte = new CarteTrésorTrophée(NomTrésor.Calice);
        listeCartes.add(carte);
        carte = new CarteTrésorTrophée(NomTrésor.Cristal);
        listeCartes.add(carte);
        carte = new CarteTrésorTrophée(NomTrésor.Pierre);
        listeCartes.add(carte);
        carte = new CarteTrésorTrophée(NomTrésor.Calice);
        listeCartes.add(carte);
        carte = new CarteTrésorTrophée(NomTrésor.Cristal);
        listeCartes.add(carte);
        carte = new CarteTrésorTrophée(NomTrésor.Pierre);
        listeCartes.add(carte);
        
        ArrayList<Aventurier> listeAventuriers = new ArrayList<>();
        Explorateur avent = new Explorateur(new Coordonnees("2", "3"));
        Pilote avent2 = new Pilote(new Coordonnees("2", "3"));
        Plongeur avent3 = new Plongeur(new Coordonnees("2", "3"));
        listeAventuriers.add(avent);
        listeAventuriers.add(avent2);
        listeAventuriers.add(avent3);
        
        VueDonnerCarte vue = new VueDonnerCarte(listeCartes, listeAventuriers, (int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2, (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2, null);
    }*/

    @Override
    public void traiterMessage(Message msg) {
        int nbCasesClicked = 0;
        switch(msg.getTypeMessage()){
            case GetClicked:
                for(PanelCarteTrophee pct : listeCarteTresor){
                    if(pct.getClicked()){
                        nbCasesClicked++;
                    }
                    if(!(pct==msg.getpCT())){
                        pct.setClicked(false);
                    }
                }
                if(nbCasesClicked==0){
                    msg.getpCT().setClicked(true);
                }
            default:;
        }
    }
}
