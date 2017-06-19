/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Model.TypeTrésor;
import Util.Utils;
import Util.Utils.EtatTuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
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
    private TypeTrésor tresor;
    
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
    
    public PanelCase(String nomCase,EtatTuile etatCase,TypeTrésor tresor) {
        typeCase = "ile";
        
        pionAAfficher = new ArrayList<>();
        
        setNomCase(nomCase);
        setEtatCase(etatCase);
        setTresor(tresor);
        
        //Parametrage du PanelCase
        setLayout(new GridLayout(3,1));
        setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        
        
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
    }

    @Override
    protected void paintComponent(Graphics g) {
        if(typeCase == "ile"){
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
            panelJoueurs.afficherJoueurs(pionAAfficher);
        }
    }
    
    
    
    public void updateCase(String nomCase,EtatTuile etatCase,TypeTrésor tresor,ArrayList<Utils.Pion> pionAAfficher){
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

    public EtatTuile getEtatCase() {
        return etatCase;
    }

    public TypeTrésor getTresor() {
        return tresor;
    }

    public void setNomCase(String nomCase) {
        this.nomCase = nomCase;
    }

    public void setEtatCase(EtatTuile etatCase) {
        this.etatCase = etatCase;
    }

    public void setTresor(TypeTrésor tresor) {
        this.tresor = tresor;
    }
    
    
    
}
