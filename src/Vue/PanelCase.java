/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Util.Utils.EtatTuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
    
    private String nomCase;
    private EtatTuile etatCase;
    private String tresor;
    
    private JLabel labelNomCase;
    private JPanel panelNomCase;
    private JLabel labelEtatCase;
    private JPanel panelEtatCase;
    private JPanel panelTresor;
    private PanelJoueurs panelJoueurs;
    private JPanel panelBas;

    public PanelCase() {
        typeCase = "vide";
        this.setBackground(Color.black);
    }
    
    public PanelCase(String nomCase,EtatTuile etatCase,String tresor) {
        typeCase = "ile";
        
        setNomCase(nomCase);
        setEtatCase(etatCase);
        setTresor(tresor);
        
        //Parametrage du PanelCase
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createLineBorder(Color.black));
        
        
        //Affichage du Nom de la Case
        panelNomCase = new JPanel();
        labelNomCase = new JLabel(nomCase,SwingConstants.CENTER);
        
        panelNomCase.add(labelNomCase);
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(typeCase == "ile"){
            //Affichage du Nom de la Case
            labelNomCase.setText(nomCase);

            //Affichage de l'état de la case
            if(etatCase.toString().equals(EtatTuile.ASSECHEE.toString())){
                labelEtatCase.setText(etatCase.toString());
                panelNomCase.setBackground(new Color(180,50,50));
                panelEtatCase.setBackground(new Color(180,50,50));
                panelJoueurs.setBackground(new Color(180,50,50));
            }else if(etatCase.toString().equals(EtatTuile.INONDEE.toString())){
                labelEtatCase.setText(etatCase.toString());
                panelNomCase.setBackground(new Color(0, 100, 250));
                panelEtatCase.setBackground(new Color(0, 100, 250));
                panelJoueurs.setBackground(new Color(0, 100, 250));
            }else if(etatCase.toString().equals(EtatTuile.COULEE.toString())){
                labelEtatCase.setText(etatCase.toString());
                panelNomCase.setBackground(new Color(0, 0, 50));
                panelEtatCase.setBackground(new Color(0, 0, 50));
                panelJoueurs.setBackground(new Color(0, 0, 50));
            }

            //Affichage du trésor présent sur la Case
            if(tresor.equals("cristal")){
                panelTresor.setBackground(new Color(255,81,21));
            }else if(tresor.equals("calice")){
                panelTresor.setBackground(new Color(60,130,140));
            }else if(tresor.equals("zéphyr")){
                panelTresor.setBackground(new Color(215,169,77));
            }else if(tresor.equals("pierre")){
                panelTresor.setBackground(new Color(89,79,108));
            }else{
                panelTresor.setBackground(panelEtatCase.getBackground());
            }
            
            //Affichage des joueurs
            panelJoueurs.afficherDisques(g);
        }
    }
    
    
    
    public void updateCase(String nomCase,EtatTuile etatCase,String tresor){
        if (typeCase == "ile"){
            setNomCase(nomCase);
            setEtatCase(etatCase);
            setTresor(tresor);
            this.repaint();
        }
    }

    
    /* Getters/Setters */
    public String getNomCase() {
        return nomCase;
    }

    public EtatTuile getEtatCase() {
        return etatCase;
    }

    public String getTresor() {
        return tresor;
    }

    public void setNomCase(String nomCase) {
        this.nomCase = nomCase;
    }

    public void setEtatCase(EtatTuile etatCase) {
        this.etatCase = etatCase;
    }

    public void setTresor(String tresor) {
        this.tresor = tresor;
    }
    
    
    
}
