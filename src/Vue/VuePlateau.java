/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;



import Model.Aventuriers.Aventurier;
import Vue.panels.KitPanelAventurier;
import Vue.panels.PanelAventurier;
import Vue.panels.PanelCase;
import Model.cartesTresor.CarteTrésor;
import Model.cartesTresor.CarteTrésorTrophée;
import Util.Coordonnees;
import Util.Couleur;
import Util.NomTrésor;
import static Util.NomTrésor.Pierre;
import Util.Utils;
import Util.Utils.EtatTuile;
import Vue.panels.PanelFadingPopUP;
import ile.interdite.Message;
import ile.interdite.Observateur;
import Util.TypeMessage;
import static Util.TypeMessage.RELANCERJEU;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public class VuePlateau implements Observateur{
    private JFrame window;
    private JPanel mainPanel;
    private JPanel panelPlateau;
    private JPanel panelAventuriers;
    private VueDefausse vueDefausse;
    private VueDonnerCarte vueDonnerCarte;
    private  PanelFadingPopUP popUp;
    
    
    private Observateur controleur;
    private ArrayList<PanelAventurier> listePanelAventuriers;
    
    
    private HashMap<String,PanelCase> listeCases = new HashMap<>();

    /**
     *
     * @param obs
     */
    public VuePlateau(ArrayList<KitPanelAventurier> kitsPanelsAventuriers,Observateur obs) {
        listePanelAventuriers = new ArrayList<>();
        controleur = obs;
        
        this.window = new JFrame();
        
        window.setSize(1200, 900);
        window.setLocation((int)((Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2)-(window.getSize().width/2)),(int)((Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2)-(window.getSize().height/2)));
        window.setTitle("Ile Interdite");
        
        mainPanel = new JPanel(new BorderLayout());
        window.setLayout(new BorderLayout());
        
        panelPlateau = new JPanel(new GridLayout(6,6));
        panelPlateau.setBackground(Couleur.GRIS_CLAIR.getColor());
        window.add(panelPlateau,BorderLayout.CENTER);
        
        panelAventuriers = new JPanel(new GridLayout(4, 1));
        window.add(panelAventuriers,BorderLayout.WEST);
        
        
        
        
        initPanelAventuriers(kitsPanelsAventuriers);
        initPlateau();
        
        
     
        this.window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.addWindowListener(new WindowAdapter(){
            @Override
            public void windowClosing(WindowEvent e) {
                
                JOptionPane d = new JOptionPane(); 
                String YNM[]={ "Oui", "Non", "Menu Principal"};
                int rO = JOptionPane.showOptionDialog(d, "Voulez vous vraiment quitter?", "Quitter?",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE,null,YNM,YNM[0]);
                
                if (rO==JOptionPane.YES_OPTION){
                    window.dispose();
                    if(vueDefausse!=null){
                        vueDefausse.dispose();
                    }
                }
                else if (rO==JOptionPane.CANCEL_OPTION){
                    window.dispose();
                    if(vueDefausse!=null){
                        vueDefausse.dispose();
                    }
                    Message m = new Message();
                    m.setTypeMessage(RELANCERJEU);
                    controleur.traiterMessage(m);
                }
            }
            
        });
    }
    
    public void initPlateau(){
        PanelCase uneCase;
        this.window.add(panelPlateau);
        
        for(int i = 1; i<=6;i++){
            for(int j = 1; j<=6;j++){
                if ((i==1 && (j<3||j>4)) || (i==2 && (j==1||j==6)) || (i==5 && (j==1||j==6))  || (i==6 && (j<3||j>4))){
                    if(i==1 && j==1){
                        uneCase = new PanelCase(NomTrésor.Calice);
                    }else if(i==1 && j==6){
                        uneCase = new PanelCase(NomTrésor.Cristal);
                    }else if(i==6 && j==1){
                        uneCase = new PanelCase(NomTrésor.Pierre);
                    } else if(i==6 && j==6){
                        uneCase = new PanelCase(NomTrésor.Zéphyr);
                    }else{
                        uneCase = new PanelCase();
                        
                    } 
                    
                    addListeCases(Integer.toString(i)+Integer.toString(j),uneCase);
                    this.panelPlateau.add(uneCase);
                }else{
                    uneCase = new PanelCase("Tuile",EtatTuile.ASSECHEE,Pierre,this);
                    addListeCases(Integer.toString(i)+Integer.toString(j),uneCase);
                    
                    this.panelPlateau.add(uneCase);
                }
            }
        }
    }
    
    public void majIconesTresors(boolean bCalice,boolean bCristal,boolean bPierre,boolean bZephyr){
        
        for(int i = 1; i<=6;i++){
            for(int j = 1; j<=6;j++){
                    if(i==1 && j==1){
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).setRecupere(bCalice);
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).repaint();
                    }else if(i==1 && j==6){
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).setRecupere(bCristal);
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).repaint();
                    }else if(i==6 && j==1){
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).setRecupere(bPierre);
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).repaint();
                    } else if(i==6 && j==6){
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).setRecupere(bZephyr);
                        listeCases.get(Integer.toString(i)+Integer.toString(j)).repaint();
                    }
                    
            }
        }
    }
    
    public void popUpMessage(String message) {
        popUp = new PanelFadingPopUP(message,(int)(panelPlateau.getLocationOnScreen().getX()+panelPlateau.getSize().width/2),(int)(panelPlateau.getLocationOnScreen().getY()+panelPlateau.getSize().width/2));
    }
    
    public void popUpGagne(){
        
    }
    
    public void popUpMonteeDesEaux() {
        popUp = new PanelFadingPopUP("Montée des Eaux!!!",(int)(panelPlateau.getLocationOnScreen().getX()+panelPlateau.getSize().width/2),(int)(panelPlateau.getLocationOnScreen().getY()+panelPlateau.getSize().width/2));
    }
    
    public void updateCase(String coord,String nomCase,EtatTuile etatCase,NomTrésor tresor,ArrayList<Utils.Pion> pionAAfficher){
        listeCases.get(coord).updateCase(nomCase, etatCase, tresor,pionAAfficher);
        
    }
    
    public void addListeCases(String key,PanelCase uneCase){
        this.listeCases.put(key, uneCase);
    }

    public void dispose(){
        window.dispose();
        if(vueDefausse!=null){
            vueDefausse.dispose();
        }
        if(vueDonnerCarte!=null){
            vueDonnerCarte.dispose();
        }
        if(popUp!=null){
            popUp.dispose();
        }
    }
    
    private void initPanelAventuriers(ArrayList<KitPanelAventurier> kitsPanelsAventuriers) {
        PanelAventurier panelA;
        
        for(KitPanelAventurier kitPanelA : kitsPanelsAventuriers){
            panelA = new PanelAventurier(kitPanelA.getNomJoueur(),kitPanelA.getNomAventurier().toString(), kitPanelA.getCouleurAventurier(),kitPanelA.getCartes(), getControleur());
            panelAventuriers.add(panelA);
            listePanelAventuriers.add(panelA);
            
            setInactive(kitPanelA.getNomAventurier());
        }
        
        JPanel filler;
        for(int i = 0;i<4-listePanelAventuriers.size();i++){
            filler = new JPanel();
            filler.setBackground(Couleur.DESERT.getColor());
            panelAventuriers.add(filler);
        }
    }

    public void popUpDefausse(ArrayList<CarteTrésor> mainCarteTrésor){
        vueDefausse = new VueDefausse(mainCarteTrésor.size()-5,mainCarteTrésor,(int)(panelPlateau.getLocationOnScreen().getX()+panelPlateau.getSize().width/2),(int)(panelPlateau.getLocationOnScreen().getY()+panelPlateau.getSize().width/2),controleur);
    }
    public void popUpDonnerCarte(ArrayList<CarteTrésorTrophée> mainCarteTrésor,ArrayList<Aventurier> aventuriers){
        vueDonnerCarte = new VueDonnerCarte(mainCarteTrésor,aventuriers,(int)(panelPlateau.getLocationOnScreen().getX()+panelPlateau.getSize().width/2),(int)(panelPlateau.getLocationOnScreen().getY()+panelPlateau.getSize().width/2),controleur);
    }
    
    public Observateur getControleur() {
        return controleur;
    }
    
    public void setActive(Utils.NomAventurier nomA){
        getPanelAventurier(nomA).setActive();
    }
    
    public void setInactive(Utils.NomAventurier nomA){
        getPanelAventurier(nomA).setInactive();
    }
    
    public PanelAventurier getPanelAventurier(Utils.NomAventurier nomA){
        for(PanelAventurier panelA : listePanelAventuriers){
            if(panelA.getNomAventurier().equals(nomA.toString())){
                return panelA;
            }
        }
        return null;
    }
    
    public void resShow(){
        for(String c : listeCases.keySet()){
            listeCases.get(c).setEtatListener(0);
        }
        for(PanelAventurier pA : listePanelAventuriers){
            pA.resetClickedSauf(null);
        }
    }
    
    public void showAssechables(Set<Coordonnees> listeCoordonnees){
        for(Coordonnees c : listeCoordonnees){
            listeCases.get(c.getX()+c.getY()).setEtatListener(2);
        } 
        
    }
    
    public void showDeplacementPossible(Set<Coordonnees> listeCoordonnees){
        for(Coordonnees c : listeCoordonnees){
            listeCases.get(c.getX()+c.getY()).setEtatListener(1);
            
            
        } 
    }
    public void showDeplacementPossible(Set<Coordonnees> listeCoordonneesDeplaNormal,Set<Coordonnees> listeCoordonneesDeplaPv){
        for(Coordonnees c : listeCoordonneesDeplaNormal){
            listeCases.get(c.getX()+c.getY()).setEtatListener(1);
        }
        for(Coordonnees c : listeCoordonneesDeplaPv){
            listeCases.get(c.getX()+c.getY()).setEtatListener(3);
        } 
    }
    
    public void showAssechablesSac(Set<Coordonnees> listeCoordonnees){
        for(Coordonnees c : listeCoordonnees){
            listeCases.get(c.getX()+c.getY()).setEtatListener(4);
        } 
        
    }
    public void showDeplacementPossibeHelico(Set<Coordonnees> listeCoordonnees){
        for(Coordonnees c : listeCoordonnees){
            listeCases.get(c.getX()+c.getY()).setEtatListener(5);
        } 
    }

    @Override
    public void traiterMessage(Message msg) {
        Message m;
        switch(msg.getTypeMessage()){
            case ALLER:
                m = new Message();
                m.setTypeMessage(TypeMessage.ALLER);
                m.setCoord(getCoordCase(msg.getpC()));
                controleur.traiterMessage(m);
                break;
            case ASSECHER:
                m = new Message();
                m.setTypeMessage(TypeMessage.ASSECHER);
                m.setCoord(getCoordCase(msg.getpC()));
                controleur.traiterMessage(m);
                break;
            case ALLER_HELICO:
                m = new Message();
                m.setTypeMessage(TypeMessage.ALLER_HELICO);
                m.setCoord(getCoordCase(msg.getpC()));
                controleur.traiterMessage(m);
                break;

            case ASSECHER_SAC:
                m = new Message();
                m.setTypeMessage(TypeMessage.ASSECHER_SAC);
                m.setCoord(getCoordCase(msg.getpC()));
                controleur.traiterMessage(m);
                break;
            default: break;
        }
    }
    
    public Coordonnees getCoordCase(PanelCase pc){
        for(String c : listeCases.keySet()){
            if(listeCases.get(c).equals(pc)){
                return new Coordonnees(Character.toString(c.charAt(0)), Character.toString(c.charAt(1)));
            }
        }
        
        return null;
    }
    
    public void setAllInactive(){
        resShow();
        for(PanelAventurier a :listePanelAventuriers){
            a.setInactive();
        }
    }
    
    public void updateMainAventurier(String nomA,ArrayList<CarteTrésor> cartes){
        for(PanelAventurier pnA : listePanelAventuriers){
            if(pnA.getNomAventurier().equals(nomA)){
                pnA.setListeCarteTresor(cartes);
                
            }
            pnA.repaint();
        }
    }
}
