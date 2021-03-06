/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import Util.Couleur;
import Util.NomTrésor;
import Util.Utils;
import Util.Utils.EtatTuile;
import ile.interdite.Message;
import ile.interdite.Observateur;
import Util.TypeMessage;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ferreijo
 */
public class PanelCase extends JPanel{
    private String typeCase;//"ile" pour une tuile, "vide" pour case vide, "tresor" pour affichage des tresors obtenus
    
    private NomTrésor nomT;
    private boolean recupere;
    private Image imageTresorNRecup;
    private Image imageTresorRecup;
    
    private int etatListener; /*0 pour inactive,1 pour cliquable#deplacement,2 pour cliquable#assechement,3 pour cliquable#deplacementPvPilote,4 pour cliquable#assechemen_sac,5pour cliquable#depla_helico*/
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
    }
    
    public PanelCase(NomTrésor nomt) {
        typeCase = "tresor";
        setNomT(nomt);
        setRecupere(false);
        pionAAfficher = new ArrayList<>();
        this.setBackground(Couleur.GRIS_CLAIR.getColor());
        
        try {
            imageTresorRecup = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/IconesTresors/"+getNomT().toString().toLowerCase()+".png"));
        } catch (IOException ex) {
            Logger.getLogger(PanelCase.class.getName()).log(Level.SEVERE, null, ex);
        }
;
        try {
            imageTresorNRecup = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/IconesTresors/"+getNomT().toString().toLowerCase()+"_terne.png"));
        } catch (IOException ex) {
            Logger.getLogger(PanelCase.class.getName()).log(Level.SEVERE, null, ex);
        }
;
    }
    
    public PanelCase(String nomCase,EtatTuile etatCase,NomTrésor tresor,Observateur obs) {
        typeCase = "ile";
        etatListener = 0;
        controleur = obs;
        this.setBackground(Couleur.GRIS_CLAIR.getColor());
        
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
                }else if(etatListener==1||etatListener==3){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.ALLER);
                    m.setpC(pC);
                    controleur.traiterMessage(m);
                }else if(etatListener==2){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.ASSECHER);
                    m.setpC(pC);
                    controleur.traiterMessage(m);
                }else if(etatListener==4){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.ASSECHER_SAC);
                    m.setpC(pC);
                    controleur.traiterMessage(m);
                }else if(etatListener==5){
                    Message m = new Message();
                    m.setTypeMessage(TypeMessage.ALLER_HELICO);
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
            //Affichage de la bordure selon la possibilité on non de l'action voulue
            if(etatListener==0){    
                setBorder(BorderFactory.createEmptyBorder(4,4,4,4));
            }else if(etatListener==1){      //si la case est atteignable
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(Couleur.ORANGE.getColor(), 4)));
            }else if(etatListener==2){      //si la case est assechable
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(Couleur.DEEP_ROSE.getColor(), 4)));
            }else if(etatListener==3){
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(Couleur.BLEU_FONCE.getColor(), 4)));
            }else if(etatListener==4){
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(Couleur.DEEP_ROSE.getColor(), 4)));
            }else if(etatListener==5){
                setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(1,1,1,1), BorderFactory.createLineBorder(Couleur.ORANGE.getColor(), 4)));
            }
            
            //Affichage du Nom de la Case
            labelNomCase.setText(nomCase);
            
            //changement de la couleur de la case selon son etat
            Color c = null;
            if(etatCase.toString().equals(EtatTuile.ASSECHEE.toString())){
                c = Couleur.DARK_ROUGE.getColor();
                labelEtatCase.setText(etatCase.toString());
            }else if(etatCase.toString().equals(EtatTuile.INONDEE.toString())){
                c = Couleur.BLEU_CLAIR.getColor();
                labelEtatCase.setText(etatCase.toString());
            }else if(etatCase.toString().equals(EtatTuile.COULEE.toString())){
                c = Couleur.BLEU_FONCE.getColor();
                labelEtatCase.setText(etatCase.toString());
            }
                if(nomCase.equals("Heliport")){
                    panelNomCase.setBackground(Color.white);
                }else{
                    panelNomCase.setBackground(c);
                }
                panelEtatCase.setBackground(c);
                panelBas.setBackground(c);
                panelJoueurs.setBackground(c);

                
            //Affichage du trésor présent sur la Case
            if(tresor.equals(NomTrésor.Cristal)){
                panelTresor.setBackground(Couleur.TOMATE.getColor());
            }else if(tresor.equals(NomTrésor.Calice)){
                panelTresor.setBackground(Couleur.BLEU_CALICE.getColor());
            }else if(tresor.equals(NomTrésor.Zéphyr)){
                panelTresor.setBackground(Couleur.JAUNE_GOLDE.getColor());
            }else if(tresor.equals(NomTrésor.Pierre)){
                panelTresor.setBackground(Couleur.MAUVE.getColor());
            }else{
                panelTresor.setBackground(panelEtatCase.getBackground());
            }
            //Affichage des joueurs
            panelJoueurs.afficherJoueurs(pionAAfficher);
        }else if(typeCase.equals("tresor")){
            super.paintComponent(g);
            int borderWidth = this.getInsets().left+this.getInsets().right;
            int borderHeight = this.getInsets().top+this.getInsets().bottom;
            if(isRecupere()){
                g.drawImage(imageTresorRecup, borderWidth/2, borderHeight/2, this.getWidth()-borderWidth, this.getHeight()-borderHeight, this);
            }else{
                g.drawImage(imageTresorNRecup, borderWidth/2, borderHeight/2, this.getWidth()-borderWidth, this.getHeight()-borderHeight, this);
            }
            
            if(nomT.equals(NomTrésor.Cristal)){
                this.setBackground(Couleur.TOMATE.getColor());
            }else if(nomT.equals(NomTrésor.Calice)){
                this.setBackground(Couleur.BLEU_CALICE.getColor());
            }else if(nomT.equals(NomTrésor.Zéphyr)){
                this.setBackground(Couleur.JAUNE_GOLDE.getColor());
            }else if(nomT.equals(NomTrésor.Pierre)){
                this.setBackground(Couleur.MAUVE.getColor());
            }
            revalidate();
        }
    }
    
    
    
    public void updateCase(String nomCase,EtatTuile etatCase,NomTrésor tresor,ArrayList<Utils.Pion> pionAAfficher){
        
        if (typeCase == "ile"){
            setNomCase(nomCase);
            setEtatCase(etatCase);
            setTresor(tresor);
            this.pionAAfficher = pionAAfficher;
            
        }
        this.repaint();
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

    /**
     * @return the nomT
     */
    public NomTrésor getNomT() {
        return nomT;
    }

    /**
     * @param nomT the nomT to set
     */
    public void setNomT(NomTrésor nomT) {
        this.nomT = nomT;
    }

    /**
     * @return the recupere
     */
    public boolean isRecupere() {
        return recupere;
    }

    /**
     * @param recupere the recupere to set
     */
    public void setRecupere(boolean recupere) {
        this.recupere = recupere;
    }
    
    
    
}
