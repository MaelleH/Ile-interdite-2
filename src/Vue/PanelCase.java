/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vue;

import Util.Utils.EtatTuile;
import static Util.Utils.EtatTuile.ASSECHEE;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author ferreijo
 */
public class PanelCase extends JPanel{
    private JLabel nomCase;
    private JLabel etatCase;
    private JPanel affichageTresor;

    public PanelCase() {
        this.setBackground(Color.black);
    }
    
    
    
    public PanelCase(String nomCase,EtatTuile etatCase,String tresor) {
        this.setSize(100,100);
        
        //Affichage du Nom de la Case
        this.nomCase = new JLabel(nomCase,SwingConstants.CENTER);
        this.nomCase.setLocation(0,0);
        this.nomCase.setSize(100,20);

        //Affichage de l'état de la Case
        switch (etatCase){
            case ASSECHEE : this.etatCase = new JLabel("Sêche",SwingConstants.CENTER);this.setBackground(Color.gray);
            case INONDEE : this.etatCase = new JLabel("Inondée",SwingConstants.CENTER);this.setBackground(Color.blue);
            case COULEE : this.etatCase = new JLabel("Coulée",SwingConstants.CENTER);this.setBackground(new Color(0, 0, 139));
        }
        this.etatCase.setLocation(20,20);
        this.etatCase.setSize(60,20);

        //Affichage du trésor présent sur la Case
        this.affichageTresor = new JPanel();
        switch (tresor){
            case "cristal" : this.affichageTresor.setBackground(Color.red);
            case "calice" : this.affichageTresor.setBackground(Color.magenta);
            case "zéphyr" : this.affichageTresor.setBackground(Color.yellow);
            case "pierre" : this.affichageTresor.setBackground(Color.CYAN);
        }
        this.etatCase.setLocation(0,60);
        this.etatCase.setSize(40,40);
    }
    
    public void updateCase(String nomCase,EtatTuile etatCase,String tresor){
        if (nomCase != null){
            //MAJ de l'Affichage du Nom de la Case
            this.nomCase.setText(nomCase);

            //MAJ de l'Affichage de l'état de la Case
            switch (etatCase){
                case ASSECHEE : this.etatCase.setText("Sêche");this.setBackground(Color.gray);
                case INONDEE : this.etatCase.setText("Inondée");this.setBackground(Color.blue);
                case COULEE : this.etatCase.setText("Coulée");this.setBackground(new Color(0, 0, 139));
            }
          

            //MAJ de l'Affichage du trésor présent sur la Case
            switch (tresor){
                case "cristal" : this.affichageTresor.setBackground(Color.red);
                case "calice" : this.affichageTresor.setBackground(Color.magenta);
                case "zéphyr" : this.affichageTresor.setBackground(Color.yellow);
                case "pierre" : this.affichageTresor.setBackground(Color.CYAN);
            }
           
        }
    }
    
    
    
}
