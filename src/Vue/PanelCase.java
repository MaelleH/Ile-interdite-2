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
        
        //Affichage du Nom de la Case
        panelNomCase = new JPanel();
        labelNomCase = new JLabel(nomCase,SwingConstants.CENTER);
        
        panelNomCase.add(labelNomCase);
        this.add(panelNomCase);
        
        //Affichage de l'état de la case
        panelEtatCase = new JPanel();
        labelEtatCase = new JLabel(etatCase.toString(),SwingConstants.CENTER);
        
        panelEtatCase.add(labelEtatCase);
        this.add(panelEtatCase);
        
        
        
        //Affichage du trésor présent sur la Case
        this.panelTresor = new JPanel();
        this.add(panelTresor);
        /*
        switch (tresor){
            case "cristal" : this.affichageTresor.setBackground(Color.red);
            case "calice" : this.affichageTresor.setBackground(Color.magenta);
            case "zéphyr" : this.affichageTresor.setBackground(Color.yellow);
            case "pierre" : this.affichageTresor.setBackground(Color.CYAN);
        }
        this.etatCase.setLocation(0,60);
        this.etatCase.setSize(40,40);
        */
    }

    @Override
    protected void paintComponent(Graphics g) {
        //Affichage du Nom de la Case
        labelNomCase.setText(nomCase);
        
        //Affichage de l'état de la case
        if(etatCase.toString().equals(EtatTuile.ASSECHEE.toString())){
            labelEtatCase.setText(etatCase.toString());
            panelNomCase.setBackground(Color.gray);
            panelEtatCase.setBackground(Color.gray);
        }else if(etatCase.toString().equals(EtatTuile.INONDEE.toString())){
            labelEtatCase.setText(etatCase.toString());
            panelNomCase.setBackground(Color.blue);
            panelEtatCase.setBackground(Color.blue);
        }else if(etatCase.toString().equals(EtatTuile.COULEE.toString())){
            labelEtatCase.setText(etatCase.toString());
            panelNomCase.setBackground(new Color(0, 0, 139));
            panelEtatCase.setBackground(new Color(0, 0, 139));
        }
        
        //Affichage du trésor présent sur la Case
        if(tresor.equals("cristal")){
            
        }else if(tresor.equals("calice")){
            
        }else if(tresor.equals("zéphyr")){
            
        }else if(tresor.equals("pierre")){
            
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
