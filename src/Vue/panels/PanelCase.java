/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import Vue.panels.PanelJoueurs;
import Util.NomTrésor;
import Util.Utils;
import Util.Utils.EtatTuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import ile.interdite.Message;
import ile.interdite.Observateur;
import Util.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ferreijo
 */
public class PanelCase extends JPanel{
    private String typeCase;
    private int etatListener; /*0 pour inactive,1 pour cliquable#deplacement,2 pour cliquable#assechement*/
    private Observateur controleur;
    
    private String nomCase;
    private EtatTuile etatCase;
    private NomTrésor tresor;
    
    private JLabel labelNomCase;
    private JPanel panelNomCase;
    private JLabel labelEtatCase;
    private JPanel panelEtatCase;
    private JPanel panelTresor;
    private PanelJoueurs panelJoueurs;
    private JPanel panelBas;
    
    ArrayList<Utils.Pion> pionAAfficher;

    public PanelCase() {
        typeCase = "vide";
        pionAAfficher = new ArrayList<>();
        this.setBackground(Color.black);
    }
    
    public PanelCase(String nomCase,EtatTuile etatCase,NomTrésor tresor,Observateur obs) {
        typeCase = "ile";
        etatListener = 0;
        controleur = obs;
        
        pionAAfficher = new ArrayList<>();
        
        setNomCase(nomCase);
        setEtatCase(etatCase);
        setTresor(tresor);
        
        //Parametrage du PanelCase
        setLayout(new GridLayout(3,1));
        
        
        
        //Affichage du Nom de la Case
        panelNomCase = new JPanel();
        panelNomCase.setLayout(new BorderLayout());
        labelNomCase = new JLabel(nomCase,SwingConstants.CENTER);
        labelNomCase.setFont(new Font(labelNomCase.getFont().getFontName(),labelNomCase.getFont().getStyle(),(int) (labelNomCase.getFont().getSize()*0.8)));
      
        panelNomCase.add(labelNomCase,BorderLayout.CENTER);
        this.add(panelNomCase);
        
        panelNomCase.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.black));

        //Affichage de l'état de la case
        panelEtatCase = new JPanel();
        labelEtatCase = new JLabel(etatCase.toString(),SwingConstants.CENTER);
        
        panelEtatCase.add(labelEtatCase);
        this.add(panelEtatCase);
                
        //Affichage Trésor+Joueurs
        panelBas = new JPanel();
        panelBas.setLayout(new GridLayout(1,2));
        
        panelTresor = new JPanel();
        panelJoueurs = new PanelJoueurs();
        panelBas.add(panelTresor);
        panelBas.add(panelJoueurs);
        this.add(panelBas);
        
        
        PanelCase pC= this;
        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(etatListener==0){
                    /*Mais rien ne se passe...*/
                }else if(etatListener==1){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.ALLER);
                    m.setpC(pC);
                    controleur.traiterMessage(m);
                }else if(etatListener==2){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.ASSECHER);
                    m.setpC(pC);
                    controleur.traiterMessage(m);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        
        
        if(typeCase == "ile"){
            super.paintComponent(g);
            //Affichag de la bordure
            if(etatListener==0){
                setBorder(BorderFactory.createEmptyBorder(3,3,3,3));
            }else if(etatListener==1){
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(Color.black, 2)));
            }else if(etatListener==2){
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(new Color(150,20,20), 2)));
            }
            
            //Affichage du Nom de la Case
            labelNomCase.setText(nomCase);

            //Affichage de l'état de la case
            if(etatCase.toString().equals(EtatTuile.ASSECHEE.toString())){
                labelEtatCase.setText(etatCase.toString());
                panelNomCase.setBackground(new Color(150,20,20));
                panelEtatCase.setBackground(new Color(150,20,20));
                panelBas.setBackground(new Color(150,20,20));
                panelJoueurs.setBackground(new Color(150,20,20));
            }else if(etatCase.toString().equals(EtatTuile.INONDEE.toString())){
                labelEtatCase.setText(etatCase.toString());
                panelNomCase.setBackground(new Color(0, 100, 250));
                panelEtatCase.setBackground(new Color(0, 100, 250));
                panelBas.setBackground(new Color(0, 100, 250));
                panelJoueurs.setBackground(new Color(0, 100, 250));
            }else if(etatCase.toString().equals(EtatTuile.COULEE.toString())){
                labelEtatCase.setText(etatCase.toString());
                panelNomCase.setBackground(new Color(10, 10, 200));
                panelEtatCase.setBackground(new Color(10, 10, 200));
                panelBas.setBackground(new Color(10, 10, 200));
                panelJoueurs.setBackground(new Color(10, 10, 200));
            }

            //Affichage du trésor présent sur la Case
            if(tresor.equals(NomTrésor.Cristal)){
                panelTresor.setBackground(new Color(255,81,21));
            }else if(tresor.equals(NomTrésor.Calice)){
                panelTresor.setBackground(new Color(60,130,140));
            }else if(tresor.equals(NomTrésor.Zéphyr)){
                panelTresor.setBackground(new Color(215,169,77));
            }else if(tresor.equals(NomTrésor.Pierre)){
                panelTresor.setBackground(new Color(89,79,108));
            }else{
                panelTresor.setBackground(panelEtatCase.getBackground());
            }
            //Affichage des joueurs
            panelJoueurs.afficherJoueurs(pionAAfficher);
        }
    }
    
    
    
    public void updateCase(String nomCase,EtatTuile etatCase,NomTrésor tresor,ArrayList<Utils.Pion> pionAAfficher){
        if (typeCase == "ile"){
            setNomCase(nomCase);
            setEtatCase(etatCase);
            setTresor(tresor);
            this.pionAAfficher = pionAAfficher;
            this.repaint();
        }
    }

    
    /* Getters/Setters */
    public String getNomCase() {
        return nomCase;
    }
    
    public int getEtatListener() {
        return etatListener;
    }

    public EtatTuile getEtatCase() {
        return etatCase;
    }

    public NomTrésor getTresor() {
        return tresor;
    }

    public void setNomCase(String nomCase) {
        this.nomCase = nomCase;
    }

    public void setEtatCase(EtatTuile etatCase) {
        this.etatCase = etatCase;
    }

    public void setTresor(NomTrésor tresor) {
        this.tresor = tresor;
    }

    public void setEtatListener(int etatListener) {
        this.etatListener = etatListener;
    }
    
    
    
}
