/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;


import Model.Coordonnees;
import Util.Utils;
import Util.Utils.EtatTuile;
import java.awt.GridLayout;
import java.util.HashMap;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author ferreijo
 */
public class VuePlateau {
    private JFrame window;
    private JPanel mainPanel;
    
    private HashMap<String,PanelCase> listeCases = new HashMap<>();

    public VuePlateau() {
        this.window = new JFrame();
        window.setSize(606, 600);
        
        window.setTitle("Vue de l'île");
        mainPanel = new JPanel(new GridLayout(6,6));
        window.add(mainPanel);
        initPlateau();
        
        
        
        this.window.setVisible(true);
    }
    
    public void initPlateau(){
        PanelCase uneCase;
        this.window.add(mainPanel);
        
        for(int i = 1; i<=6;i++){
            for(int j = 1; j<=6;j++){
                if ((i==1 && (j<3||j>4)) || (i==2 && (j==1||j==6)) || (i==5 && (j==1||j==6))  || (i==6 && (j<3||j>4))){
                    uneCase = new PanelCase();
                    addListeCases(Integer.toString(i)+Integer.toString(j),uneCase);
                    this.mainPanel.add(uneCase);
                }else{
                    uneCase = new PanelCase("Tuile",EtatTuile.ASSECHEE,"pierre");
                    addListeCases(Integer.toString(i)+Integer.toString(j),uneCase);
                    this.mainPanel.add(uneCase);
                }
            }
        }
    }
    
    public void addListeCases(String key,PanelCase uneCase){
        this.listeCases.put(key, uneCase);
    }
    
    public static void main(String [] args) {
        // Instanciation de la fenêtre 
        VuePlateau oui = new VuePlateau();
    }
}
