/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import Util.Couleur;
import com.sun.javafx.css.Rule;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Label;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
/**
 *
 * @author ferreijo
 */
public class PanelEtatPiocheDefausse extends JPanel{
    private int typeCarte; //0 pour les cartes inondations, 1 pour les cartes trésors
    private int nbCartesPioche;
    private ArrayList<String> listeCartesDefausse;
    
    private JPanel panelTitreCarte;
    private JLabel labelTitreCarte; 
    
    private JPanel panelCentral;
    
    private PanelDosCarte panelPioche;
    private JLabel labelNbCartesPioche;
    private Image imagePioche;
    
    private JPanel panelDefausse;
    private JPanel panelTempDefausse;
    private JLabel labelDefausse;
    private JScrollPane scrollPaneDefausse;

    public PanelEtatPiocheDefausse(int typeCarte, int nbCartesPioche, ArrayList<String> listeCartesDefausse) {
        this.typeCarte = typeCarte;
        this.nbCartesPioche = nbCartesPioche;
        this.listeCartesDefausse = listeCartesDefausse;
        
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Couleur.GRIS_CLAIR.getColor(),2));
        
        //Partie Titre
        panelTitreCarte = new JPanel();
        panelTitreCarte.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.BLACK));
        this.add(panelTitreCarte,BorderLayout.NORTH);
        
        String titre;
        if(typeCarte == 0){
            titre = "Inondation";
        }else{
            titre = "Trésor";
        }
        labelTitreCarte = new JLabel("Cartes "+titre,SwingConstants.CENTER);
        panelTitreCarte.add(labelTitreCarte);
        
        //Partie Centrale
        panelCentral = new JPanel(new GridLayout(2, 1));
        panelCentral.setBorder(BorderFactory.createEmptyBorder(2, 2, 2 ,2));
        this.add(panelCentral,BorderLayout.CENTER);
            //Partie Centrale - Pioche
            try {
                if(typeCarte == 0){
                    imagePioche = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/DosDeCartes/Fond_Bleu.png"));
                }else{
                    imagePioche = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/DosDeCartes/Fond_Rouge.png"));
                }
            } catch (IOException ex) {
                Logger.getLogger(PanelEtatPiocheDefausse.class.getName()).log(Level.SEVERE, null, ex);
            }
            panelPioche = new PanelDosCarte(imagePioche);
            
            panelPioche.setLayout(new BorderLayout());
            labelNbCartesPioche = new JLabel(((Integer)nbCartesPioche).toString(),SwingConstants.CENTER);
            panelPioche.add(labelNbCartesPioche,BorderLayout.CENTER);

            labelNbCartesPioche.setFont(new Font("Serif", Font.PLAIN, 40));
            if(typeCarte == 0){
                labelNbCartesPioche.setForeground(Couleur.BLEU_FONCE.getColor());
            }else{
                labelNbCartesPioche.setForeground(Couleur.JAUNE.getColor());
            }
                  
            
            
            
            panelCentral.add(panelPioche);
            //Partie Centrale - Defausse
            panelDefausse = new JPanel(new BorderLayout());
            panelDefausse.setBorder(BorderFactory.createEmptyBorder(2, 2, 2 ,2));
                //Label
                labelDefausse = new JLabel("Contenu de la défausse :",SwingConstants.LEFT);
                panelDefausse.add(labelDefausse,BorderLayout.NORTH);
                //ScrollPane
                setLabelsScrollPane();
                
                panelDefausse.add(scrollPaneDefausse,BorderLayout.CENTER);
            panelCentral.add(panelDefausse);
            
            
    }

    public void update(int nbCartesPioche, ArrayList<String> listeCartesDefausse){
        this.nbCartesPioche = nbCartesPioche;
        this.listeCartesDefausse = listeCartesDefausse;
        labelNbCartesPioche.setText(((Integer)nbCartesPioche).toString());
        panelDefausse.remove(scrollPaneDefausse);
        setLabelsScrollPane();
        panelDefausse.add(scrollPaneDefausse);
        repaint();
        revalidate();
    }
    
    public void setLabelsScrollPane(){
        
        scrollPaneDefausse = new JScrollPane(new JList(listeCartesDefausse.toArray()),JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    }
    
    
    /*public static void main(String [] args) {
        JFrame window = new JFrame();
        window.setSize(100, 100);
        
        
        ArrayList<String> listeCartesDefausse= new ArrayList<>();
        listeCartesDefausse.add("ouiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiii");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        
        window.add(new PanelEtatPiocheDefausse(0, 24, listeCartesDefausse));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }*/
}
