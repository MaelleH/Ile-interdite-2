/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue.panels;

import com.sun.javafx.css.Rule;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
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
import javax.swing.JScrollPane;
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
    
    private JPanel panelPioche;
    private Image imagePioche;
    
    private JPanel panelDefausse;
    private JLabel labelDefausse;
    private JScrollPane scrollPaneDefausse;

    public PanelEtatPiocheDefausse(int typeCarte, int nbCartesPioche, ArrayList<String> listeCartesDefausse) {
        this.typeCarte = typeCarte;
        this.nbCartesPioche = nbCartesPioche;
        this.listeCartesDefausse = listeCartesDefausse;
        
        
        this.setLayout(new BorderLayout());
        
        
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
        this.add(panelCentral,BorderLayout.CENTER);
            //Partie Centrale - Pioche
            panelPioche = new JPanel();
            panelPioche.setBorder(BorderFactory.createEmptyBorder(3, 3, 3, 3));
            try {
                if(typeCarte == 0){
                    imagePioche = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/DosDeCartes/Fond_Bleu.png"));

                }else{
                    imagePioche = ImageIO.read(new File(System.getProperty("user.dir")+"/src/Vue/DosDeCartes/Fond_Rouge.png"));
                }
            } catch (IOException ex) {
                Logger.getLogger(PanelEtatPiocheDefausse.class.getName()).log(Level.SEVERE, null, ex);
            }
            panelCentral.add(panelPioche);
            //Partie Centrale - Defausse
            panelDefausse = new JPanel(new BorderLayout());
                //Label
                labelDefausse = new JLabel("Contenu de la défausse :",SwingConstants.LEFT);
                panelDefausse.add(labelDefausse,BorderLayout.NORTH);
                //ScrollPane
                scrollPaneDefausse = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
                //scrollPaneDefausse.setColumnHeaderView(new Rule(Rule., true));
                setLabelsScrollPane();
                panelDefausse.add(scrollPaneDefausse,BorderLayout.SOUTH);
            panelCentral.add(panelDefausse);
            
            
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        g.drawImage(imagePioche,(int) panelPioche.getLocation().getX(),(int) panelPioche.getLocation().getX(),(int) panelPioche.getSize().getWidth(), (int) panelPioche.getSize().getHeight(), this);
    }
    
    
    
    public void setLabelsScrollPane(){
        scrollPaneDefausse.removeAll();
        
        JLabel label;
        for(String contenuLabel : listeCartesDefausse){
            label = new JLabel(contenuLabel);
            scrollPaneDefausse.add(label);
        }
    }
    
    
    public static void main(String [] args) {
        JFrame window = new JFrame();
        window.setSize(200, 200);
        
        
        ArrayList<String> listeCartesDefausse= new ArrayList<>();
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        listeCartesDefausse.add("oui");
        
        window.add(new PanelEtatPiocheDefausse(0, 24, listeCartesDefausse));
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
}
